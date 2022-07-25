package com.ams.handler.ex;

import java.util.Map;

public class AMSValidationApiException extends RuntimeException{

	//객체를 구분할 때 쓰는 것
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> errorMap;
	
	public AMSValidationApiException(String message) {
		super(message); //부모한테 던져주기만 하면 됌
	}
	
	public AMSValidationApiException(String message, Map<String, String> errorMap) {
		super(message); //부모한테 던져주기만 하면 됌
		this.errorMap = errorMap;
	}
	
	public Map<String,String> getErrorMap() {
		return errorMap;
	}
}
