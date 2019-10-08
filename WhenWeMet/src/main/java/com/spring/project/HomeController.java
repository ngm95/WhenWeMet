package com.spring.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.project.util.MeetingCommand;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home(Model model, @ModelAttribute("meetingCommand") MeetingCommand meetingCommand) {
		
		return "index";
	}
	
	@GetMapping("/invitation/index") 
	public void invitationIndex() {
		
	}
	
}
