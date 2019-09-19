import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dto.InvitationDTO;
import mapper.InvitationMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class InvitationMapperTest {
	@Inject
	InvitationMapper mapper;
	
	/*
	@Test
	public void testInsert() {
		InvitationDTO dto = new InvitationDTO();
		dto.setSender("user00");
		dto.setReceiver("user01");
		dto.setMid(1);
		mapper.insert(dto);
	}
	*/
	@Test
	public void testDelete() {
		String sender = "user00";
		String receiver = "user01";
		mapper.delete(sender, receiver);
	}
}
