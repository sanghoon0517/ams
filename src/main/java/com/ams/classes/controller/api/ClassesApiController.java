package com.ams.classes.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ams.classes.model.dto.ClassDto;
import com.ams.classes.service.ClassService;
import com.ams.common.model.dto.ResponseDto;
import com.ams.teacher.model.dto.TeacherDto;
import com.ams.teacher.service.TeacherSerivce;


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
            return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.BAD_REQUEST);
        }else{
            // 클래스 생성 성공이후 학생 테이블에 클래스 등록처리
            HashMap<String,String> map = new HashMap<>();
            map.put("c_nm", dto.getC_nm());
            map.put("c_color",dto.getC_color());
            map.put("c_detail",dto.getC_detail());
            map.put("st_idx", dto.getSt_idx().get(0).toString());
            for(int st_idx : dto.getSt_idx()){
                map.replace("st_idx", Integer.toString(st_idx));
                result2 = classService.updateStClass(map);
            }
            if(result2==0){
                return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
        }



	}
}
