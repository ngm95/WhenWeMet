package com.spring.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.dao.InvitationDAO;
import com.spring.project.dto.InvitationDTO;

@Service
public class InvitationService {
	@Inject
	InvitationDAO mapper;
	
	public void invite(int mid, String sender, String receiver) {
		InvitationDTO dto = new InvitationDTO();
		dto.setMid(mid);
		dto.setSender(sender);
		dto.setReceiver(receiver);
		mapper.insert(dto);
	}
	
	public void accept(int mid, String sender, String accepter) {
		InvitationDTO dto = new InvitationDTO();
		dto.setMid(mid);
		dto.setSender(accepter);
		dto.setReceiver(sender);
		mapper.insert(dto);
	}
	
	public void reject(int mid, String sender, String receiver) {
		InvitationDTO dto = new InvitationDTO();
		dto.setMid(mid);
		dto.setSender(sender);
		dto.setReceiver(receiver);
		mapper.delete(dto);
	}
	
	//나를 초대한 사람 목록 확인
	public List<InvitationDTO> getInvitationList(String userid) {
		return mapper.getInvitationList(userid);
	}
	
	//같은 모임에 있는 사용자 확인
	public List<String> getUserByMid(int mid) {
		return mapper.getUserListByMid(mid);
	}
}
