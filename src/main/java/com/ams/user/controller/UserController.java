package com.ams.user.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ams.user.model.dto.User;
import com.ams.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private UserService userService;
	
	@GetMapping("user/allUser")
	public String userPage(Model model) {
		List<User> userList = userService.getAllUser();
		model.addAttribute("userList", userList);
		return "user";
	}

}
