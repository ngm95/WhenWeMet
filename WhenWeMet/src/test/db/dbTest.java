import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.project.dao.InvitationDAO;
import com.spring.project.dao.ScheduleDAO;
import com.spring.project.dto.ScheduleDTO;
import com.spring.project.service.InvitationService;
import com.spring.project.service.PartyService;
import com.spring.project.service.ScheduleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class dbTest {
	@Inject
	ScheduleService svc;
	
	@Inject
	ScheduleDAO dao;
	
	@Inject
	InvitationService isvc;
	
	@Inject
	PartyService pvc;
	@Test
	public void insertTest() throws Exception {
		isvc.invite(1, "user00", "admin");
		isvc.invite(1, "user01", "admin");
		isvc.invite(1, "user02", "admin");
	}
}
