package com.spring.project.security;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class LoginController {
	
	@RequestMapping(value="/login/loginPage")
	public String loginPage() throws Exception {
		return "/user/login/loginPage";
	}
}
