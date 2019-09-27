package com.spring.project.util;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class MeetingCommand {
	
	@NotEmpty(message="���ο� ���� �̸��� �Է����ּ���.")
	private String mname;
	private String creator;
	
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
