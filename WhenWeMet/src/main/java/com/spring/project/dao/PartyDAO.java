package com.spring.project.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.project.dto.PartyDTO;

@Repository
public class PartyDAO {
	private static final String NAMESPACE = "com.spring.project.mappers.sql.PartyMapper";
	
	@Inject
	SqlSession sqlSession;
	
	public void create(PartyDTO partyDto) {
		sqlSession.insert(NAMESPACE + ".create", partyDto);
	}
	
	public PartyDTO read(PartyDTO partyDto) {
		return sqlSession.selectOne(NAMESPACE + ".read", partyDto);
	}
	
	public void update(PartyDTO partyDto) {
		
	}
	
	public void delete(PartyDTO partyDto) {
		sqlSession.delete(NAMESPACE + ".delete", partyDto);
	}
	
	public List<PartyDTO> listAll(String uid) {
		return sqlSession.selectList(NAMESPACE + ".listAll", uid);
	}
	
	public List<String> listByPid(int pid) {
		return sqlSession.selectList(NAMESPACE + ".listByPid", pid);
	}
}
