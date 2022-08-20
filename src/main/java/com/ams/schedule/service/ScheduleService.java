package com.ams.schedule.service;

import java.util.List;

import com.ams.schedule.model.dto.ScheduleDto;
import com.ams.schedule.model.dto.ScheduleParamDto;

public interface ScheduleService {
    public int registerSchedule(ScheduleDto dto);
    public List<ScheduleDto> getSchedule(ScheduleParamDto dto);
    public ScheduleDto getOneSchedule(int s_idx);
    public int updateSchedule(ScheduleDto dto);
    public int deleteSchedule(int s_idx);
}
