package com.spring.project.security;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@RequestMapping(value="/loginPage")
	public String loginPage() throws Exception {
		return "/login/loginPage";
	}
	
	@RequestMapping(value="/loginProcess", method=RequestMethod.POST)
	public ModelAndView loginPOST(HttpSession session, @RequestParam("loginID") String loginID, @RequestParam("loginPW") String loginPW) throws Exception {
		ModelAndView mv = new ModelAndView();
		session.setAttribute("loginID", loginID);
		System.out.println("loginID:" + loginID);
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/";
	}
}
