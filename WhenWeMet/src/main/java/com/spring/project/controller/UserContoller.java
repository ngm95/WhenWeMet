package com.spring.project.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.exception.AlreadyExistingEmailException;
import com.spring.project.exception.AlreadyExistingIdException;
import com.spring.project.exception.IdPasswordNotMatchingException;
import com.spring.project.service.UserService;
import com.spring.project.util.AuthInfo;
import com.spring.project.util.LoginCommand;
import com.spring.project.util.RegisterRequest;


@Controller
@RequestMapping("/user")
public class UserContoller {
	
	@Resource(name="userService")
	private UserService userSer;

	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public ModelAndView signinGET(LoginCommand loginCommand,
			@CookieValue(value="REMEMBER", required=false) Cookie rememberCookie) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(rememberCookie != null) {
			loginCommand.setId(rememberCookie.getValue());
			loginCommand.setRememberId(true);
		}
		
		mv.setViewName("/user/loginForm");
		return mv;
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public ModelAndView signinPOST(@Valid LoginCommand loginCommand, BindingResult bindingResult,
			HttpSession session, HttpServletResponse response) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			mv.setViewName("/user/loginForm");
			return mv;
		}
		
		try {
			AuthInfo authInfo = userSer.loginAuth(loginCommand);
			session.setAttribute("authInfo", authInfo);
			
			Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getId());
			rememberCookie.setPath("/");
			
			if(loginCommand.isRememberId()) {
                rememberCookie.setMaxAge(60*60*24*7);
            } else {
                rememberCookie.setMaxAge(0);
            }
            response.addCookie(rememberCookie);
			
		} catch(IdPasswordNotMatchingException e) {
			bindingResult.rejectValue("password", "notMatch", "아이디와 비밀번호가 맞지않습니다.");
			mv.setViewName("/user/loginForm");
			return mv;
			
		}
		
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@RequestMapping(value="/signout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="signup/step1")
	public String step1() throws Exception {
		return "user/signup/step1";
	}
	
	@RequestMapping(value="signup/step2", method=RequestMethod.POST)
	public ModelAndView step2(@RequestParam(value="agree", defaultValue="false") Boolean agree) throws Exception {

		ModelAndView mv = new ModelAndView();
		
		if(!agree) {
			mv.setViewName("/user/signup/step1");
			return mv;
		}
		
		mv.setViewName("/user/signup/step2");
		mv.addObject("registerRequest", new RegisterRequest());
		return mv;
		
	}
	
    @RequestMapping(value="signup/step3", method=RequestMethod.POST)
    public ModelAndView step3(@Valid RegisterRequest regReq, BindingResult bindingResult) throws Exception{
    	
        ModelAndView mv = new ModelAndView();
        
        if(bindingResult.hasErrors()) {
        	mv.setViewName("/user/signup/step2");
            return mv;
        }
        
        boolean check = regReq.isPwEqualToCheckPw();
        if(!check) {
            bindingResult.rejectValue("checkPassword", "noMatch", "비밀번호를 확인해주세요.");
            mv.setViewName("/user/signup/step2");
            return mv;
        }
        
        try {
            userSer.register(regReq);
            
        } catch (AlreadyExistingEmailException e) {
        	bindingResult.rejectValue("email", "duplicate", "이미 가입된 이메일입니다.");
            mv.setViewName("/user/signup/step2");
            return mv;
            
        } catch (AlreadyExistingIdException e) {
        	bindingResult.rejectValue("id", "duplicate", "이미 가입된 아이디입니다.");
            mv.setViewName("/user/signup/step2");
            return mv;
        }
        
        mv.setViewName("redirect:/");
        return mv;
    }
}
