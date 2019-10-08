package com.spring.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.util.MeetingCommand;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home(Model model, @ModelAttribute("meetingCommand") MeetingCommand meetingCommand) {
		
		return "index";
	}
	
	@RequestMapping(value="/access_denied_page")
	public String accessDeniedPage() throws Exception {
		return "/access_denied_page";
	}
	
    @RequestMapping(value="/access_denied")
    public ModelAndView accessDenied() throws Exception {
    	ModelAndView mv = new ModelAndView("/goIndex");
    	mv.addObject("msg", "접근 권한이 없습니다.");
    	mv.addObject("url", "/");
    	return mv;
    }
	
	@GetMapping("/invitation/index") 
	public void invitationIndex() {
		
	}
	
}
