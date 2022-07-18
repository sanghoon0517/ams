package com.ams.classes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ams.teacher.model.dto.TeacherDto;
import com.ams.teacher.service.TeacherSerivce;

@Controller
public class ClassesController {
    @Autowired
    private TeacherSerivce service;
	/**
	 * 클래스 정보 등록 화면 연결
	 * @return
	 */
	@RequestMapping("/class/register")
	public String register(Model model) {
		List<TeacherDto> list =service.listDao();
        model.addAttribute("list", list);
		return "classes/register";
	}
	/**
	 * 클래스 정보 리스트
	 * @return
	 */
	@RequestMapping("/class")
	public String list() {
		return "classes/list";
	}
	
	
	@GetMapping("/schedule")
	public String schedule(){
		return "schedule/schedule";
	}
}
