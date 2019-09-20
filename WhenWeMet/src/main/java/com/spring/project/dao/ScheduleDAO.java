package com.spring.project.dao;

import java.util.List;

import com.spring.project.dto.ScheduleDTO;

public interface ScheduleDAO{
	public void insert(ScheduleDTO dto);
	public void delete(long sid);
	public void update(ScheduleDTO dto);
	public List<ScheduleDTO> select(String userid);
	public List<ScheduleDTO> selectByManyUser(List<String> userList);
}
