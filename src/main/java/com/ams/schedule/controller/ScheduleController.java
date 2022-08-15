package com.ams.schedule.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ams.classes.model.dto.ClassDto;
import com.ams.classes.service.ClassService;
import com.ams.student.model.dto.StudentDto;
import com.ams.student.service.StudentService;
import com.ams.teacher.model.dto.TeacherDto;
import com.ams.teacher.service.TeacherSerivce;

@Controller
public class ScheduleController {
    
    @Autowired
    private ClassService classService;
    @Autowired
    private TeacherSerivce teacherService;
    @Autowired
    private StudentService studentService;
    @GetMapping("/schedule")
    public String schedulePage(Model model){
        //클래스 목록
        List<ClassDto> classList = classService.getAllClasses();
        //선생님 목록
        List<TeacherDto> teacherList =teacherService.listDao(); 
        //학생 목록
        List<StudentDto> studentList = studentService.getStudentList();


		List<StudentDto> list3 = classService.getAllSchl(); // 학교 정보 불러오기
		List<Integer> ages = new ArrayList<>();
		for(StudentDto vo : studentList){
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
		model.addAttribute("list3", list3);
        model.addAttribute("class", classList);
        model.addAttribute("teacher", teacherList);
        model.addAttribute("student", studentList);
        return "schedule/schedule";
    }
    @GetMapping("/schedule/tabletest")
    public String testtest(){
        return "schedule/tabletest";
    }
}
