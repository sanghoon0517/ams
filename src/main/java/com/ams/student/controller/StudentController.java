package com.ams.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ams.student.model.dto.StudentDto;
import com.ams.student.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final이 붙은 변수를 메모리에 띄워줌(DI -Autowired해줌)
@Controller
public class StudentController {
	
	private final StudentService studentService;
	
	@GetMapping("/studentEnroll")
	public String enroll(){
		return "student/enroll";
	}
	
	@PostMapping("student/enroll")
	public String enrollStudent(StudentDto vo, Model model) {
		int result = studentService.enrollStudent(vo);
		model.addAttribute("result", result);
		return "enroll";
	}
}
