import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.project.dao.ScheduleDAO;
import com.spring.project.dto.ScheduleDTO;
import com.spring.project.service.ScheduleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class dbTest {
	@Inject
	ScheduleService svc;
	
	@Inject
	ScheduleDAO dao;
	
	@Test
	public void insertTest() throws Exception {
		List<String> list = new LinkedList<>();
		list.add("user00");
		list.add("user01");
		list.add("user02");
		svc.getAvailableTime(list);
		
		
		
	}
}
