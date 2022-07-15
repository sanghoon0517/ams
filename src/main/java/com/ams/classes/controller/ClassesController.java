package com.ams.classes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClassesController {

	/**
	 * 클래스 정보 등록 화면 연결
	 * @return
	 */
	@RequestMapping("/class/register")
	public String register() {
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
		return "classes/schedule";
	}
}
