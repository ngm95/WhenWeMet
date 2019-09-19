import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dto.ScheduleDTO;
import lombok.extern.log4j.Log4j;
import mapper.ScheduleMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
@Log4j
public class ScheduleMapperTest {
	@Inject
	ScheduleMapper mapper;
	
	/*@Test
	public void insertTest() {
		ScheduleDTO dto = new ScheduleDTO();
		dto.setSname("테스트일정");
		dto.setUserid("user00");
		Calendar c = Calendar.getInstance();
		c.set(2019, 0, 0, 0, 0, 0);
		dto.setStart_time(c.getTime());
		Date d = new Date();
		dto.setEnd_time(d);
		mapper.insert(dto);
		
	}*/
	
	/*@Test
	public void deleteTest() {
		mapper.delete(1);
	}*/
	
	/*@Test
	public void selectTest() {
		List<ScheduleDTO> list = mapper.select("user00");
		list.forEach(data -> log.info(data));
	}*/
	
	/*@Test
	public void updateTest() {
		ScheduleDTO dto = new ScheduleDTO();
		dto.setSname("수정테스트일정2");
		LocalDateTime t = LocalDateTime.of(2019, 9, 1, 0, 0);
		dto.setStart_time(t);
		t = LocalDateTime.now();
		dto.setEnd_time(t);
		dto.setSid(10);
		mapper.update(dto);
		
	}*/
}
