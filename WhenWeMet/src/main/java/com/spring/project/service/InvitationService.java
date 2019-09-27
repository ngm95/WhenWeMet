package com.spring.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.dao.InvitationDAO;
import com.spring.project.dto.InvitationDTO;
import com.spring.project.dto.PartyDTO;

@Service
public class InvitationService {
	@Inject
	InvitationDAO mapper;
	
	@Inject
	PartyService partysvc;
	
	public void invite(int mid, String sender, String receiver) throws Exception {
		InvitationDTO dto = new InvitationDTO();
		dto.setMid(mid);
		dto.setSender(sender);
		dto.setReceiver(receiver);
		mapper.insert(dto);
		List<PartyDTO> list = partysvc.listAll(sender);
		PartyDTO partyDTO = new PartyDTO();
		partyDTO.setPid(mid);
		partyDTO.setUid(sender);
		if(!list.contains(partyDTO))
			partysvc.create(partyDTO);
	}
	
	public void accept(int mid, String accepter, String sender) throws Exception {
		InvitationDTO dto = new InvitationDTO();
		dto.setMid(mid);
		dto.setSender(accepter);
		dto.setReceiver(sender);
		mapper.insert(dto);
		List<PartyDTO> list = partysvc.listAll(accepter);
		PartyDTO partyDTO = new PartyDTO();
		partyDTO.setPid(mid);
		partyDTO.setUid(accepter);
		if(!list.contains(partyDTO))
			partysvc.create(partyDTO);
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
