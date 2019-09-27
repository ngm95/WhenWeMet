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
	
	/**
	 * index.jsp에서 새로운 일정 만들기 버튼을 클릭하면 동작.
	 * /meeting/makeForm.jsp로 매핑한다.
	 * @param /meeting/makeForm.jsp로 매핑되는 ModelAndView
	 */
	@RequestMapping(value="/make", method=RequestMethod.GET)
	public ModelAndView makeGET(MeetingCommand meetingCommand) {
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
	 */
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
			bindingResult.rejectValue("mname", "duplicate", "중복되는 이름입니다.");
			mv.setViewName("/meeting/makeForm");
			return mv;
		} catch(Exception e) {
			bindingResult.reject("데이터베이스 오류가 발생했습니다.");
			mv.setViewName("/meeting/makeForm");
			return mv;
		}
		
		mv.setViewName("redirect:/");
		return mv;
	}
}
