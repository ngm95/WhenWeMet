package com.spring.project.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.exception.AlreadyExistingIdException;
import com.spring.project.service.MeetingService;
import com.spring.project.util.MeetingCommand;
import com.spring.project.util.MeetingRequest;

@Controller
@RequestMapping("/meeting")
public class MeetingController {
	
	@Resource(name="meetingService")
	private MeetingService meetingSer;
	
	@RequestMapping(value="/make", method=RequestMethod.GET)
	public ModelAndView makeGET(MeetingCommand meetingCommand) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/meeting/make");
		return mv;
	}
	
	@RequestMapping(value="/make", method=RequestMethod.POST)
	public ModelAndView makePOST(@Valid MeetingCommand meetingCommand, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			mv.setViewName("/meeting/makeForm");
			return mv;
		}
		
		MeetingRequest meRe = new MeetingRequest(meetingCommand.getMname(), meetingCommand.getCreator());
		
		try {
			meetingSer.create(meRe);
		} catch(AlreadyExistingIdException e) {
			bindingResult.rejectValue("mname", "duplicate", "중복된 일정 이름입니다.");
			mv.setViewName("/meeting/makeForm");
			return mv;
		} catch(Exception e) {
			bindingResult.reject("데이터베이스 에러입니다.");
			mv.setViewName("/meeting/makeForm");
			return mv;
		}
		
		mv.setViewName("redirect:/");
		return mv;
	}
}
