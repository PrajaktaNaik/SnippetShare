package com.cmpe275.snippetshare;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmpe275.snippetshare.Manager.UserManager;
import com.cmpe275.snippetshare.Model.User;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
	/*	ApplicationContext ctx = 
	             new AnnotationConfigApplicationContext(MongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		*/
		
		UserManager.createUser("prajakta", "naik");
		
		
		return "home";
	}
	
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
	}

