package com.spring.project.controller;

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
import com.spring.project.service.InvitationService;

@RestController
@RequestMapping("/invitation")
public class InvitationController {
	@Autowired
	InvitationService svc;
	
	@GetMapping("/list/{userId}")
	public ResponseEntity<List<InvitationDTO>> getList(@PathVariable("userId") String userId) {
		List<InvitationDTO> list = svc.getInvitationList(userId);
		return new ResponseEntity<List<InvitationDTO>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/invite/{mid}/{userId}/{receiver}") 
	public ResponseEntity<Void> invite(@PathVariable("mid") int mid, @PathVariable("userId") String userId, @PathVariable("receiver") String receiver) {
		svc.invite(mid, userId, receiver);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/accept/{mid}/{userId}/{sender}")
	public ResponseEntity<Void> accept(@PathVariable("mid") int mid, @PathVariable("userId") String userId, @PathVariable("sender") String sender) {
		svc.accept(mid, sender, userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/reject/{mid}/{userId}/{sender}")
	public ResponseEntity<Void> delete(@PathVariable("mid") int mid, @PathVariable("userId") String userId, @PathVariable("sender") String sender) {
		svc.reject(mid, sender, userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/mid/{mid}")
	public ResponseEntity<List<String>> getUserByMid(@PathVariable("mid") int mid) {
		return new ResponseEntity<List<String>>(svc.getUserByMid(mid), HttpStatus.OK);
	}
}
