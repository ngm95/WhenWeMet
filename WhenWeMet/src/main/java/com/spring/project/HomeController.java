package com.spring.project;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.dto.MeetingDTO;
import com.spring.project.dto.UserDTO;
import com.spring.project.service.MeetingService;
import com.spring.project.service.SignUpService;
import com.spring.project.util.AuthInfo;

@Controller
public class HomeController {
	@Autowired
	MeetingService msvc;
	
	@Autowired
	private SignUpService sSer;

	@RequestMapping("/")
	public String home(HttpSession session, Authentication auth) {
		if (auth != null) {
			if (session.getAttribute("authInfo") == null) {
				UserDTO dto = sSer.readById(auth.getName());
				session.setAttribute("authInfo", new AuthInfo(dto.getUserid(), dto.getUname()));
			}
		}
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
	
	@PostMapping("/schedule/index")
	public void scheduleIndex(@RequestParam("mid") int mid, Model model) {
		MeetingDTO dto = msvc.readByMid(mid);
		model.addAttribute("meeting", dto);
	}

}
