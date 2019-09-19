package com.spring.project.dto;

public class PartyDTO {
	private Integer mid;
	private String userid;
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "mid=" + mid + ", userid=" + userid;
	}
}
