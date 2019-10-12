package com.spring.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.dao.PartyDAO;
import com.spring.project.dto.PartyDTO;

@Service
public class PartyService {

	private PartyDAO partyDao;
	
	@Inject
	public PartyService(PartyDAO partyDao) {
		this.partyDao = partyDao;
	}
	
	public void create(PartyDTO partyDto) {
		partyDao.create(partyDto);
	}
	
	public PartyDTO read(PartyDTO partyDto) {
		return partyDao.read(partyDto);
	}
	
	public void update(PartyDTO partyDto) {
		partyDao.update(partyDto);
	}
	
	public void delete(PartyDTO partyDto) {
		partyDao.delete(partyDto);
	}
	
	public List<PartyDTO> listAll(String uid) {
		return partyDao.listAll(uid);
	}
	
	public List<String> listByPid(int pid) {
		return partyDao.listByPid(pid);
	}

}
