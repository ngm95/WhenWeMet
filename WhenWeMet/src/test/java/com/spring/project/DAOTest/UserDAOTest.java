package com.spring.project.DAOTest;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.project.dao.UserDAO;
import com.spring.project.dto.UserDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(UserDAOTest.class);
	
	@Inject
	private UserDAO userDao;
	
	
	
	@Test
	public void testUpdate() throws Exception {
		UserDTO userDto = new UserDTO();
		userDto.setUserid("testid2");
		userDto.setPassword("testpw");
		userDto.setUname("테스트 이름");
		userDto.setEmail("테스트 이메일");
		userDto.setRole(1);
		userDao.update(userDto);
	}
}
