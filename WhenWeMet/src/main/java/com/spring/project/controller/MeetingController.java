package com.spring.project.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.exception.AlreadyExistingIdException;
import com.spring.project.service.MeetingService;
import com.spring.project.util.AuthInfo;
import com.spring.project.util.MeetingCommand;
import com.spring.project.util.MeetingRequest;

@Controller
@RequestMapping("/meeting")
public class MeetingController {
	
	@Resource(name="meetingService")
	private MeetingService meetingSer;
	
	/**
	 * index.jsp�뿉�꽌 �깉濡쒖슫 �씪�젙 留뚮뱾湲� 踰꾪듉�쓣 �겢由��븯硫� �룞�옉.
	 * �젙�긽�쟻�쑝濡� 濡쒓렇�씤�븯吏� �븡�븯�떎硫� '/'濡� 由щ떎�씠�젆�듃,
	 * �젙�긽�쟻�씠�씪硫� /meeting/makeForm.jsp濡� 留ㅽ븨�븳�떎.
	 * @param /meeting/makeForm.jsp濡� 留ㅽ븨�릺�뒗 ModelAndView
	 */
	@RequestMapping(value="/make", method=RequestMethod.GET)
	public ModelAndView makeGET(MeetingCommand meetingCommand, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if (authInfo == null) {
			mv.setViewName("redirect:/");
			return mv;
		}
		
		mv.setViewName("/meeting/makeForm");
		return mv;
	}
	
	/**
	 * makeForm.jsp�뿉�꽌 �솗�씤 踰꾪듉�쓣 �늻瑜대㈃ �룞�옉.
	 * �엯�젰�븳 �궡�슜�뿉 �씠�긽�씠 �엳�쑝硫� �씠�쟾 �떒怨꾨줈, �씠�긽�씠 �뾾�쑝硫� '/'濡� 由щ떎�씠�젆�듃�븳�떎.
	 * @param meetingCommand �엯�젰諛쏆� �젙蹂�
	 * @param bindingResult meetingCommand媛� �쑀�슚�븳吏� 寃��궗
	 * @return '/'濡� 由щ떎�씠�젆�듃
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
