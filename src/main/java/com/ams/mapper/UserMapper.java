package com.ams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ams.user.model.dto.User;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM user")
	List<User> findAll();
	
	@Select("SELECT * FROM user WHERE userIdx = #{userIdx}")
	User findByUserIdx();
}
