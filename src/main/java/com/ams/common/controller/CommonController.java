package com.ams.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
    @GetMapping("/login")
	public String login() {
		return "common/login";
	}
	@GetMapping("/signup")
	public String signup(){
		return "common/signup";
	}
}
