package com.spring.project.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ScheduleDTO implements Comparable<ScheduleDTO>{
	private int sid;
	private int mid;
	private Date start_time;
	private Date end_time;
	private String userid;
	
	@Override
	public int compareTo(ScheduleDTO s) {
		if(s.start_time.compareTo(start_time) < 0)
			return 1;
		else if(s.start_time.compareTo(start_time) > 0)
			return -1;
		return 0;
	}
}
