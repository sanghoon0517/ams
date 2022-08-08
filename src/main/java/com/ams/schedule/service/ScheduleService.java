package com.ams.schedule.service;

import java.util.List;

import com.ams.schedule.model.dto.ScheduleDto;

public interface ScheduleService {
    public int registerSchedule(ScheduleDto dto);
    public List<ScheduleDto> getSchedule();

}
