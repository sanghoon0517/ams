package com.ams.common.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ams.common.model.dto.ResponseDto;
import com.ams.teacher.model.dto.TeacherDto;
import com.ams.teacher.service.TeacherSerivce;

@RestController
public class CommonApiController {

	@Autowired
	private TeacherSerivce teacherSerivce;

	@Autowired
	private AuthenticationManager manager;


	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/auth/signin")
	public ResponseEntity<?> login(@RequestBody TeacherDto dto) {
		int code=1;
		String msg= "";
		String data = "";
		if(teacherSerivce.checkIdDao(dto.getT_id())==0){
			System.out.println("####### 아이디 오류");
			code = 0;
			msg = "로그인 실패 - 존재하지 않는 아이디입니다.";
			return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.BAD_REQUEST);
		}
		TeacherDto teacher = teacherSerivce.selectOneDao(dto.getT_id());
		// 비밀번호 검증
		if (bCryptPasswordEncoder.matches(dto.getT_pwd(), teacher.getT_pwd())) {
			System.out.println("####### 로그인 성공");
			Authentication authentication = manager
					.authenticate(new UsernamePasswordAuthenticationToken(teacher.getT_id(), dto.getT_pwd()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
		} else {
			// 비밀번호 틀렸을시 로직
			System.out.println("####### 비밀번호 오류");
			code =0;
			msg = "로그인 실패 - 잘못된 비밀번호입니다.";
			return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * id 중복 검사
	 * @param dto
	 * @return 중복시 1 아닐시 0
	 */
	@PostMapping("/auth/signup/check")
	public ResponseEntity<?>  idCheck(@RequestBody TeacherDto dto) {
		int code=1;
		String msg= "";
		String data = "";
		if (teacherSerivce.checkIdDao(dto.getT_id()) >= 1) {
			code=0;
			System.out.println("####################");
			return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
		}

	}

	/*
	 * 회원가입 요청
	 */
	
	@PostMapping("/auth/signup")
	public ResponseEntity<?> signup(@RequestBody TeacherDto dto) {
		int code=1;
		String msg= "";
		String data = "";

		dto.setT_pwd(bCryptPasswordEncoder.encode(dto.getT_pwd()));
		code = teacherSerivce.insertDao(dto);
		return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
	}
}
