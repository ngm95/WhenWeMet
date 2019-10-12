package com.spring.project.util;

import com.spring.project.dto.TimeDTO;

public class TimeArray {
	
	private TimeDTO[] timeArr;
	private int curr;
	private int size;
	private int maxSize;
	
	public TimeArray() {
		maxSize = 5;
		timeArr = new TimeDTO[maxSize];
		curr = 0;
		size = 0;
	}
	
	public TimeArray(int size) {
		maxSize = size;
		timeArr = new TimeDTO[maxSize];
		curr = 0;
		this.size = 0;
	}
	public void add(TimeDTO t) {
		timeArr[size++] = t;
	}
	
	public void next() {
		curr = curr + 1;
	}
	
	public boolean hasNext() {
		return curr < size -1;
	}
	
	public TimeDTO getCurr() {
		return timeArr[curr];
	}
	
	@Override
	public String toString() {
		return "크기: " + maxSize;
	}
}
