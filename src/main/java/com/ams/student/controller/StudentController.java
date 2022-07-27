package com.ams.student.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ams.common.model.dto.PaginationCriteriaDto;
import com.ams.common.model.dto.PaginationDto;
import com.ams.student.model.dto.StudentDto;
import com.ams.student.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final이 붙은 변수를 메모리에 띄워줌(DI -Autowired해줌)
@Controller
public class StudentController {
	
	private final StudentService studentService;
	
	@GetMapping("/studentEnroll")
	public String enroll(Model model){
//		String stdDate = studentService.getStdDate();
//		model.addAttribute("std_date", stdDate);
		return "student/enroll";
	}
	
	@GetMapping("/studentList")
	public String studentlList(PaginationCriteriaDto criteria, StudentDto vo, Model model){
//		List<StudentDto> resultList = studentService.getStudentList();
		
		int studentTotalCnt = studentService.getStudentListCount();
		
		PaginationDto pagination = new PaginationDto();
		pagination.setCriteria(criteria);
		pagination.setTotalCount(studentTotalCnt);
		System.out.println("[jsh] pagination : "+pagination.toString());
		
		List<StudentDto> resultList = studentService.getStudentListPaging(criteria);
		model.addAttribute("stdList", resultList);
		model.addAttribute("pagination", pagination);
		return "student/article";
	}
	
	@PostMapping("student/enroll")
	public String enrollStudent(StudentDto vo, Model model) {
		int result = studentService.enrollStudent(vo);
		model.addAttribute("result", result);
		return "enroll";
	}
}
