package com.spring.project.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.dao.ScheduleDAO;
import com.spring.project.dto.ScheduleDTO;
import com.spring.project.dto.TimeDTO;

@Service
public class ScheduleService {
	@Inject
	ScheduleDAO mapper;
	
	public void addSchedule(ScheduleDTO dto) {
		mapper.insert(dto);
	}
	
	public void modifySchedule(ScheduleDTO dto) {
		mapper.update(dto);
	}
	
	public void deleteSchedule(int sid) {
		mapper.delete(sid);
	}
	
	public List<ScheduleDTO> getSchedule(String userid) {
		return mapper.select(userid);
	}
	
	public List<ScheduleDTO> getScheduleByUserList(List<String> userList) {
		return mapper.selectByManyUser(userList);
	}
	
	//같은 미팅 그룹에 있는 사용자들 간에 서로 만날 수 있는 시간을 찾아줌
	public List<TimeDTO> getAvailableTime(List<String> userList) {
		List<TimeDTO> result = new ArrayList<TimeDTO>();
		List<TimeDTO> timeList = new ArrayList<TimeDTO>();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(Long.MAX_VALUE);
		Date minDate = c.getTime();
		c.setTimeInMillis(Long.MIN_VALUE);
		Date maxDate = c.getTime();
		getScheduleByUserList(userList).forEach(res -> {
			TimeDTO dto = new TimeDTO();
			dto.setStart_time(res.getStart_time());
			dto.setEnd_time(res.getEnd_time());
			timeList.add(dto);
		});	
		timeList.forEach(t -> {
			Date s = t.getStart_time();
			Date e = t.getEnd_time();
			if(s.compareTo(minDate) == -1)
				minDate.setTime(s.getTime());
			if(e.compareTo(maxDate) == 1)
				maxDate.setTime(e.getTime());
		});
		long start = minDate.getTime();
		long end = maxDate.getTime();
		
		Long startTime = 0L, endTime = 0L;
		for(long curr = start; curr < end; curr += 60000) {
			boolean intersection = false;
			for(TimeDTO t : timeList) {
				long s = t.getStart_time().getTime();
				long e = t.getEnd_time().getTime();
				if(s <= curr && curr <= e) {
					intersection = true;
				}
				else {
					intersection = false;
					break;
				}
			}
			if(intersection && startTime == 0L) {
				startTime = curr;
			}
			else if(!intersection) {
				if(startTime != 0L) {
					endTime = curr - 60000;
					TimeDTO dto = new TimeDTO();
					dto.setStart_time(new Date(startTime));
					dto.setEnd_time(new Date(endTime));
					result.add(dto);
					startTime = 0L;
					endTime = 0L;
				}
			}
		}	
		return result;
	}
}
