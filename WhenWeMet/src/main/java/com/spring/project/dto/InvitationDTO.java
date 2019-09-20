package com.spring.project.dto;

import lombok.Data;

@Data
public class InvitationDTO {
	private String sender;
	private String receiver;
	private int mid;
}
