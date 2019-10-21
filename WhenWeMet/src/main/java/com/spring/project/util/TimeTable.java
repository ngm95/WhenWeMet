package com.spring.project.util;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TimeTable {
	private Date start;
	private Date end;
	private List<String> selectID;
	
	public TimeTable(Date start, Date end) {
		this.start = start;
		this.end = end;
		selectID = new LinkedList<>();
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public List<String> getSelectID() {
		return selectID;
	}

	public void setSelectID(List<String> selectID) {
		this.selectID = selectID;
	}
	
	public int getSelected() {
		return selectID.size();
	}
	
	public void addID(String id) {
		selectID.add(id);
	}
	
	public int getTime() {
		return start.getHours();
	}
}
