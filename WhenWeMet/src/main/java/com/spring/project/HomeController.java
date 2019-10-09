package com.spring.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.project.dto.MeetingDTO;
import com.spring.project.service.MeetingService;
import com.spring.project.util.MeetingCommand;

@Controller
public class HomeController {
	@Autowired
	MeetingService msvc;
	
	@RequestMapping("/")
	public String home(Model model, @ModelAttribute("meetingCommand") MeetingCommand meetingCommand) {
		
		return "index";
	}
	
	@GetMapping("/invitation/index") 
	public void invitationIndex() {
		
	}
	
	@PostMapping("/schedule/index")
	public void scheduleIndex(@RequestParam("mid") int mid, Model model) {
		MeetingDTO dto = msvc.readByMid(mid);
		model.addAttribute("meeting", dto);
	}
	
}
