package com.ams.classes.controller.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ams.classes.model.dto.ClassDto;
import com.ams.classes.service.ClassService;
import com.ams.common.model.dto.ResponseDto;
import com.ams.student.model.dto.StudentDto;
import com.ams.teacher.model.dto.TeacherDto;
import com.ams.teacher.service.TeacherSerivce;

import retrofit2.http.Path;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class ClassesApiController {
    
    @Autowired
    private TeacherSerivce service;

    @Autowired
    private ClassService classService;
    /**
     * 클래스 등록
     * @param 
     * @return
     */
    @PostMapping("/class")
	public ResponseEntity<?> idCheck(@RequestBody ClassDto dto) {
        int code=1;
		String msg= "";
		String data = "";

        //클래스 먼저 생성
        int result = classService.insertClass(dto);
        int result2 = 1;
        if(result==0){
            msg = "클래스 생성 실패";
            return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.BAD_REQUEST);
        }else{
            // 클래스 생성 성공이후 학생 테이블에 클래스 등록처리
            result2 = classService.updateStClass(dto);
            if(result2==0){
                msg = "원생->클래스 등록 실패";
                return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.BAD_REQUEST);
            }
            msg ="클래스 등록, 원생 클래스 등록 완료";
            return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
        }
	}

    @PostMapping(value="/class/studentlist")
    public ResponseEntity<?> studentlist(@RequestBody ClassDto dto) {
        int code=1;
		String msg= "";
        //나이 환산
        if(dto.getAge_max() !=null && dto.getAge_min()!=null){
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
            int nowYear = Integer.parseInt(now.format(formatter));       
            int age_min = nowYear + 1 - Integer.parseInt(dto.getAge_min());
            int age_max = nowYear + 1 -  Integer.parseInt(dto.getAge_max());
            dto.setAge_max(Integer.toString(age_max));
            dto.setAge_min(Integer.toString(age_min));
        }
        List<StudentDto> list = classService.getStudent(dto);
        for(StudentDto vo : list){
            vo.setSt_bth(classService.getKoreanAge(vo.getSt_bth()));
        }
        return new ResponseEntity<>(new ResponseDto<List<StudentDto>>(code,msg,list), HttpStatus.OK);
    }

    @GetMapping(value="/class/{c_idx}")
    public ResponseEntity<?> getMethodName(@PathVariable int c_idx) {
        int code=1;
		String msg= "";
        
        ClassDto dto = classService.getClass(c_idx);
        List<StudentDto> st_list = classService.getClassStudent(c_idx);
        int current_student_count = classService.countStClass(c_idx);
        if(dto.getC_wkd()!=null){
            dto.setC_wkd(classService.numToDay(Integer.parseInt(dto.getC_wkd())));
        }
        for(StudentDto vo : st_list){
            vo.setSt_bth(classService.getKoreanAge(vo.getSt_bth()));
        }
        dto.setSt_list(st_list);
        dto.setCurrent_student_count(current_student_count);
        dto.setT_name(classService.getTeachername(c_idx));


        return new ResponseEntity<>(new ResponseDto<ClassDto>(code,msg,dto), HttpStatus.OK);
    }
    
    // @PutMapping("/class/{c_idx}")
	// public ResponseEntity<?> idCheck(@PathVariable int c_idx) {
    //     int code=1;
	// 	String msg= "";
	// 	String data = "";

    //     //클래스 먼저 생성
    //     int result = classService.insertClass(dto);
    //     int result2 = 1;
    //     if(result==0){
    //         msg = "클래스 생성 실패";
    //         return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.BAD_REQUEST);
    //     }else{
    //         // 클래스 생성 성공이후 학생 테이블에 클래스 등록처리
    //         result2 = classService.updateStClass(dto);
    //         if(result2==0){
    //             msg = "원생->클래스 등록 실패";
    //             return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.BAD_REQUEST);
    //         }
    //         msg ="클래스 등록, 원생 클래스 등록 완료";
    //         return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
    //     }
	// }
}
