package com.spring.project.util;

public class AuthInfo {
	
    private String id;
    private String name;
    private int role;
    
    public AuthInfo(String id, String name, int role) {
    	this.id = id;
    	this.name = name;
    	this.role = role;
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}

}
