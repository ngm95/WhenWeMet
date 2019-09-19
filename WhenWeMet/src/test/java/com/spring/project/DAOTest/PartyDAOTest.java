package com.spring.project.DAOTest;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.project.dao.PartyDAO;
import com.spring.project.dto.PartyDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PartyDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(PartyDAOTest.class);
	
	@Inject
	private PartyDAO PartyDao;
	
	@Test
	public void testCreate() throws Exception {
		PartyDTO partyDto = new PartyDTO();
		partyDto.setMid(1);
		partyDto.setUserid("testid");
		
		PartyDao.create(partyDto);
	}
	
	@Test
	public void testRead() throws Exception {
		PartyDTO partyDto = new PartyDTO();
		partyDto.setMid(1);
		partyDto.setUserid("testid");
		
		logger.info(PartyDao.read(partyDto).toString());
	}
	
	@Test
	public void testUpdate() throws Exception {
		
	}
	
	@Test
	public void testDelete() throws Exception {
		PartyDTO partyDto = new PartyDTO();
		partyDto.setMid(1);
		partyDto.setUserid("testid");
		
		PartyDao.delete(partyDto);
	}
}
