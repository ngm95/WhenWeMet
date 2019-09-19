package com.spring.project.dto;

public class UserDTO {
	private String userid; 
	private String password;
	private String uname;
	private String email;
	private Integer role;
	public boolean matchPassword(String password) {
	    	return this.password.equals(password);
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public String toString() {
		return "userid=" + userid + ", password=" + password + ", uname="
				+ uname + ", email=" + email + ", role=" + role;
	}
}
