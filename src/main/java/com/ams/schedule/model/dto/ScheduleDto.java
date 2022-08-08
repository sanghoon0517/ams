package com.ams.schedule.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleDto {
    private int id;
    private int s_idx;
    private int c_idx;
    private String title;
    private String color;
    private String start;
    private String end;
    private int t_idx;
    // 반복일정 param
    private String startRecur;
    private String endRecur;
    private String startTime;
    private String endTime;
    private String groupId;
    private List<Integer> daysOfWeek; 
    private boolean allDay;
    
}
