package dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ScheduleDTO {
	private int sid;
	private String sname;
	private LocalDateTime start_time;
	private LocalDateTime end_time;
	private String userid;
}
