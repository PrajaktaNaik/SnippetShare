package com.cmpe275.snippetshare;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cmpe275.snippetshare.DAO.CommentDAO;
import com.cmpe275.snippetshare.DAO.SnippetDAO;
import com.cmpe275.snippetshare.Manager.BoardManager;
import com.cmpe275.snippetshare.Manager.CategoryManager;
import com.cmpe275.snippetshare.Manager.CommentManager;
import com.cmpe275.snippetshare.Manager.RequestMapperManager;
import com.cmpe275.snippetshare.Manager.SnippetImagesManager;
import com.cmpe275.snippetshare.Manager.SnippetManager;
import com.cmpe275.snippetshare.Manager.UserManager;
import com.cmpe275.snippetshare.Model.Board;
import com.cmpe275.snippetshare.Model.Category;
import com.cmpe275.snippetshare.Model.Comment;
import com.cmpe275.snippetshare.Model.RequestMapper;
import com.cmpe275.snippetshare.Model.Snippet;
import com.cmpe275.snippetshare.Model.SnippetImages;
import com.cmpe275.snippetshare.Model.User;
import com.cmpe275.snippetshare.Utility.ApplicationConstants;
import com.cmpe275.snippetshare.Utility.Utility;
import com.cmpe275.snippetshare.VO.RequestTracker;
import com.cmpe275.snippetshare.VO.SnippetVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//---------------------------------------------Generic Mappings------------------------------------------------------------
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req, Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	//---------------------------------------------User Mappings------------------------------------------------------------
	@RequestMapping(value="/user/signUp", method=RequestMethod.POST)
	public String user_signup(HttpServletRequest req, Model model, String email, String firstName, String lastName, String password ){
		
		User user = new User();
		user.setEmail(email);
		user.setFullName(firstName+" "+lastName);
		user.setPassword(Utility.getEncryptedValue(password));
		user.setFollowers(new ArrayList<String>());
		user.setFollowing(new ArrayList<String>());
		user.setProfilePicture("");
		
		System.out.println("came here for sign up");
		System.out.println(user.toString());
		try {
			UserManager.addUser(user);
			return "home";
		} catch (Exception e) {
			e.printStackTrace();
			return "signup";
		}
		
	}
	

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String user_login(HttpServletRequest req, Model model, String email, String password){
		User user = new User();
		user.setEmail(email);
		user.setPassword(Utility.getEncryptedValue(password));
		
		try {
			if(UserManager.loginUser(user)){
				HttpSession session = req.getSession();
				session.setAttribute(ApplicationConstants.USER_ID_SESSION, user.getEmail());
				return user_boards(req, model);
			}else
				return "home";	
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@RequestMapping(value="/user/editprofile",method=RequestMethod.GET)
	 public String user_profile(){
		return "profile";
	}
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	public String user_login(@RequestBody User user) throws Exception{
		UserManager.loginUser(user);
		return "";
	}

	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String user_signup(){
		return "signup";
	}
	
	@RequestMapping(value="/header",method=RequestMethod.GET)
	public String getHeader(){
		return "header";
	}

	
	@RequestMapping(value="/aboutUs",method=RequestMethod.GET)
	public String aboutUs(HttpServletRequest req){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		return "aboutUs";
	}
	
	@RequestMapping(value="/contact",method=RequestMethod.GET)
	public String contactUs(HttpServletRequest req){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		return "contact";
	}
	
	
	
	//---------------------------------------------Board Mappings------------------------------------------------------------

	@RequestMapping(value="/boards",method=RequestMethod.GET)
	public String user_boards(HttpServletRequest req, Model model){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
		String currentUser = getLoggedInUser(req.getSession()); 
		User user=new User();
		user.setEmail(currentUser);
		
		try {
			ApplicationContext context= new ClassPathXmlApplicationContext("Spring-config.xml");
			BoardManager boardmanager=(BoardManager)context.getBean("board");
			List<Board> allBoards=boardmanager.getAllBoards(req.getSession(), user);
			List<Board> publicBoards=new ArrayList<Board>();
			List<Board> privateBoards=new ArrayList<Board>();
			
			for(int i=0;i<allBoards.size();i++){
				Board b=(Board)allBoards.get(i);
				if(b.getType().equalsIgnoreCase("private")){
					privateBoards.add(b);
				}
				else{
					publicBoards.add(b);
				}
			}
			
			List<Category> categoryList = CategoryManager.getAllCategories();
			List<String> userList = UserManager.getAllUsers(currentUser);
			model.addAttribute("publicBoards",publicBoards);
			model.addAttribute("privateBoards",privateBoards);
			model.addAttribute("boardTypes", ApplicationConstants.BOARD_TYPES);
			model.addAttribute("Categories", categoryList);
			model.addAttribute("Users", userList);
			System.out.println("here in boards");
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		return "boards";
	}
	   
	@RequestMapping(value="/createBoard",method=RequestMethod.POST)
	public String createBoard(HttpServletRequest req, Model model, String boardName,String privacy ,String boardDescription,String sharedWith, String category ){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
		Board newBoard=new Board();
		newBoard.setBoardName(boardName);
		newBoard.setDescription(boardDescription != null ? boardDescription : "");
		newBoard.setSharedWith(parseUsers(sharedWith));
		newBoard.setOwnerId(getLoggedInUser(req.getSession()));
		newBoard.setType(privacy);
		newBoard.setCategoryId(category);
		newBoard.setSnippets(new ArrayList<Snippet>());
		try {
			ApplicationContext context= new ClassPathXmlApplicationContext("Spring-config.xml");
			BoardManager boardmanager=(BoardManager)context.getBean("board");
			boardmanager.createBoard(req.getSession(), newBoard);
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		return user_boards(req, model);
	}
	
	@RequestMapping(value="/sharedWith",method=RequestMethod.GET)
	public @ResponseBody String getSharedWithUsers(HttpServletRequest req, String boardId){
		
		String result = "";
		try{
			result = BoardManager.getSharedUser(boardId);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("My String"+result);
		return result;
	}
	
	private List<String> parseUsers(String sharedWith) {
		List<String> userEmails = new ArrayList<String>();
		if(sharedWith != null && !sharedWith.isEmpty()){
			StringTokenizer tokenizer = new StringTokenizer(sharedWith, " , ");
			while(tokenizer.hasMoreTokens()){
				userEmails.add(tokenizer.nextToken());
			}
		}
		return userEmails;
	}

	@RequestMapping(value="/editBoard",method=RequestMethod.POST)
	public String updateBoard(HttpServletRequest req, Model model, String boardId2, 
			String boardName2, String privacy2 ,String boardDescription2, String sharedWith2, String category2){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
		Board newBoard=new Board();
		newBoard.setBoardId(boardId2);
		newBoard.setBoardName(boardName2);
		newBoard.setDescription(boardDescription2 != null ? boardDescription2 : "");
		newBoard.setSharedWith(parseUsers(sharedWith2));
		newBoard.setType(privacy2);
		newBoard.setCategoryId(category2);
		
		try {
			ApplicationContext context= new ClassPathXmlApplicationContext("Spring-config.xml");
			BoardManager boardmanager=(BoardManager)context.getBean("board");
			boardmanager.updateBoard(req.getSession(), newBoard);
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		return user_boards(req, model);
	}
	
	@RequestMapping(value="/deleteBoard", method= RequestMethod.POST)
	public String deleteBoard(HttpServletRequest req, Model model, String boardId){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
		try {
			ApplicationContext context= new ClassPathXmlApplicationContext("Spring-config.xml");
			BoardManager boardmanager=(BoardManager)context.getBean("board");	
			boardmanager.deleteBoard(req.getSession(), boardId);
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		return user_boards(req, model);
	}
	
	@RequestMapping(value="/searchBoards", method= RequestMethod.POST)
	public String searchBoards(HttpServletRequest req, Model model, String type, String value){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
		System.out.println("Type"+type+"Value:"+value);
		try{
			List<Category> categoryList = CategoryManager.getAllCategories();
			List<String> userList = UserManager.getAllUsers(getLoggedInUser(req.getSession()));
			
			ApplicationContext context= new ClassPathXmlApplicationContext("Spring-config.xml");
			BoardManager boardmanager=(BoardManager)context.getBean("board");
			boardmanager.searchBoards(req.getSession(),type, value, getLoggedInUser(req.getSession()), model);
			
			model.addAttribute("Categories", categoryList);
			model.addAttribute("Users", userList);
			model.addAttribute("searchType", type);
			
		}catch(Exception e){
			e.printStackTrace();
			return "home";
		}
		return "searchBoards";
	}
	
	@RequestMapping(value="/viewSearchBoards", method= RequestMethod.GET)
	public String viewSearchBoards(HttpServletRequest req, Model model){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
		try{
			List<Category> categoryList = CategoryManager.getAllCategories();
			List<String> userList = UserManager.getAllUsers(getLoggedInUser(req.getSession()));
			model.addAttribute("publicBoards",new ArrayList<Board>());
			model.addAttribute("privateBoards",new ArrayList<Board>());
			model.addAttribute("searchValue", "");
			model.addAttribute("searchType", ApplicationConstants.SEARCH_CATEGORY);
			model.addAttribute("Categories", categoryList);
			model.addAttribute("Users", userList);
		}catch(Exception e){
			e.printStackTrace();
			return "home";
		}
		return "searchBoards";
	}
	
	@RequestMapping(value="/showSnippets/{boardId}",method=RequestMethod.GET)
	public String showBoard(HttpServletRequest req, Model model,@PathVariable  String boardId){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
		List<SnippetVO> allImagesList=new ArrayList<SnippetVO>();
		
		try {
			List<Snippet> snippetData=SnippetDAO.getAllSnippets(boardId);
			for(int i=0;i<snippetData.size();i++){
				SnippetVO snippetVO=new SnippetVO();
				snippetVO.setDescription(snippetData.get(i).getDescription());
				snippetVO.setSnippetId(snippetData.get(i).getSnippetId());
				snippetVO.setOwnerId(snippetData.get(i).getOwnerId());
				byte[] imageData=(byte[])SnippetImagesManager.getImage(snippetData.get(i).getSnippetId()).getImage();
				snippetVO.setImage(new String(Base64.encodeBase64(imageData)));
				allImagesList.add(snippetVO);
				//SnippetImagesManager.getAllSnippetImages(imageIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("allSnippetsVO", allImagesList);
		model.addAttribute("loggedInUser", getLoggedInUser(req.getSession()));
		model.addAttribute("boardId", boardId);
		return "showBoard";
	}
	
	@RequestMapping(value="/viewAccess",method=RequestMethod.POST)
	public String viewAccess(HttpServletRequest req, Model model, String boardOwner){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
		try{
			System.out.println("Access---->"+boardOwner);
			List<RequestTracker> requestList = RequestMapperManager.getBoardsForAccess(boardOwner, getLoggedInUser(req.getSession()));
			model.addAttribute("requestList", requestList);
			return "requestAccess";
		}catch(Exception e){
			e.printStackTrace();
			return "home";
		}
	}
	
	@RequestMapping(value="/requestAccess",method=RequestMethod.POST)
	public String requestAccess(HttpServletRequest req, Model model, String boardOwner, String boardId){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
		try{
			RequestMapper mapper = new RequestMapper();
			mapper.setBoardId(boardId);
			mapper.setOwnerId(boardOwner);
			mapper.setRequesterId(getLoggedInUser(req.getSession()));
			mapper.setStatus(ApplicationConstants.REQUEST_PENDING);
			
			RequestMapperManager.saveRequest(mapper);
			
			return viewAccess(req, model, boardOwner);
		}catch(Exception e){
			e.printStackTrace();
			return "home";
		}
	}
	
	@RequestMapping(value="/viewPendingRequests",method=RequestMethod.POST)
	public String viewPendingRequests(HttpServletRequest req, Model model){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
		try{
			System.out.println("Access---->"+getLoggedInUser(req.getSession()));
			List<RequestTracker> requestList = RequestMapperManager.getPendingRequests(getLoggedInUser(req.getSession()));
			System.out.println("In Home"+requestList.size());
			model.addAttribute("requestList", requestList);
			return "pendingRequest";
		}catch(Exception e){
			e.printStackTrace();
			return "home";
		}
	}
	
	@RequestMapping(value="/processPendingRequests",method=RequestMethod.POST)
	public String processPendingRequests(HttpServletRequest req, Model model, String requestId, String boardId, String requesterId, String mode){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
		try{
			System.out.println("Access---->"+getLoggedInUser(req.getSession()));
			RequestMapperManager.updateRequest(requestId, boardId, requesterId, mode);
			
			return viewPendingRequests(req, model);
		}catch(Exception e){
			e.printStackTrace();
			return "home";
		}
	}
	
	
	//---------------------------------------------Category Mappings------------------------------------------------------------
	
	//---------------------------------------------Snippet Mappings------------------------------------------------------------
	public String createSnippet(HttpServletRequest req, Model model,String boardId,byte[] imageData,String description){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
		try {
			//boardId = "5546887bfa5f17b438635db4";
			String ownerId = getLoggedInUser(req.getSession());
			//String description = "First Snippet Insertion";
			int noOfLikes = 0;
			long snippetId = SnippetDAO.getNextSnippetId(ApplicationConstants.SNIPPET_SEQ_KEY);
			List<Comment> comments = new ArrayList<Comment>();;
			
			Snippet snippet = new Snippet();
			snippet.setSnippetId(snippetId);
			snippet.setOwnerId(ownerId);
			snippet.setDescription(description);
			snippet.setComments(comments);
			snippet.setNoOfLikes(noOfLikes);
			SnippetManager.addSnippet(boardId, snippet);
			SnippetImages newImage=new SnippetImages();
			newImage.setImageId(snippetId);
			newImage.setImage(imageData);
			SnippetImagesManager.addSnippetImages(newImage);
			return showBoard(req, model,boardId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String updateSnippet(){
		return "";
	}
	
	@RequestMapping(value="/deleteSnippet",method=RequestMethod.POST)
	public String removeSnippet(HttpServletRequest req, Model model,String imageId,String boardId){
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
		try {
			SnippetManager.deleteSnippet(imageId, boardId);
			SnippetImagesManager.deleteImage(Long.valueOf(imageId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return showBoard(req, model,boardId);
	}
	
	public String likeSnippet(){
		return "";
	}
	
	@RequestMapping(value = "/showSnippets/uploadSnippet", method = RequestMethod.POST)
    public  String  uploadFileHandler(HttpServletRequest req, Model model,@RequestParam("file") MultipartFile file,String boardIdHidden,String snippetDescription) {
		if(!checkUserLoggedIn(req.getSession())){
			return "home";
		}
		
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                System.out.println("in upload controller");
                System.out.println(bytes.toString());
                System.out.println("You successfully uploaded file="+" boardID"+boardIdHidden+" description"+snippetDescription) ;
               return  createSnippet(req, model,boardIdHidden,bytes,snippetDescription);
                //showBoard(model,"");
                
            } catch (Exception e) {
                System.out.println("You failed to upload "+e.getMessage());
                return "home";
            }
        } else {
        	 System.out.println("You failed to upload ");
        }
        
        return "";
    }
	
	//---------------------------------------------Comment Mappings------------------------------------------------------------
	
	public String createComment(){
		
		try {
			String boardId = "553bd6b81f011d661241c3f1";
			long snippetId = 4;
			long commentId = CommentDAO.getNextCommentId(ApplicationConstants.COMMENT_SEQ_KEY);
			String userName = "Kunal Barve";
			String content = "First Comment Insertion";

			Comment comment = new Comment();
			comment.setCommentId(commentId);
			comment.setContent(content);
			comment.setUserName(userName);
			CommentManager.addComment(boardId, snippetId, comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String updateComment(){
		return "";
	}
	
	//--------------------------------------------------Session Management--------------------------------------------------------
	
	// If user is not in session return false else return true
	public boolean checkUserLoggedIn(HttpSession session){
		String userId = getLoggedInUser(session);
		if(userId.isEmpty())
			return false;
		else
			return true;
	}
	
	public String getLoggedInUser(HttpSession session){
		if(session != null){
			Object userId = session.getAttribute(ApplicationConstants.USER_ID_SESSION);
			if(userId == null)
				return "";
			else
				return String.valueOf(userId);
		}else
			return "";
		
	}
	
}