package com.ams.classes.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/classes/teacherlist")
	public ResponseEntity<?> idCheck(Model model) {
        int code=1;
		String msg= "";
		String data = "";
        //선생님 리스트 
        List<TeacherDto> list =service.listDao();
        model.addAttribute("list", list);
		return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);

	}
}
