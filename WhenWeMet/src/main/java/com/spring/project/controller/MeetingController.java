package com.spring.project.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.dto.MeetingDTO;
import com.spring.project.dto.PartyDTO;
import com.spring.project.exception.AlreadyExistingIdException;
import com.spring.project.service.MeetingService;
import com.spring.project.service.PartyService;
import com.spring.project.util.MeetingCommand;
import com.spring.project.util.MeetingRequest;

@Controller
@RequestMapping("/meeting")
public class MeetingController {
	@Autowired
	PartyService psvc;
	
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
			bindingResult.rejectValue("mname", "duplicate", "중복되는 이름입니다.");
			mv.setViewName("/meeting/makeForm");
			return mv;
		} 
		
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/list")
	public void meetingList(@RequestParam("userId") String userId, Model model) throws Exception {
		List<PartyDTO> list = psvc.listAll(userId);
		List<MeetingDTO> meetingList = new LinkedList<MeetingDTO>();
		for(PartyDTO dto: list) {
			int mid = dto.getPid();
			MeetingDTO meetingDTO = meetingSer.readByMid(mid);
			meetingList.add(meetingDTO);
		}
		model.addAttribute("meetingList", meetingList);
	}
	
	@PostMapping("/meetingInfo")
	public void meetingInfo(@RequestParam("mid") int mid, Model model) {
		MeetingDTO dto = meetingSer.readByMid(mid);
		model.addAttribute("meeting", dto);
	}
	
	@GetMapping("/{mid}")
	@ResponseBody
	public ResponseEntity<List<String>> getUserListInMeeting(@PathVariable("mid") int mid) throws Exception {
		return new ResponseEntity<List<String>>(psvc.listByPid(mid), HttpStatus.OK);
	}
	
	@GetMapping(value="/name/{mid}", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<String> getNameOfMeeting(@PathVariable("mid") int mid) throws Exception {
		MeetingDTO dto = meetingSer.readByMid(mid);
		String mname = dto.getMname();
		return new ResponseEntity<String>(mname, HttpStatus.OK);
	}
}