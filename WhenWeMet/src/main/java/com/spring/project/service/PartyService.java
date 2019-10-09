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
	
	public void create(PartyDTO partyDto) throws Exception {
		partyDao.create(partyDto);
	}
	
	public PartyDTO read(PartyDTO partyDto) throws Exception {
		return partyDao.read(partyDto);
	}
	
	public void update(PartyDTO partyDto) throws Exception {
		partyDao.update(partyDto);
	}
	
	public void delete(PartyDTO partyDto) throws Exception {
		partyDao.delete(partyDto);
	}
	
	public List<PartyDTO> listAll(String uid) throws Exception {
		return partyDao.listAll(uid);
	}
	
	public List<String> listByPid(int pid) throws Exception {
		return partyDao.listByPid(pid);
	}
}
