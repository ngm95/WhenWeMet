package com.spring.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.dao.MeetingDAO;
import com.spring.project.dto.MeetingDTO;
import com.spring.project.exception.AlreadyExistingMnameException;
import com.spring.project.exception.NotExistingMnameException;
import com.spring.project.util.MeetingRequest;

@Service
public class MeetingService {
	private MeetingDAO meetingDao;
	
	@Inject
	public MeetingService(MeetingDAO meetingDao) {
		this.meetingDao = meetingDao;
	}
	
	public void create(MeetingDTO meetingDto) throws Exception {
		meetingDao.create(meetingDto);
	}
	
	public void create(MeetingRequest meRe) throws Exception {
		MeetingDTO unique = meetingDao.readUniqueMeeting(meRe.getMname(), meRe.getCreator());
		if (unique != null) {
			throw new AlreadyExistingMnameException("이미 존재하는 일정 이름입니다.");
		}
		
		meetingDao.create(meRe);
	}
	
	public MeetingDTO readByName(String mname) throws Exception {
		return meetingDao.readByName(mname);
	}
	
	public MeetingDTO readByCreator(String creator) throws Exception {
		return meetingDao.readByCreator(creator);
	}
	
	public void update(MeetingDTO meetingDto) throws Exception {
		meetingDao.update(meetingDto);
	}
	
	public void delete(Integer mid) throws Exception {
		meetingDao.delete(mid);
	}
	
	public List<MeetingDTO> listAll(String creator) throws Exception {
		return meetingDao.listAll(creator);
	}
	
	public MeetingDTO meetingInfo(String mname, String creator) throws Exception {
		MeetingDTO meeting = meetingDao.readUniqueMeeting(mname, creator);
		if (meeting == null) {
			throw new NotExistingMnameException("이미 존재하는 일정 이름입니다.");
		}
		
		return meeting;
	}
}
