package com.ams.teacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ams.teacher.model.dto.TeacherDto;
import com.ams.teacher.service.TeacherSerivce;

@Controller
public class TeacherController {

	
	@Autowired
	TeacherSerivce service;
	
	@RequestMapping("/dbtest")
	public String dbtest(Model model) {
		List<TeacherDto> list = service.listDao();
		System.out.println("######################################");
		TeacherDto dto = service.selectOneDao("test");
		System.out.println(dto.toString());
		model.addAttribute("list", list);
		return "test";
	}
	
	

}
