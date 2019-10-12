import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.project.dao.InvitationDAO;
import com.spring.project.dao.PartyDAO;
import com.spring.project.dao.ScheduleDAO;
import com.spring.project.dao.UserDAO;
import com.spring.project.dto.ScheduleDTO;
import com.spring.project.dto.UserDTO;
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
	
	@Autowired
	PartyDAO pdao;
	
	@Autowired
	UserDAO udao;
	@Test
	public void insertTest() throws Exception {
		List<String> userList = new LinkedList<>();
		userList.add("admin");
		userList.add("user00");
		svc.getAvailableTime(userList, 7).forEach(r -> System.out.println(r));
	}
}
