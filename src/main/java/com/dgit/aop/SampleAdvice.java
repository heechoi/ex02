package com.dgit.aop;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//advice class이다 annotation
@Component
@Aspect
public class SampleAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	//함수가 실행되기 전에 startLog()함수가 실행된다.
	//"execution(* com.dgit.service.MessageServiceImpl.addMessage(..))"
	//@Before("execution(* com.dgit.service.MessageServiceImpl.*(..))")
	public void startLog(JoinPoint jp){
		logger.info("===============================");
		logger.info("[startLog]");
		//logger.info("arg : "+jp.getArgs());
		
		logger.info(Arrays.toString(jp.getArgs()));
		logger.info("===============================");
	}
	
	//모든 함수들
	@Around("execution(* com.dgit.service.MessageServiceImpl.*(..))")
	public Object timeLog(ProceedingJoinPoint jp) throws Throwable{
		//시작
		long startTime = System.currentTimeMillis();
		logger.info("==================================================");
		logger.info("[timeLog]START - " +startTime);
		logger.info(jp.getSignature().getName());//함수 이름을 return
		logger.info(Arrays.toString(jp.getArgs()));
		logger.info("==================================================");
		Object result = jp.proceed(); //proxy객체를 통해 target메소드를 실행해준다.
		
		//엔드
		long endTime = System.currentTimeMillis();
		logger.info("==================================================");
		logger.info("[timeLog]End - " +endTime);
		long time = endTime - startTime;
		logger.info("time : "+time );
		logger.info("==================================================");
		return result;
	}
}
