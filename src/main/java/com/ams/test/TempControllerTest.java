package com.ams.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TempControllerTest {
	
	@GetMapping("/")
	public String mainPage() {
		return "common/main";
	}
	@GetMapping("/login")
	public String login() {
		return "common/login";
	}
	@GetMapping("/signup")
	public String signup(){
		return "common/signup";
	}
	@GetMapping("/test")
	public String temp() {
		return "test";
	}
	@GetMapping("/studentEnroll")
	public String enroll(){
		return "student/enroll";
	}
	@GetMapping("/studentList")
	public String enrollList(){
		return "student/article";
	}

}
