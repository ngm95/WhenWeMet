package com.spring.project.security;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class LoginController {
	
	@RequestMapping(value="/loginPage")
	public String loginPage() throws Exception {
		return "/user/loginPage";
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/";
	}
}
