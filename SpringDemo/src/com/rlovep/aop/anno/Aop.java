package com.rlovep.aop.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aop {
	//指定切入点表达式：拦截哪些方法
	@Pointcut("execution(* com.rlovep.aop.anno..*.*(..))")
	public void pointCut(){
		
	}
	@Before("pointCut()")
	public void begin(){
		System.out.println("开始事务/异常");
	}
	@After("pointCut()")
	public void after(){
		System.out.println("提交事务/关闭");
	}
	@AfterReturning("pointCut()")
	public void afterReturning() {
		System.out.println("afterReturning()+函数返回");
	}
	@AfterThrowing("pointCut()")
	public void afterThrowing(){
		System.out.println("afterThrowing()+出现异常");
	}
	@Around("pointCut()")
	public void around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("环绕前....");
		pjp.proceed();  // 执行目标方法
		System.out.println("环绕后....");
	}
}
