package com.spring.project.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.project.dto.MeetingDTO;
import com.spring.project.util.MeetingRequest;

@Repository
public class MeetingDAO{
	private static final String NAMESPACE = "com.spring.project.mappers.sql.MeetingMapper";
	
	@Inject
	SqlSession sqlSession;
	
	public void create(MeetingDTO meetingDto) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", meetingDto);
	}
	
	public void create(MeetingRequest meRe) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", meRe);
	}
	
	public MeetingDTO readByName(String mname) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".readByName", mname); 
	}
	
	public MeetingDTO readByCreator(String creator) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".readByCreator", creator); 
	}
	
	public MeetingDTO readUniqueMeeting(String mname, String creator) throws Exception {
		MeetingDTO meetingDto = new MeetingDTO();
		meetingDto.setMname(mname);
		meetingDto.setCreator(creator);
		return sqlSession.selectOne(NAMESPACE + ".readUniqueMeeting", meetingDto); 
	}
	
	public void update(MeetingDTO meetingDto) throws Exception {
		sqlSession.update(NAMESPACE + ".update", meetingDto);
	}
	
	public void delete(Integer mid) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete", mid); 
	}
	
	public List<MeetingDTO> listAll(String userid) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".listAll", userid);
	}
}