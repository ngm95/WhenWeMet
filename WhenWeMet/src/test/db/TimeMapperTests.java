import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import repository.TimeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
@Log4j
public class TimeMapperTests {
	@Inject
	private TimeMapper mapper;
	
	@Test
	public void testGetTime() {
		log.info(mapper.getClass().getName());
		log.info(mapper.getTime());
	}
}
