package com.spring.project.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ScheduleDTO {
	private int sid;
	private String sname;
	private Date start_time;
	private Date end_time;
	private String userid;
}
