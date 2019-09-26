package com.spring.project.dto;

public class PartyDTO {
	private Integer pid;
	private String uid;
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "pid=" + pid + ", uid=" + uid;
	}
}
