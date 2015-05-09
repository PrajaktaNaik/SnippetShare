package com.cmpe275.snippetshare.aspect;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.cmpe275.snippetshare.Utility.ApplicationConstants;

@Aspect
public class LoggingAspect {

//	----------------------------------------------------------------------------------------------------
	
	// If user is not in session return false else return true
	@Before("execution(* com.cmpe275.snippetshare.Manager.BoardManager.*(..)) && args(session,..)")
		public static boolean checkUserLoggedIn(JoinPoint joinpoint, HttpSession session){
		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinpoint.getSignature().getName());
		System.out.println("******");
			String userId = getLoggedInUser(session);
			if(userId.isEmpty())
				return false;
			else
				return true;
		}
	
//	----------------------------------------------------------------------------------------------------
	
		public static String getLoggedInUser(HttpSession session){
			Object userId = session.getAttribute(ApplicationConstants.USER_ID_SESSION);
			if(userId == null)
				return "";
			else
				return String.valueOf(userId);
		}
}
