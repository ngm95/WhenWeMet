package com.spring.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.dao.MeetingDAO;
import com.spring.project.dto.MeetingDTO;
import com.spring.project.exception.AlreadyExistingMnameException;
import com.spring.project.exception.NotExistingMnameException;
import com.spring.project.util.MeetingCommand;

@Service
public class MeetingService {
	private MeetingDAO meetingDao;
	
	@Inject
	public MeetingService(MeetingDAO meetingDao) {
		this.meetingDao = meetingDao;
	}
	
	public void create(MeetingDTO meetingDto){
		meetingDao.create(meetingDto);
	}
	
	public void create(MeetingCommand meCo) throws AlreadyExistingMnameException {
		MeetingDTO unique = meetingDao.readUniqueMeeting(meCo.getMname(), meCo.getCreator());
		if (unique != null) {
			throw new AlreadyExistingMnameException("이미 존재하는 이름입니다.");
		}
		
		meetingDao.create(meCo);
	}
	
	public MeetingDTO readByName(String mname) {
		return meetingDao.readByName(mname);
	}
	
	public MeetingDTO readByCreator(String creator) {
		return meetingDao.readByCreator(creator);
	}
	
	public MeetingDTO readByMid(int mid) {
		return meetingDao.readByMid(mid);
	}
	
	public void update(MeetingDTO meetingDto) {
		meetingDao.update(meetingDto);
	}
	
	public void delete(Integer mid) throws Exception {
		meetingDao.delete(mid);
	}
	
	public List<MeetingDTO> listAll(String creator) {
		return meetingDao.listAll(creator);
	}
	
	public MeetingDTO meetingInfo(String mname, String creator) throws Exception {
		MeetingDTO meeting = meetingDao.readUniqueMeeting(mname, creator);
		if (meeting == null) {
			throw new NotExistingMnameException("해당 이름의 모임이 존재하지 않습니다.");
		}
		
		return meeting;
	}
}
