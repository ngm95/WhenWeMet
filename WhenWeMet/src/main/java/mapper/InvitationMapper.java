package mapper;

import org.apache.ibatis.annotations.Param;

import dto.InvitationDTO;

public interface InvitationMapper {
	public void insert(InvitationDTO dto);
	public void delete(@Param("sender") String sender, @Param("receiver") String receiver);
}
