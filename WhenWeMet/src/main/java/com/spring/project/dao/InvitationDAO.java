package com.spring.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.project.dto.InvitationDTO;

public interface InvitationDAO {
	public void insert(InvitationDTO dto);
	public void delete(InvitationDTO dto);
	public List<InvitationDTO> getInvitationList(@Param("userid") String userid);
}
