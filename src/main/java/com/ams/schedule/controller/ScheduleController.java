package com.ams.schedule.controller;

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
