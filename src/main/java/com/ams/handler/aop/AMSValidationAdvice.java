package com.ams.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ams.handler.ex.AMSValidationApiException;
import com.ams.handler.ex.AMSValidationException;

@Component
@Aspect
public class AMSValidationAdvice { //Advice라는 명칭은 "공통기능"이라고 통용된다.

	/**
	 * 작성자 : 전상훈
	 * 
	 * AOP를 사용할 때는 Before, After, Around 어노테이션들이 있다.
	 * 
	 * @Before : 특정 함수가 실행되기전에 실행시키고 싶을 때 붙이는 어노테이션
	 * @After : 특정 함수가 실행된 이후에 실행시키고 싶을 때 붙이는 어노테이션
	 * @Around 특정 함수가 실행되기전에 관여하고, 실행 이후에도 관여하고 싶을 때 붙이는 어노테이션
	 */
	//제일 처음 execution은 실행하겠다는 의미의 함수
	//PointCut - 와일드 카드 사용법
	// "*" : 모든 것을 가리킴
	// ".." : 0개 이상을 가리킴
	//execution메서드 사용법 : execution([접근제어자] 반환타입 패키지.패키지.패키지.패키지.클래스.메소드(인자))
	@Around("execution(* com.ams.*.api.*Controller.*(**))")
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		System.out.println("[AOP] API 컨트롤러=======================================");
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg : args) {
			if(arg instanceof BindingResult) {
				System.out.println("[AOP] BindinResult값 유효성 검사 중");
				BindingResult bindingResult = (BindingResult) arg;
				
				//BindingResult 인터페이스는 에러내역들을 모두 모아준다.
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					throw new AMSValidationApiException("유효성 검사 실패", errorMap);
				}
			}
		}
		
		//BindingResult 안에 에러 필드가 없을 경우
		//정상적으로 유효성검사를 마친 것이기 때문에
		//실행하고 있는 해당 함수 라인으로 돌아가야 한다.
		return proceedingJoinPoint.proceed();
	}
	
	@Around("execution(* com.ams.*.*Controller.*(**))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		System.out.println("[AOP] 컨트롤러=======================================");
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg : args) {
			if(arg instanceof BindingResult) {
				System.out.println("[AOP] BindinResult값 유효성 검사 중");
				BindingResult bindingResult = (BindingResult) arg;
				
				//BindingResult 인터페이스는 에러내역들을 모두 모아준다.
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					throw new AMSValidationException("유효성 검사 실패", errorMap);
				}
			}
		}
		
		//BindingResult 안에 에러 필드가 없을 경우
		//정상적으로 유효성검사를 마친 것이기 때문에
		//실행하고 있는 해당 함수 라인으로 돌아가야 한다.
		return proceedingJoinPoint.proceed();
	}
	
}
