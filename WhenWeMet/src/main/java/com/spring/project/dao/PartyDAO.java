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
	
	public void create(PartyDTO partyDto) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", partyDto);
	}
	
	public PartyDTO read(PartyDTO partyDto) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".read", partyDto);
	}
	
	public void update(PartyDTO partyDto) throws Exception {
		
	}
	
	public void delete(PartyDTO partyDto) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete", partyDto);
	}
	
	public List<PartyDTO> listAll() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".listAll");
	}
}
