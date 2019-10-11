package com.spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.dto.ScheduleDTO;
import com.spring.project.dto.TimeDTO;
import com.spring.project.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	@Autowired
	ScheduleService svc;
	
	@GetMapping("/list/{userId}/{mid}")
	public ResponseEntity<List<ScheduleDTO>> getSchedule(@PathVariable("userId") String userId, @PathVariable("mid") int mid) {
		return new ResponseEntity<List<ScheduleDTO>>(svc.getScheduleByUser(userId, mid), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Void> addSchedule(@RequestBody ScheduleDTO dto) {
		svc.addSchedule(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/modify") 
	public ResponseEntity<Void> modifySchedule(@RequestBody ScheduleDTO dto) {
		svc.modifySchedule(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{sid}")
	public ResponseEntity<Void> deleteSchedule(@PathVariable("sid") int sid) {
		svc.deleteSchedule(sid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/availableList")
	public ResponseEntity<List<TimeDTO>> getAvailableSchedule(@RequestParam(value="userList[]") List<String> userList, @RequestParam(value="mid") int mid) {
		return new ResponseEntity<List<TimeDTO>>(svc.getAvailableTime(userList, mid), HttpStatus.OK);
	}
}
