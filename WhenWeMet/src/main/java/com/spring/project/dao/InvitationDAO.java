package com.spring.project.dao;

import org.apache.ibatis.annotations.Param;

import com.spring.project.dto.InvitationDTO;

public interface InvitationDAO {
	public void insert(InvitationDTO dto);
	public void delete(@Param("sender") String sender, @Param("receiver") String receiver);
}
