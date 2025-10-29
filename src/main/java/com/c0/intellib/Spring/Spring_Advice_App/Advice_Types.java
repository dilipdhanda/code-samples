package com.c0.intellib.Spring.Spring_Advice_App;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Advice_Types {

  // This advice runs ONLY when the method returns normally (no exception)
  @AfterReturning(
    pointcut = "execution(* com.c0.intellib.Spring_Advice.GreetingService.greet(..))",
    returning = "result"
  )
  public void logAfterReturning(JoinPoint jp, Object result) {
    System.out.println("[AfterReturning] " + jp.getSignature()
      + " returned: " + result);
  }

  // Runs regardless of outcome (finally)
  @org.aspectj.lang.annotation.After("execution(* com.c0.intellib.Spring_Advice.GreetingService.greet(..))")
  public void afterFinally() {
    System.out.println("[After(finally)] ran");
  }

  // Runs regardless of outcome (finally)
  @org.aspectj.lang.annotation.Before("execution(* com.c0.intellib.Spring_Advice.GreetingService.greet(..))")
  public void before() {
    System.out.println("Before advice ran");
  }

  // Runs only when an exception is thrown
  @org.aspectj.lang.annotation.AfterThrowing(
    pointcut = "execution(* com.c0.intellib.Spring_Advice.GreetingService.greet(..))",
    throwing = "ex"
  )
  public void afterThrowing(Exception ex) {
    System.out.println("[AfterThrowing] caught: " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
  }
}
