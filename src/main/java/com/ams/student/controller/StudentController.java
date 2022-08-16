package com.ams.student.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ams.classes.model.dto.ClassDto;
import com.ams.classes.service.ClassService;
import com.ams.common.model.dto.PaginationCriteriaDto;
import com.ams.common.model.dto.PaginationDto;
import com.ams.student.model.dto.StudentDto;
import com.ams.student.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final이 붙은 변수를 메모리에 띄워줌(DI -Autowired해줌)
@Controller
public class StudentController {
	
	private final StudentService studentService;
	
	private final ClassService classService;
	
	@GetMapping("/studentEnroll")
	public String enroll(Model model){
//		String stdDate = studentService.getStdDate();
//		model.addAttribute("std_date", stdDate);
		
		List<ClassDto> classList  = classService.getAllClasses();
		model.addAttribute("clsList", classList);
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
		return "student/manage";
	}
	
	@GetMapping("/studentList/advanced")
	public String studentListAdvanced(/*PaginationCriteriaDto criteria, StudentDto vo,*/ Model model){
		
//		int studentTotalCnt = studentService.getStudentListCount();
//		
//		PaginationDto pagination = new PaginationDto();
//		pagination.setCriteria(criteria);
//		pagination.setTotalCount(studentTotalCnt);
//		System.out.println("[jsh] pagination : "+pagination.toString());
//		
//		List<StudentDto> resultList = studentService.getStudentListPaging(criteria);
//		model.addAttribute("stdList", resultList);
//		model.addAttribute("pagination", pagination);
		
//		List<StudentDto> resultList = studentService.getStudentList();
//		
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("total", studentTotalCnt);
//		resultMap.put("totalNotFiltered", studentTotalCnt);
//		resultMap.put("rows", resultList);
//		
//		model.addAttribute("stdList", resultMap);
		
		List<ClassDto> classList  = classService.getAllClasses();
		model.addAttribute("clsList", classList);
		
		return "student/advanced-manage";
	}
	
	@PostMapping("student/enroll")
	public String enrollStudent(StudentDto vo, Model model) {
		int result = studentService.enrollStudent(vo);
		model.addAttribute("result", result);
		return "enroll";
	}
}
