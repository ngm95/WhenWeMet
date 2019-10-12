package com.spring.project.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.dao.ScheduleDAO;
import com.spring.project.dto.ScheduleDTO;
import com.spring.project.dto.TimeDTO;
import com.spring.project.util.TimeArray;

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
	
	public List<ScheduleDTO> getScheduleByUser(String userid, int mid) {
		return mapper.selectByUser(userid, mid);
	}
	
	public List<ScheduleDTO> getScheduleByUserList(List<String> userList, int mid) {
		return mapper.selectByManyUser(userList, mid);
	}
	
	//같은 미팅 그룹에 있는 사용자들 간에 서로 만날 수 있는 시간을 찾아줌
	public List<TimeDTO> getAvailableTime(List<String> userList, int mid) {
		List<TimeDTO> result = new LinkedList<>();
		List<ScheduleDTO> list = getScheduleByUserList(userList, mid);
		Map<String, List<ScheduleDTO>> map = new HashMap<>();
		
		for(ScheduleDTO sDto : list) {
			String user = sDto.getUserid();
			if(!map.containsKey(user)) {
				List<ScheduleDTO> sList = new LinkedList<>();
				sList.add(sDto);
				map.put(user, sList);
			} else {
				List<ScheduleDTO> sList = map.get(user);
				sList.add(sDto);
				map.put(user, sList);
			}
		}
		
		Set<String> keySet = map.keySet();
		TimeArray[] tArray;
		tArray = new TimeArray[keySet.size()];
		int idx = 0;
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		for(String key : keySet) {
			List<ScheduleDTO> temp = map.get(key);
			Collections.sort(temp);
			tArray[idx] = new TimeArray(temp.size());
			for(ScheduleDTO sDto : temp) {
				TimeDTO t = new TimeDTO();
				Date st = sDto.getStart_time();
				Date et = sDto.getEnd_time();
				t.setStart_time(st);
				t.setEnd_time(et);
				tArray[idx].add(t);
				if(min > st.getTime())
					min = st.getTime();
				if(max < et.getTime())
					max = et.getTime();
			}
			idx++;
		}
		
		long startDate = 0L, endDate = 0L;
		for(long i = min; i <= max; i += 60000L) {
			long now = i;
			boolean intersect = false;
			for(int j = 0; j < tArray.length; j++) {
				long start = tArray[j].getCurr().getStart_time().getTime();
				long end = tArray[j].getCurr().getEnd_time().getTime();
				if(now > end) {
					if(tArray[j].hasNext())
						tArray[j].next();
				}
				if(start <= now && now <= end) {
					intersect = true;
				} else {
					intersect = false;
					break;
				}
			}
			
			if(intersect) {
				if(startDate == 0L) 
					startDate = now;
				else
					endDate = now;
			}
			else {
				if(startDate != 0L && endDate != 0L) {
					Date sd = new Date(startDate);
					Date ed = new Date(endDate);
					TimeDTO td = new TimeDTO();
					td.setStart_time(sd);
					td.setEnd_time(ed);
					result.add(td);
					startDate = 0L;
					endDate = 0L;
				}
			}
		}
		return result;
	}
}
