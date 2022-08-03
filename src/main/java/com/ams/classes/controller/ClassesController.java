package com.ams.classes.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ams.classes.model.dto.ClassDto;
import com.ams.classes.service.ClassService;
import com.ams.student.model.dto.StudentDto;
import com.ams.student.service.StudentService;
import com.ams.teacher.model.dto.TeacherDto;
import com.ams.teacher.service.TeacherSerivce;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ClassesController {
	
    @Autowired
    private TeacherSerivce service;
	@Autowired
	private StudentService service2;

	@Autowired
	private ClassService classService;
	/**
	 * 클래스 정보 등록 화면 연결
	 * @return
	 */
	@GetMapping("/class")
	public String register(Model model) {
		List<TeacherDto> list =service.listDao(); // 선생님 목록 가져오기
		List<StudentDto> list2 = service2.getStudentList(); // 원생 정보 불러오기
		List<StudentDto> list3 = classService.getAllSchl(); // 학교 정보 불러오기
		List<Integer> ages = new ArrayList<>();
		for(StudentDto vo : list2){
			int birth = Integer.parseInt(vo.getSt_bth().substring(0, 4));
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
			int nowYear = Integer.parseInt(now.format(formatter));
			String result =String.valueOf(nowYear-birth+1);
			ages.add(Integer.parseInt(result));
			vo.setSt_bth(result);
		}
		int min = Collections.min(ages);
		int max = Collections.max(ages);
		model.addAttribute("min", min);
		model.addAttribute("max", max);
        model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		return "classes/register";
	}
	/**
	 * 클래스 정보 리스트
	 * @return
	 */
	@RequestMapping("/class/all")
	public String list(Model model) {
		List<ClassDto> list = classService.getAllClasses();
		for(ClassDto dto : list){
			if(dto.getC_wkd()!=null){
				String day = classService.numToDay(Integer.parseInt(dto.getC_wkd()));
				dto.setC_wkd(day);
				int current_student_count = classService.countStClass(dto.getC_idx());
				dto.setCurrent_student_count(current_student_count);
			}

		}
		model.addAttribute("classes", list);
		return "classes/list";
	}
	
	
	@GetMapping("/schedule")
	public String schedule(){
		return "schedule/schedule";
	}
	
	/**
	 * 수정 페이지로 이동
	 * @param c_idx
	 * @param model
	 * @return
	 */
	@GetMapping("/class/{c_idx}/post")
    public String updateClass(@PathVariable int c_idx ,Model model) {
		System.out.println("###################등록 기본 정보 로딩#####################");
		List<TeacherDto> list =service.listDao(); // 선생님 목록 가져오기
		List<StudentDto> list2 = service2.getStudentList(); // 원생 정보 불러오기
		List<StudentDto> list3 = classService.getAllSchl(); // 학교 정보 불러오기
		List<Integer> ages = new ArrayList<>();
		for(StudentDto vo : list2){
			int birth = Integer.parseInt(vo.getSt_bth().substring(0, 4));
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
			int nowYear = Integer.parseInt(now.format(formatter));
			String result =String.valueOf(nowYear-birth+1);
			ages.add(Integer.parseInt(result));
			vo.setSt_bth(result);
		}
		int min = Collections.min(ages);
		int max = Collections.max(ages);
		model.addAttribute("min", min);
		model.addAttribute("max", max);
        model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);

		System.out.println("##################클래스 정보 로딩######################");
        ClassDto dto = classService.getClass(c_idx);
        List<StudentDto> st_list = classService.getClassStudent(c_idx);
        int current_student_count = classService.countStClass(c_idx);
        for(StudentDto vo : st_list){
            vo.setSt_bth(classService.getKoreanAge(vo.getSt_bth()));
        }

        dto.setSt_list(st_list);
        dto.setCurrent_student_count(current_student_count);
        dto.setT_name(classService.getTeachername(c_idx));
		model.addAttribute("class", dto);
		return "classes/update";
    }
    
}
