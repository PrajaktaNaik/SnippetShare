package com.cmpe275.snippetshare;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmpe275.snippetshare.DAO.CommentDAO;
import com.cmpe275.snippetshare.DAO.SnippetDAO;
import com.cmpe275.snippetshare.Manager.BoardManager;
import com.cmpe275.snippetshare.Manager.CategoryManager;
import com.cmpe275.snippetshare.Manager.CommentManager;
import com.cmpe275.snippetshare.Manager.SnippetImagesManager;
import com.cmpe275.snippetshare.Manager.SnippetManager;
import com.cmpe275.snippetshare.Manager.UserManager;
import com.cmpe275.snippetshare.Model.Board;
import com.cmpe275.snippetshare.Model.Category;
import com.cmpe275.snippetshare.Model.Comment;
import com.cmpe275.snippetshare.Model.Snippet;
import com.cmpe275.snippetshare.Model.User;
import com.cmpe275.snippetshare.Utility.ApplicationConstants;
import com.cmpe275.snippetshare.Utility.Utility;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public HttpSession session;
	//---------------------------------------------Generic Mappings------------------------------------------------------------
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
//		createBoard();
//		updateBoard();
		
//		ImageManager.withoutUsingGridFS();
		
//		Image test = ImageManager.retrieve("", "");
		
		
		//ImageManager.insert();
		
	/*	Image test = ImageManager.retrieve("","");
		
	model.addAttribute("UserPhoto",new String(Base64.encodeBase64(test.getImage())));*/
		
//		createSnippet();
		
		//createComment();
		
		return "home";
	}
	
	//---------------------------------------------User Mappings------------------------------------------------------------
	@RequestMapping(value="/user/signUp", method=RequestMethod.POST)
	public String user_signup(Model model, String email, String firstName, String lastName, String password ){
		
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
				session = req.getSession();
				session.setAttribute(ApplicationConstants.USER_ID_SESSION, user.getEmail());
				return user_boards(model);
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
	
	
	
	//---------------------------------------------Board Mappings------------------------------------------------------------

	@RequestMapping(value="/boards",method=RequestMethod.GET)
	public String user_boards(Model model){
		if(!checkUserLoggedIn()){
			return "home";
		}
		
		String currentUser = getLoggedInUser(); 
		User user=new User();
		user.setEmail(currentUser);
		
		try {
			List<Board> allBoards=BoardManager.getAllBoards(user);
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
		}
		
		
		return "boards";
	}
	   
	@RequestMapping(value="/createBoard",method=RequestMethod.POST)
	public String createBoard(Model model, String boardName,String privacy ,String boardDescription,String sharedWith, String category ){
		if(!checkUserLoggedIn()){
			return "home";
		}
		
		Board newBoard=new Board();
		newBoard.setBoardName(boardName);
		newBoard.setDescription(boardDescription != null ? boardDescription : "");
		newBoard.setSharedWith(parseUsers(sharedWith));
		newBoard.setOwnerId(getLoggedInUser());
		newBoard.setType(privacy);
		newBoard.setCategoryId(category);
		newBoard.setSnippets(new ArrayList<Snippet>());
		try {
			BoardManager.createBoard(newBoard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user_boards(model);
	}
	
	@RequestMapping(value="/sharedWith",method=RequestMethod.GET)
	public @ResponseBody String getSharedWithUsers(String boardId){
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
	public String updateBoard(Model model, String boardId2, 
			String boardName2, String privacy2 ,String boardDescription2, String sharedWith2, String category2){
		if(!checkUserLoggedIn()){
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
			BoardManager.updateBoard(newBoard);
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		return user_boards(model);
	}
	
	@RequestMapping(value="/deleteBoard", method= RequestMethod.POST)
	public String deleteBoard(Model model, String boardId){
		if(!checkUserLoggedIn()){
			return "home";
		}
		
		try {
			BoardManager.deleteBoard(boardId);
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		return user_boards(model);
	}
	
	@RequestMapping(value="/searchBoards", method= RequestMethod.POST)
	public String searchBoards(Model model, String type, String value){
		if(!checkUserLoggedIn()){
			return "home";
		}
		
		System.out.println("Type"+type+"Value:"+value);
		try{
			BoardManager.searchBoards(type, value, getLoggedInUser(), model);
		}catch(Exception e){
			e.printStackTrace();
			return "home";
		}
		return "searchBoards";
	}
	
	@RequestMapping(value="/showSnippets/{boardId}",method=RequestMethod.GET)
	public String showBoard(Model model,@PathVariable  String boardId){
		createSnippet();
		
	//	Snippet 
		//SnippetImagesDAO.addImage(image);
	/*	if(!checkUserLoggedIn()){
			return "home";
		}
		*/
		/*try {
			boardId="553bd6b81f011d661241c3f1";
		List<Snippet> allSnippets=(List<Snippet>)SnippetManager.getAllSnippet(boardId);
		model.addAttribute("allSnippets", allSnippets);
		for(int i=0;i<allSnippets.size();i++){
			Snippet temp=(Snippet)allSnippets.get(i);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	*/
		return "showBoard";
	}
	
	//---------------------------------------------Category Mappings------------------------------------------------------------
	
	//---------------------------------------------Snippet Mappings------------------------------------------------------------
	
	
	
	public String createSnippet(){
		try {
			String boardId = "5546887bfa5f17b438635db4";
			String ownerId = getLoggedInUser();
			String description = "First Snippet Insertion";
			int noOfLikes = 10;
			long snippetId = SnippetDAO.getNextSnippetId(ApplicationConstants.SNIPPET_SEQ_KEY);
			List<Comment> comments = new ArrayList<Comment>();;
			
			Snippet snippet = new Snippet();
			snippet.setSnippetId(snippetId);
			snippet.setOwnerId(ownerId);
			snippet.setDescription(description);
			snippet.setComments(comments);
			snippet.setNoOfLikes(noOfLikes);
			SnippetManager.addSnippet(boardId, snippet);
			SnippetImagesManager.addSnippetImages(snippetId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String updateSnippet(){
		return "";
	}
	
	public String removeSnippet(){
		return "";
	}
	
	public String likeSnippet(){
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
	public boolean checkUserLoggedIn(){
		String userId = getLoggedInUser();
		if(userId.isEmpty())
			return false;
		else
			return true;
	}
	
	public String getLoggedInUser(){
		Object userId = session.getAttribute(ApplicationConstants.USER_ID_SESSION);
		if(userId == null)
			return "";
		else
			return String.valueOf(userId);
	}
	
}