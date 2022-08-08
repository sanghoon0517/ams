package com.ams.schedule.controller.api;

import org.springframework.web.bind.annotation.RestController;

import com.ams.common.model.dto.ResponseDto;
import com.ams.schedule.model.dto.ScheduleDto;
import com.ams.schedule.service.ScheduleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ScheduleApiController {
    
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping(value="/schedule")
    public ResponseEntity<?> registerSchedule(@RequestBody ScheduleDto dto) {
        int code=1;
		String msg= "";
		String data = "";
        System.out.println(dto.toString());
        if(dto.isRepeat()){
            // 반복일정 등록 기능
            int result = scheduleService.registerSchedule(dto);
            List<ScheduleDto> list= scheduleService.getSchedule();
            System.out.println(list.toString());
            if(result>0){
                msg="반복 일정 등록 성공";
                return new ResponseEntity<>(new ResponseDto<String>(result,msg,data), HttpStatus.OK);
            }else{
                msg="반복 일정 등록 실패";
                return new ResponseEntity<>(new ResponseDto<String>(result,msg,data), HttpStatus.BAD_REQUEST);
            }
        }else{
            // 일반 일정 등록
        }
        return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
    }
    
}
