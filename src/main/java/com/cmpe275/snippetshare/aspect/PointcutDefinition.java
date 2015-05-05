package com.cmpe275.snippetshare.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutDefinition {

	
	@Pointcut("within(com.cmpe275.snippetshare.Manager.UserManager.*)")
	  public void serviceLayer() {
	  }
}
