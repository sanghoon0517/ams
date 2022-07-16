package com.ams.common.controller.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class CommonController {
    
    @Autowired
    TeacherSerivce teacherSerivce;
	@Autowired
	private AuthenticationManager manager;

    @Autowired
    PrincipleDetailService principleDetailService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/login")
    public ResponseDto<Integer> login(@RequestBody TeacherDto dto){
        System.out.println("#############################################################################################");
        
        TeacherDto teacher = teacherSerivce.selectOneDao(dto.getT_id());
        System.out.println("#############################################################################################");
        System.out.println(bCryptPasswordEncoder.matches(dto.getT_pwd(), teacher.getT_pwd()));
        System.out.println("#############################################################################################");
        try {
            Authentication authentication= manager.authenticate(
				new UsernamePasswordAuthenticationToken(teacher.getT_id(),dto.getT_pwd()));
		    SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PostMapping("/signup")
    public ResponseDto<Integer> signup(@RequestBody TeacherDto dto){
        System.out.println(dto.toString());
        dto.setT_pwd(bCryptPasswordEncoder.encode(dto.getT_pwd()));
        teacherSerivce.insertDao(dto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
