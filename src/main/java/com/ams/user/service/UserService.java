package com.ams.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ams.user.model.dto.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private String userMapper;
	
	public List<User> getAllUser() {
		//final List<User> userList = userMapper.findAll();
		
		return null;
	}
}
