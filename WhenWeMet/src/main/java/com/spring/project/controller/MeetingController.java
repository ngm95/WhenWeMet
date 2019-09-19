//package com.spring.project.controller;
//
//import javax.inject.Inject;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.spring.project.service.MeetingServiceImpl;
//
//@Controller
//@RequestMapping("/meeting")
//public class MeetingController {
//	private final MeetingServiceImpl meetingService;
//	
//	@Inject
//	public MeetingController(MeetingServiceImpl meetingService) {
//		this.meetingService = meetingService;
//	}
//	
//	@RequestMapping("/make")
//	public String make() {
//		return "/meeting/make";
//	}
//}
