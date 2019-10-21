package com.spring.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.project.dto.ScheduleDTO;

public interface ScheduleDAO{
	public void insert(ScheduleDTO dto);
	public void delete(long sid);
	public void update(ScheduleDTO dto);
	public List<ScheduleDTO> selectByUser(@Param("userid") String userid, @Param("mid") int mid);
	public List<ScheduleDTO> selectByManyUser(@Param("list") List<String> userList, @Param("mid") int mid);
	public List<ScheduleDTO> selectAll(@Param("mid") int mid);
}