package com.cmpe275.snippetshare;

import java.text.DateFormat;
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

import com.cmpe275.snippetshare.Manager.BoardManager;
import com.cmpe275.snippetshare.Manager.UserManager;
import com.cmpe275.snippetshare.Model.Board;
import com.cmpe275.snippetshare.Model.User;

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
		
//		ImageTest test = ImageManager.retrieve("", "");
		
		
//		ImageManager.insert();
		
//		ImageTest test = ImageManager.retrieve("","");
		
//		model.addAttribute("UserPhoto",new String(Base64.encodeBase64(test.getImage())));
		
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
	
	//---------------------------------------------Comment Mappings------------------------------------------------------------
}