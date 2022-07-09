package com.ams.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TempControllerTest {
	
	@GetMapping("/")
	public String mainPage() {
		return "common/main";
	}
	@GetMapping("/test")
	public String temp() {
		return "test";
	}
	@GetMapping("/studentEnroll")
	public String enroll(){
		return "enroll/student_enroll";
	}
	@GetMapping("/studentList")
	public String enrollList(){
		return "enroll/student_list";
	}
	@GetMapping("/schedule")
	public String schedule(){
		return "schedule/schedule";

	}
}
