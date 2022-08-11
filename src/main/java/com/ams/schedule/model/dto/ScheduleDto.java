package com.ams.schedule.model.dto;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@JsonInclude(Include.NON_NULL)
public class ScheduleDto {
    private boolean repeat;
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
    private List<String> daysOfWeek = new ArrayList<>();; 
    private boolean allDay;
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


    
}
