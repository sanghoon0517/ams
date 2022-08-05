package com.ams.student.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ams.common.model.dto.ResponseDto;
import com.ams.student.model.dto.StudentDto;
import com.ams.student.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class StudentApiController {
	
	private final StudentService studentService;
	
	@PostMapping("data/student/enroll")
	public ResponseEntity<?> enrollStudent(@RequestBody StudentDto vo) {
		int result = studentService.enrollStudent(vo);
		if(result == 1) {
			return new ResponseEntity<>(new ResponseDto<String>(result, "학생 등록 성공", null), HttpStatus.OK);			
		} else {
			return new ResponseEntity<>(new ResponseDto<String>(result, "학생 등록 실패", null), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/studentList/{st_idx}")
	public ResponseEntity<?> getStudentDetail(@PathVariable int st_idx) {
		StudentDto studentDto = studentService.getStudentInfoByIdx(st_idx);
		return new ResponseEntity<>(new ResponseDto<StudentDto>(1, "OK", studentDto), HttpStatus.OK);
	}
}