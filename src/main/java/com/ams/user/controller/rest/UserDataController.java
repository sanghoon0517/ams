package com.ams.user.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ams.user.dto.User;
import com.ams.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserDataController {
	private UserService userService;
	
	@GetMapping("/api/user/all")
	public ResponseEntity<?> getAllUser() {
		userService.getAllUser();
		return new ResponseEntity<>(new User(), HttpStatus.OK);
	}
}
