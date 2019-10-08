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
	 * index.jsp�뿉�꽌 �쉶�썝媛��엯 踰꾪듉�쓣 �늻瑜대㈃ �룞�옉.
	 * /user/signup/step1.jsp�� 留ㅽ븨�븳�떎.
	 * @return /user/signup/step1.jsp濡� 留ㅽ븨�릺�뒗 ModelAndView
	 */
	@RequestMapping(value="signup/step1")
	public String step1() throws Exception {
		System.out.println("step1");
		return "user/signup/step1";
	}
	
	/**
	 * step1.jsp�뿉�꽌 �떎�쓬 踰꾪듉�쓣 �늻瑜대㈃ �룞�옉.
	 * �솗�씤 踰꾪듉�쓣 �닃���뒗吏� 寃��궗�븯怨� �늻瑜댁� �븡�븯�쑝硫� �씠�쟾 �떒怨꾨줈,
	 * �닃���쑝硫� /user/signup/step2.jsp濡� 留ㅽ븨�븳�떎.
	 * @param agree
	 * @return /user/signup/step2.jsp濡� 留ㅽ븨�릺�뒗 ModelAndView
	 */
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
	
	/**
	 * step2.jsp�뿉�꽌 �솗�씤 踰꾪듉�쓣 �늻瑜대㈃ �룞�옉.
	 * �엯�젰�븳 �궡�슜�씠 �씠�긽 �뾾�뒗吏� �솗�씤�븯怨� �씠�긽�씠 �엳�쑝硫� �씠�쟾 �떒怨꾨줈,
	 * �씠�긽�씠 �뾾�떎硫� �뜲�씠�꽣踰좎씠�뒪�뿉 �깉濡쒖슫 �궗�슜�옄瑜� �벑濡앺븳 �뮘 '/'濡� 由щ떎�씠�젆�듃�븳�떎.
	 * @param regReq 濡쒓렇�씤�뿉 �븘�슂�븳 �젙蹂�
	 * @param bindingResult RegisterRequest媛� �쑀�슚�븳吏� 寃��궗
	 * @return '/'濡� 由щ떎�씠�젆�듃
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
            bindingResult.rejectValue("checkPassword", "noMatch", "�뙣�뒪�썙�뱶媛� �씪移섑븯吏� �븡�뒿�땲�떎.");
            mv.setViewName("/user/signup/step2");
            return mv;
        }
        
        try {
        	ser.register(regReq);
            
        } catch (AlreadyExistingEmailException e) {
        	bindingResult.rejectValue("email", "duplicate", "以묐났�맂 �씠硫붿씪�엯�땲�떎.");
            mv.setViewName("/user/signup/step2");
            return mv;
            
        } catch (AlreadyExistingIdException e) {
        	bindingResult.rejectValue("id", "duplicate", "以묐났�맂 �븘�씠�뵒�엯�땲�떎.");
            mv.setViewName("/user/signup/step2");
            return mv;
        }
        
        mv.setViewName("redirect:/");
        return mv;
    }
}
