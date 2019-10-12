package com.spring.project.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.project.dto.UserDTO;
import com.spring.project.util.RegisterRequest;

@Repository
public class UserDAO {
	private static final String NAMESPACE = "com.spring.project.mappers.sql.UserMapper";
	
	@Inject
	SqlSession sqlSession;
	
	public void create(UserDTO userDto) {
		sqlSession.insert(NAMESPACE + ".create", userDto);
	}
	
	public void insertUser(RegisterRequest regReq)  {
		sqlSession.insert(NAMESPACE + ".register", regReq);
	}
	
	public UserDTO readByEmail(String email) {
		return (UserDTO)sqlSession.selectOne(NAMESPACE + ".readByEmail", email);
	}

	public UserDTO readById(String userid) {
		return (UserDTO)sqlSession.selectOne(NAMESPACE + ".readById", userid);
	}

	public void update(UserDTO userDto) {
		sqlSession.update(NAMESPACE + ".update", userDto);
	}
	
	public void delete(String userid) {
		sqlSession.delete(NAMESPACE + ".delete", userid); 
	}
	
	public List<UserDTO> listAll() {
		return sqlSession.selectList(NAMESPACE + ".listAll");
	}
}