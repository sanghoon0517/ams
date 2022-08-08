package com.ams.schedule.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ams.schedule.model.dto.ScheduleDto;
@Mapper
public interface ScheduleDao {
    public int insertRepeatSchedule(ScheduleDto dto);
    public int insertNonRepeatSchedule(ScheduleDto dto);
    public List<ScheduleDto> getSchedule();
}
