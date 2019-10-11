package com.spring.project.security;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("userAuthDAO")
public class UserAuthDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public CustomUserDetails getUserById(String username) {
		return sqlSession.selectOne("login.selectUserById", username);
	}

	public void updateFailureCount(String username) {
		sqlSession.update("login.updateFailureCount", username);
	}
	
	public int checkFailureCount(String username) {
		return sqlSession.selectOne("login.checkFailureCount", username);
	}
	
	public void updateDisabled(String username) {
		sqlSession.update("login.updateUnenabled", username);
	}

	public void updateFailureCountReset(String username) {
		sqlSession.update("login.updateFailureCountReset", username);
	}

	public void updateNewAccessDate(String username) {
		sqlSession.update("login.updateAccessDate", username);
	}

}
