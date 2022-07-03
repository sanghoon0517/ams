package com.ams.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //Exception발생시 해당 클래스로 들어옴
@RestController
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value=Exception.class)
	public String handleException(Exception e) {
		return e.getMessage();
	}
}
