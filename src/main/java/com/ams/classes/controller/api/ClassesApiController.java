package com.ams.classes.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ams.common.model.dto.ResponseDto;
import com.ams.teacher.model.dto.TeacherDto;
import com.ams.teacher.service.TeacherSerivce;


@RestController
public class ClassesApiController {
    
    @Autowired
    private TeacherSerivce service;

    /**
     * 선생님 리스트 출력
     * @param dto
     * @return
     */
    @PostMapping("/classes/teacherList")
	public ResponseDto<Integer> idCheck(Model model) {
        
        //선생님 리스트 
        List<TeacherDto> list =service.listDao();
        model.addAttribute("list", list);
		return new ResponseDto<>(HttpStatus.OK.value(), 1);

	}
}
