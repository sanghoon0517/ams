package com.ams.schedule.model.dto;

import lombok.Data;

@Data
public class NonRepeatScheduleDto {
    private int id;
    private String title;
    private String color;
    private String start;
    private String end;
    private boolean allDay;
}
