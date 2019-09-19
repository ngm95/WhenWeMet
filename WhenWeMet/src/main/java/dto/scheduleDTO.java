package dto;

import java.sql.Date;

import lombok.Data;

@Data
public class scheduleDTO {
	private int sid;
	private String sname;
	private Date start_time;
	private Date end_time;
	private String userid;
}
