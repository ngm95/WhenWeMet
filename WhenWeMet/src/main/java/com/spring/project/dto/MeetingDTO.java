package com.spring.project.dto;

public class MeetingDTO {
	private int mid;
	private String mname;
	private String creator;
	public int getMid() {
		return mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String toString() {
		return "mid=" + mid + ", mname=" + mname + ", creator=" + creator;
	}
}
