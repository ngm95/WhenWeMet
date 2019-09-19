package com.spring.project.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.project.dto.MeetingDTO;

@Repository
public class MeetingDAO{
	private static final String NAMESPACE = "com.spring.project.mappers.sql.MeetingMapper";
	
	@Inject
	SqlSession sqlSession;
	
	public void create(MeetingDTO meetingDto) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", meetingDto);
	}
	
	public MeetingDTO read(Integer mid) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".read", mid); 
	}
	
	public void update(MeetingDTO meetingDto) throws Exception {
		sqlSession.update(NAMESPACE + ".update", meetingDto);
	}
	
	public void delete(Integer mid) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete", mid); 
	}
	
	public List<MeetingDTO> listAll() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".listAll");
	}
}