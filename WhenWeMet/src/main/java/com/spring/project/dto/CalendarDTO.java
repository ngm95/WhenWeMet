package com.spring.project.dto;

public class CalendarDTO {
	private String title;
	private String start;
	private String end;
	private String color = "#000000";
	
	public CalendarDTO(String title, String start, String end) {
		this.title = title;
		this.start = start;
		this.end = end;
		
		int userInt = 1;
		for (int i = 0; i < title.length(); i++) {
			userInt = (userInt+title.charAt(i))%0xffffff;
		}
		color = "#"+Integer.toHexString(((0xffffff%userInt)*(0xffffff/userInt))%0xffffff).toUpperCase();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
