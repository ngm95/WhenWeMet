package com.spring.project.DAOTest;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.project.dao.MeetingDAO;
import com.spring.project.dto.MeetingDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MeetingDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(MeetingDAOTest.class);
	
	@Inject
	private MeetingDAO meetingDao;
	
	@Test
	public void testCreate() throws Exception {
		MeetingDTO meetingDto = new MeetingDTO();
		meetingDto.setMname("테스트 이름");
		meetingDto.setCreator("testid2");
		
		meetingDao.create(meetingDto);
	}
	
	@Test
	public void testRead() throws Exception {
		logger.info(meetingDao.read(3).toString());
	}
	
	@Test
	public void testUpdate() throws Exception {
		MeetingDTO meetingDto = new MeetingDTO();
		meetingDto.setMid(3);
		meetingDto.setMname("테스트 이름 업데이트");
		meetingDto.setCreator("testid2");
		
		meetingDao.update(meetingDto);
	}
	
	@Test
	public void testDelete() throws Exception {
		meetingDao.delete(3);
	}
}
