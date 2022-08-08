package com.ams.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.ams.common.model.dto.ResponseDto;
import com.ams.handler.ex.AMSApiException;
import com.ams.handler.ex.AMSException;
import com.ams.handler.ex.AMSValidationApiException;
import com.ams.handler.ex.AMSValidationException;

@ControllerAdvice //Exception발생시 해당 클래스로 들어옴
@RestController
public class AMSExceptionHandler {

	//AMSValidationException이 발동하는 모든 에외들을 낚아챔
	@ExceptionHandler(AMSValidationException.class)
	public ResponseEntity<?> validationException(AMSValidationException e) {
		//에러를 줄 유형 Script VS ResponseEntity<>
		//일단은 전부 ResponseEntity<>로 줄 예정
		//에이작스 통신을 기본으로 하기 때문에
		//1. 클라이언트에게 응답할 때는 Script로 보내는게 좋음
		//2. AJAX, Android 통신 경우에는 ResponseEntity로 보내는게 좋음
		return new ResponseEntity<>(new ResponseDto<>(0, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
	
	//AMSException이 발동하는 모든 에외들을 낚아챔
	@ExceptionHandler(AMSException.class)
	public ResponseEntity<?> exception(AMSException e) {
		//에러를 줄 유형 Script VS ResponseEntity<>
		//일단은 전부 ResponseEntity<>로 줄 예정
		//에이작스 통신을 기본으로 하기 때문에
		//1. 클라이언트에게 응답할 때는 Script로 보내는게 좋음
		//2. AJAX, Android 통신 경우에는 ResponseEntity로 보내는게 좋음
		return new ResponseEntity<>(new ResponseDto<>(0, e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
	
	//AMSValidationApiException이 발동하는 모든 에외들을 낚아챔
	@ExceptionHandler(AMSValidationApiException.class)
	public ResponseEntity<?> validationApiException(AMSValidationApiException e) {
		//에러를 줄 유형 Script VS ResponseEntity<>
		//일단은 전부 ResponseEntity<>로 줄 예정
		//에이작스 통신을 기본으로 하기 때문에
		//1. 클라이언트에게 응답할 때는 Script로 보내는게 좋음
		//2. AJAX, Android 통신 경우에는 ResponseEntity로 보내는게 좋음
		return new ResponseEntity<>(new ResponseDto<>(0, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
	
	//AMSApiException이 발동하는 모든 에외들을 낚아챔
	@ExceptionHandler(AMSApiException.class)
	public ResponseEntity<?> apiException(AMSApiException e) {
		//에러를 줄 유형 Script VS ResponseEntity<>
		//일단은 전부 ResponseEntity<>로 줄 예정
		//에이작스 통신을 기본으로 하기 때문에
		//1. 클라이언트에게 응답할 때는 Script로 보내는게 좋음
		//2. AJAX, Android 통신 경우에는 ResponseEntity로 보내는게 좋음
		return new ResponseEntity<>(new ResponseDto<>(0, e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
	
	
}
