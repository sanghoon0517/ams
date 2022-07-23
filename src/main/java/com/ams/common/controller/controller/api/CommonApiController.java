package com.ams.common.controller.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ams.common.model.dto.ResponseDto;
import com.ams.config.auth.PrincipalDetail;
import com.ams.config.auth.PrincipleDetailService;
import com.ams.teacher.model.dto.TeacherDto;
import com.ams.teacher.service.TeacherSerivce;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
public class CommonApiController {

	@Autowired
	TeacherSerivce teacherSerivce;
	@Autowired
	private AuthenticationManager manager;

	@Autowired
	PrincipleDetailService principleDetailService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody TeacherDto dto) {
		TeacherDto teacher = teacherSerivce.selectOneDao(dto.getT_id());
		int code=1;
		String msg= "";
		String data = "";
		// 비밀번호 검증
		if (bCryptPasswordEncoder.matches(dto.getT_pwd(), teacher.getT_pwd())) {
			// 맞을시 로직
			Authentication authentication = manager
					.authenticate(new UsernamePasswordAuthenticationToken(teacher.getT_id(), dto.getT_pwd()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// 200리턴
			return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
		} else {
			// 비밀번호 틀렸을시 로직
			// 500 리턴
			return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
		}
	}

	@PostMapping("/signup/idCheck")
	public ResponseEntity<?>  idCheck(@RequestBody TeacherDto dto) {
		int code=1;
		String msg= "";
		String data = "";
		if (teacherSerivce.checkIdDao(dto.getT_id()) >= 1) {
			return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
		}

	}

	/*
	 * 회원가입 요청
	 */
	
	@PostMapping("/signup")
	public ResponseEntity<?>  signup(@RequestBody TeacherDto dto) {
		int code=1;
		String msg= "";
		String data = "";

		dto.setT_pwd(bCryptPasswordEncoder.encode(dto.getT_pwd()));
		int	 result = teacherSerivce.insertDao(dto);
		System.out.println("#############################################");
		System.out.println(result);
		System.out.println("#############################################");
		return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
	}
}
