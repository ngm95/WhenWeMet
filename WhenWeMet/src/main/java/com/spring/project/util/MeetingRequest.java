package com.spring.project.util;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

public class MeetingRequest {
	
	@Column
	@Pattern(regexp="\\S{1,20}", message="1~20자로 입력해주세요.")
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
