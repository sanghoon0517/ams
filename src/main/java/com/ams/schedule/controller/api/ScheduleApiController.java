package com.ams.schedule.controller.api;

import org.springframework.web.bind.annotation.RestController;

import com.ams.classes.service.ClassService;
import com.ams.common.model.dto.ResponseDto;
import com.ams.schedule.model.dto.RepeatScheduleDto;
import com.ams.schedule.model.dto.ScheduleDto;
import com.ams.schedule.model.dto.ScheduleParamDto;
import com.ams.schedule.service.ScheduleService;
import com.ams.student.model.dto.StudentDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class ScheduleApiController {
    
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ClassService classService;
    //일정 등록
    @PostMapping(value="/schedule")
    public ResponseEntity<?> registerSchedule(@RequestBody ScheduleDto dto) {
        int code=1;
		String msg= "";
		String data = "";
        System.out.println(dto.toString());
        System.out.println("####################일정 등록####################");
        if(dto.isRoutine()){
            // 반복일정 등록 기능
            int result = scheduleService.registerSchedule(dto);
            if(result>0){
                msg="반복 일정 등록 성공";
                return new ResponseEntity<>(new ResponseDto<String>(result,msg,data), HttpStatus.OK);
            }else{
                msg="반복 일정 등록 실패";
                return new ResponseEntity<>(new ResponseDto<String>(result,msg,data), HttpStatus.BAD_REQUEST);
            }
        }else{
            // 일반 일정 등록
            int result1 = scheduleService.registerSchedule(dto);
            if(result1>0){
                msg="일반 일정 등록 성공";
                return new ResponseEntity<>(new ResponseDto<String>(result1,msg,data), HttpStatus.OK);
            }else{
                msg="일반 일정 등록 실패";
                return new ResponseEntity<>(new ResponseDto<String>(result1,msg,data), HttpStatus.BAD_REQUEST);
            }
        }
        //return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
    }

    // 전체 일정 로딩(화면에 로딩된 일정)
    @GetMapping(value="/schedule/all")
    public ResponseEntity<?> getMethodName(@RequestParam String start,@RequestParam String end) {
        int code=1;
		String msg= "";
        System.out.println("###################전체 일정 로딩#####################");
        ScheduleParamDto dto = new ScheduleParamDto(end.substring(0, 10), start.substring(0,10));
        List<ScheduleDto> list= scheduleService.getSchedule(dto);
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
            temp.setAllDay(vo.isAllDay());
            temp.setGroupId(vo.getGroupId());
            temp.setStart(vo.getStart());
            temp.setEnd(vo.getEnd());
            repeat.add(temp);
        }
        //System.out.println(repeat.toString());
        return new ResponseEntity<>(new ResponseDto<List<RepeatScheduleDto>>(code,msg,repeat), HttpStatus.OK);
    }
    
    //일정 세부 사항 조회
    @GetMapping(value="/schedule/{s_idx}/post")
    public ResponseEntity<?> getSchdulePost(@PathVariable int s_idx){
        System.out.println("####################일정 조회####################");
        System.out.println(s_idx);
        ScheduleDto dto = scheduleService.getOneSchedule(s_idx);
        System.out.println(dto.getStudents().toString());
        //scheduled의 학생정보 가저오기
        if(!dto.getStudents().isEmpty()){
            List<StudentDto> list = scheduleService.getStudentSchedule(dto.getStudents());
            for(StudentDto vo : list){
                vo.setSt_bth(classService.getKoreanAge(vo.getSt_bth()));
            }
            dto.setStudentlist(list);
        }
        int code=1;
		String msg= "";
		String data = "";
        return new ResponseEntity<>(new ResponseDto<ScheduleDto>(code,msg,dto), HttpStatus.OK);
    }
    
    //일정 수정
    @PutMapping("/schedule/{s_idx}/post")
    public ResponseEntity<?> updateSchedule(@PathVariable int s_idx, @RequestBody ScheduleDto dto){
        System.out.println("####################일정 수정####################");
        System.out.println(dto.toString());
        System.out.println(s_idx); 
        int code=1;
        String msg= "";
        String data = "";
        dto.setS_idx(s_idx);
        int result = scheduleService.updateSchedule(dto);
        return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
    }
    //일정 삭제
    @DeleteMapping("/schedule/{s_idx}/post")
    public ResponseEntity<?> deleteSchedule(@PathVariable int s_idx){
        System.out.println("####################일정 삭제####################");
        System.out.println(s_idx); 
        int code=1;
        String msg= "";
        String data = "";
        int result = scheduleService.deleteSchedule(s_idx);
        return new ResponseEntity<>(new ResponseDto<String>(code,msg,data), HttpStatus.OK);
    }
}

