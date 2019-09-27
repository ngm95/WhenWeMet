package com.spring.project.util;

public class MeetingRequest {
	private String mname;
	private String creator;
	
	public MeetingRequest(String mname, String creator) {
		this.mname = mname;
		this.creator = creator;
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
}
