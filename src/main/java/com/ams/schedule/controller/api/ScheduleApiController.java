package com.ams.schedule.controller.api;

import org.springframework.web.bind.annotation.RestController;

import com.ams.common.model.dto.ResponseDto;
import com.ams.schedule.model.dto.RepeatScheduleDto;
import com.ams.schedule.model.dto.ScheduleDto;
import com.ams.schedule.service.ScheduleService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



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

    @GetMapping(value="/schedule/all")
    public ResponseEntity<?> getMethodName() {
        int code=1;
		String msg= "";
		String data = "";
        List<ScheduleDto> list= scheduleService.getSchedule();
        List<RepeatScheduleDto> repeat = new ArrayList<RepeatScheduleDto>();
        for(ScheduleDto vo : list){
            RepeatScheduleDto temp = new RepeatScheduleDto();
            temp.setId(vo.getS_idx());
            temp.setTitle(vo.getTitle());
            temp.setDaysOfWeek(vo.getDaysOfWeek());
            temp.setColor(vo.getColor());
            temp.setEndRecur(vo.getEndRecur());
            temp.setStartRecur(vo.getStartRecur());
            temp.setStartTime(vo.getStartTime());
            temp.setEndTime(vo.getEndTime());
            temp.setAllDay(vo.isAllDay()==true?true:false);
            temp.setGroupId(vo.getGroupId());
            repeat.add(temp);
        }
        System.out.println(repeat.toString());
        return new ResponseEntity<>(new ResponseDto<List<RepeatScheduleDto>>(code,msg,repeat), HttpStatus.OK);
    }
    
    
}
