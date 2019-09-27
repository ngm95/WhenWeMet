package com.spring.project.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.dto.InvitationDTO;
import com.spring.project.dto.MeetingDTO;
import com.spring.project.dto.PartyDTO;
import com.spring.project.service.InvitationService;
import com.spring.project.service.MeetingService;
import com.spring.project.service.PartyService;

@RestController
@RequestMapping("/invitation")
public class InvitationController {
	@Autowired
	InvitationService svc;
	
	@Autowired
	PartyService psvc;
	
	@Autowired
	MeetingService msvc;
	
	@GetMapping("/list/{userId}")
	public ResponseEntity<List<InvitationDTO>> getList(@PathVariable("userId") String userId) {
		List<InvitationDTO> list = svc.getInvitationList(userId);
		return new ResponseEntity<List<InvitationDTO>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/invite/{mid}/{userId}/{receiver}") 
	public ResponseEntity<Void> invite(@PathVariable("mid") int mid, @PathVariable("userId") String userId, @PathVariable("receiver") String receiver) throws Exception {
		svc.invite(mid, userId, receiver);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/accept/{mid}/{userId}/{sender}")
	public ResponseEntity<Void> accept(@PathVariable("mid") int mid, @PathVariable("userId") String userId, @PathVariable("sender") String sender) throws Exception {
		svc.accept(mid, userId, sender);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/deny/{mid}/{userId}/{sender}")
	public ResponseEntity<Void> delete(@PathVariable("mid") int mid, @PathVariable("userId") String userId, @PathVariable("sender") String sender) {
		svc.reject(mid, sender, userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/mid/{mid}")
	public ResponseEntity<List<String>> getUserByMid(@PathVariable("mid") int mid) {
		return new ResponseEntity<List<String>>(svc.getUserByMid(mid), HttpStatus.OK);
	}
	
	@GetMapping("/group/{userId}")
	public ResponseEntity<List<MeetingDTO>> getMeetingByUserId(@PathVariable("userId") String userId) throws Exception {
		List<MeetingDTO> list = new LinkedList<MeetingDTO>();
		List<PartyDTO> partyList = psvc.listAll(userId);
		for(PartyDTO p : partyList) {
			MeetingDTO dto = msvc.readByMid(p.getPid());
			list.add(dto);
		}
		return new ResponseEntity<List<MeetingDTO>>(list, HttpStatus.OK);
	}
}
