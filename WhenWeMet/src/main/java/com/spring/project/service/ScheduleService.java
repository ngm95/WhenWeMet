package com.spring.project.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.dao.ScheduleDAO;
import com.spring.project.dto.CalendarDTO;
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
	
	public List<CalendarDTO> getAllJSONSchedule(int mid) {
		List<ScheduleDTO> list = mapper.selectAll(mid);
		List<CalendarDTO> result = new LinkedList<>();
		
		Iterator<ScheduleDTO> it = list.iterator();
		while(it.hasNext()) {
			ScheduleDTO item = it.next();
			Date start = item.getStart_time();
			Date end = item.getEnd_time();
			String tMonth, tDate, tHours, tMinutes, tSeconds;
			if (start.getMonth()+1 <= 9) tMonth = "0" + start.getMonth(); else tMonth = start.getMonth()+1 + "";
			if (start.getDate() <= 9) tDate = "0" + start.getDate(); else tDate = start.getDate() + "";
			if (start.getHours() <= 9) tHours = "0" + start.getHours(); else tHours= start.getHours() + "";
			if (start.getMinutes() <= 9) tMinutes = "0" + start.getMinutes(); else tMinutes = start.getMinutes() + "";
			if (start.getSeconds() <= 9) tSeconds = "0" + start.getSeconds(); else tSeconds = start.getSeconds() + "";
			String startTime = start.getYear()+1900+"-"+tMonth+"-"+tDate+"T"+tHours+":"+tMinutes+":"+tSeconds;
			if (end.getMonth()+1 <= 9) tMonth = "0" + end.getMonth(); else tMonth = end.getMonth()+1 + "";
			if (end.getDate() <= 9) tDate = "0" + end.getDate(); else tDate = end.getDate() + "";
			if (end.getHours() <= 9) tHours = "0" + end.getHours(); else tHours= end.getHours() + "";
			if (end.getMinutes() <= 9) tMinutes = "0" + end.getMinutes(); else tMinutes = end.getMinutes() + "";
			if (end.getSeconds() <= 9) tSeconds = "0" + end.getSeconds(); else tSeconds = end.getSeconds() + "";
			String endTime = end.getYear()+1900+"-"+tMonth+"-"+tDate+"T"+tHours+":"+tMinutes+":"+tSeconds;
			CalendarDTO cDto = new CalendarDTO(item.getUserid(), startTime, endTime);
			result.add(cDto);
		}
		return result;
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
		int idx = 0; long min = Long.MAX_VALUE; long max = Long.MIN_VALUE;
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
		for(long i = min; i <= max + 60000; i += 60000L) {
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
					TimeDTO td = new TimeDTO();
					td.setStart_time(new Date(startDate));
					td.setEnd_time(new Date(endDate));
					result.add(td);
					startDate = 0L;
					endDate = 0L;
				}
			}
		}
		return result;
	}
}
