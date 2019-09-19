package com.spring.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.dao.MeetingDAO;
import com.spring.project.dto.MeetingDTO;

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
	
	public MeetingDTO read(Integer mid) throws Exception {
		return meetingDao.read(mid);
	}
	
	public void update(MeetingDTO meetingDto) throws Exception {
		meetingDao.update(meetingDto);
	}
	
	public void delete(Integer mid) throws Exception {
		meetingDao.delete(mid);
	}
	
	public List<MeetingDTO> listAll() throws Exception {
		return meetingDao.listAll();
	}
}
