package com.ams.schedule.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RepeatScheduleDto {
    private int id;
    private String title;
    private String color;
    private String startRecur;
    private String endRecur;
    private String startTime;
    private String endTime;
    private String groupId;
    private List<String> daysOfWeek = new ArrayList<>(); 
    private boolean allDay;
}
