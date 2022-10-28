package com.dhaya.final2.AOP;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Component
@Aspect
@Order(1)
public class SecurityAspect {

    @Before(value="execution(* com.dhaya.final2.service.ProductServiceImpl.*(..))")
    public void securityCheck() {
        System.out.println("2. Security check before called "+new java.util.Date());
    }
    @After(value="execution(* com.dhaya.final2.service.ProductServiceImpl.*(..))")
    public void securityCheck1() {
        System.out.println("3. Security check after called "+new java.util.Date());
    }
    
    @Around(value =  "execution(* com.dhaya.final2.service.ProductServiceImpl.*(..))")
	public Object around(ProceedingJoinPoint joinpoint) throws Throwable
	{
		Signature methods = joinpoint.getSignature();
		
		System.out.println("1."+(methods.getName() + " , method execution AROUND (BEFORE) successfully at :"+new Date()));
		//code to check whether to proceed with the method execution 	
		Object retval = joinpoint.proceed();
		
		System.out.println("4. "+(methods.getName() + " , method execution AROUND (AFTER) successfully at :"+new Date()));
		
		return retval;
	}
    
}