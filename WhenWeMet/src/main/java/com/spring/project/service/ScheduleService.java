package com.spring.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.dao.ScheduleDAO;
import com.spring.project.dto.ScheduleDTO;

@Service
public class ScheduleService {
	@Inject
	ScheduleDAO mapper;
	
	public void addSchedule() {
		
	}
	
	public void modifySchedule() {
		
	}
	
	public void deleteSchedule() {
		
	}
	
	public List<ScheduleDTO> getSchedule() {
		return null;
	}
}
