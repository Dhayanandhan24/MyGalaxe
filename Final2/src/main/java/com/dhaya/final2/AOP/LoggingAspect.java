package com.dhaya.final2.AOP;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class LoggingAspect {

	@Before(value =  "execution(* com.dhaya.final2.service.ProductServiceImpl.*(..))")
	public void doLogging() {
		//	code for logging goes here
		System.out.println("####Logging done before!!! called "+ new java.util.Date());
	}
	
	@After(value =  "execution(* com.dhaya.final2.service.ProductServiceImpl.*(..))")
	public void doLogging2() {
		//	code for logging goes here
		System.out.println("####Logging done after !!! called "+ new java.util.Date());
	}
}
