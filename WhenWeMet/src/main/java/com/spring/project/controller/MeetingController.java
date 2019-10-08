package com.spring.project.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
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
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class MeetingController {
	
	@Resource(name="meetingService")
	private MeetingService meetingSer;
	
	/**
	 * index.jsp에서 새로운 일정 만들기 버튼을 클릭하면 동작.
	 * 정상적으로 로그인하지 않았다면 '/'로 리다이렉트,
	 * 정상적이라면 /meeting/makeForm.jsp로 매핑한다.
	 * @param /meeting/makeForm.jsp로 매핑되는 ModelAndView
	 */
	@RequestMapping(value="/make", method=RequestMethod.GET)
	public ModelAndView makeGET(MeetingCommand meetingCommand, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/meeting/makeForm");
		return mv;
	}
	
	/**
	 * makeForm.jsp에서 확인 버튼을 누르면 동작.
	 * 입력한 내용에 이상이 있으면 이전 단계로, 이상이 없으면 '/'로 리다이렉트한다.
	 * @param meetingCommand 입력받은 정보
	 * @param bindingResult meetingCommand가 유효한지 검사
	 * @return '/'로 리다이렉트
	 * @throws Exception 
	 */
	@RequestMapping(value="/makeProcess", method=RequestMethod.POST)
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
			bindingResult.rejectValue("mname", "duplicate", "以묐났�릺�뒗 �씠由꾩엯�땲�떎.");
			mv.setViewName("/meeting/makeForm");
			return mv;
		} 
		
		mv.setViewName("redirect:/");
		return mv;
	}
}
