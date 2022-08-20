package com.ams.schedule.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ams.schedule.model.dto.ScheduleDto;
import com.ams.schedule.model.dto.ScheduleParamDto;
@Mapper
public interface ScheduleDao {
    public int insertRepeatSchedule(ScheduleDto dto);
    public int insertNonRepeatSchedule(ScheduleDto dto);
    public List<ScheduleDto> getSchedule(ScheduleParamDto dto);
    public ScheduleDto getOneSchedule(int s_idx);
    public int updateSchedule(ScheduleDto dto);
    public int deleteSchedule(int s_idx);
}
