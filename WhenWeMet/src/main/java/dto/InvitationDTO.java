package dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class InvitationDTO {
	private String sender;
	private String receiver;
	private int mid;
}
