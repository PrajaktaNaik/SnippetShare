package com.cmpe275.snippetshare;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmpe275.snippetshare.DAO.CommentDAO;
import com.cmpe275.snippetshare.DAO.SnippetDAO;
import com.cmpe275.snippetshare.Manager.BoardManager;
import com.cmpe275.snippetshare.Manager.CommentManager;
import com.cmpe275.snippetshare.Manager.SnippetManager;
import com.cmpe275.snippetshare.Manager.UserManager;
import com.cmpe275.snippetshare.Model.Board;
import com.cmpe275.snippetshare.Model.Comment;
import com.cmpe275.snippetshare.Model.Snippet;
import com.cmpe275.snippetshare.Model.User;
import com.cmpe275.snippetshare.Utility.ApplicationConstants;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
		
		
//		ImageManager.insert();
		
//		Image test = ImageManager.retrieve("","");
		
//		model.addAttribute("UserPhoto",new String(Base64.encodeBase64(test.getImage())));
		
//		createSnippet();
		
		createComment();
		
		return "home";
	}
	
	//---------------------------------------------User Mappings------------------------------------------------------------
	
	@RequestMapping(value="/user/signup", method=RequestMethod.POST)
	public String user_signup(@RequestBody User user  ){
		if(!UserManager.is_user_email_exists(user)){
			UserManager.addUser(user);
			return "Created user account successfully!!";
		}else
			return "User already exists.Login";
	}
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	public String user_login(@RequestBody User user){
		UserManager.loginUser(user);
		return "";
	}
	
	//---------------------------------------------Board Mappings------------------------------------------------------------
	
	public String createBoard(){
		String categoryId = "cat1";
		String ownerId = "own 1";
		String boardName = "SJSU culture";
		String description = "Try";
		String type = "Public";
		List<String> sharedWith = Arrays.asList("Harsh","Fareen");
		
		Board board = new Board();
		board.setBoardName(boardName);
		board.setCategoryId(categoryId);
		board.setDescription(description);
		board.setOwnerId(ownerId);
		board.setType(type);
		board.setSharedWith(sharedWith);
		board.setSnippets(new ArrayList<Snippet>());
		
		try {
			BoardManager.createBoard(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String updateBoard(){
		String boardId = "553bd2c81f0103ad379f8cd3";
		String boardName = "SJSU tested";
		String description = "Try Good";
		String type = "Private";
		List<String> sharedWith = Arrays.asList("Harsh","Kunal");
		
		Board board = new Board();
		board.setBoardId(boardId);
		board.setBoardName(boardName);
		board.setDescription(description);
		board.setType(type);
		board.setSharedWith(sharedWith);
		
		try {
			BoardManager.updateBoard(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	//---------------------------------------------Category Mappings------------------------------------------------------------
	
	//---------------------------------------------Snippet Mappings------------------------------------------------------------
	
	public String createSnippet(){
		try {
			String boardId = "553bd6b81f011d661241c3f1";
			String ownerId = "Kunal";
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
	
	//-----------------------------------------------------------------------------------------------------------------------------
}