package service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import dto.ScheduleDTO;
import mapper.ScheduleMapper;

@Service
public class ScheduleService {
	@Inject
	ScheduleMapper mapper;
	
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
