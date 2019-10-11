package com.spring.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.exception.AlreadyExistingEmailException;
import com.spring.project.exception.AlreadyExistingIdException;
import com.spring.project.service.SignUpService;
import com.spring.project.util.RegisterRequest;


@Controller
@RequestMapping("/user")
public class SignUpController {
	
	@Autowired
	private SignUpService ser;
	
	/**
	 * index.jsp에서 회원가입 버튼을 누르면 동작.
	 * /user/signup/step1.jsp와 매핑한다.
	 * @return /user/signup/step1.jsp로 매핑되는 ModelAndView
	 */
	@RequestMapping(value="signup/step1")
	public String step1() throws Exception {
		return "/user/signup/step1";
	}
	
	/**
	 * step1.jsp에서 다음 버튼을 누르면 동작.
	 * 확인 버튼을 눌렀는지 검사하고 누르지 않았으면 이전 단계로,
	 * 눌렀으면 /user/signup/step2.jsp로 매핑한다.
	 * @param agree
	 * @return /user/signup/step2.jsp로 매핑되는 ModelAndView
	 */
	@RequestMapping(value="signup/step2", method=RequestMethod.GET)
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
	
	/**
	 * step2.jsp에서 확인 버튼을 누르면 동작.
	 * 입력한 내용이 이상 없는지 확인하고 이상이 있으면 이전 단계로,
	 * 이상이 없다면 데이터베이스에 새로운 사용자를 등록한 뒤 '/'로 리다이렉트한다.
	 * @param regReq 로그인에 필요한 정보
	 * @param bindingResult RegisterRequest가 유효한지 검사
	 * @return '/'로 리다이렉트
	 */
    @RequestMapping(value="signup/step3", method=RequestMethod.POST)
    public ModelAndView step3(@Valid RegisterRequest regReq, BindingResult bindingResult) throws Exception{
    	
        ModelAndView mv = new ModelAndView();
        
        if(bindingResult.hasErrors()) {
        	mv.setViewName("/user/signup/step2");
            return mv;
        }
        
        boolean check = regReq.isPwEqualToCheckPw();
        if(!check) {
            bindingResult.rejectValue("checkPassword", "noMatch", "패스워드가 일치하지 않습니다.");
            mv.setViewName("/user/signup/step2");
            return mv;
        }
        
        try {
        	ser.register(regReq);
            
        } catch (AlreadyExistingEmailException e) {
        	bindingResult.rejectValue("email", "duplicate", "중복된 이메일입니다.");
            mv.setViewName("/user/signup/step2");
            return mv;
            
        } catch (AlreadyExistingIdException e) {
        	bindingResult.rejectValue("id", "duplicate", "중복된 아이디입니다.");
            mv.setViewName("/user/signup/step2");
            return mv;
        }
        
        mv.setViewName("redirect:/");
        return mv;
    }
}
