package com.spring.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.dao.UserDAO;
import com.spring.project.dto.UserDTO;
import com.spring.project.exception.AlreadyExistingEmailException;
import com.spring.project.exception.AlreadyExistingIdException;
import com.spring.project.exception.IdPasswordNotMatchingException;
import com.spring.project.util.AuthInfo;
import com.spring.project.util.LoginCommand;
import com.spring.project.util.RegisterRequest;

@Service
public class SignUpService {
	
	private UserDAO userDao;
	
	@Inject
	public SignUpService(UserDAO userDao) {
		this.userDao = userDao;
	}

	public void create(UserDTO userDto) throws Exception {
		userDao.create(userDto);
	}
	
	public void update(UserDTO userDto) throws Exception {
		userDao.update(userDto);
	}
	
	public void delete(String userid) throws Exception {
		userDao.delete(userid);
	}
	
	public List<UserDTO> listAll() throws Exception {
		return userDao.listAll();
	}
	
	/**
	 * ?šŒ?›ê°??…?„ ?‹œ?„?•œ?‹¤.
	 * ?´ë©”ì¼?´?‚˜ ?•„?´?””ê°? ì¤‘ë³µ?˜ë©? ?˜ˆ?™¸ë¥? ?˜ì§?ê³? ?šŒ?›ê°??…?„ ?—ˆê°??•˜ì§? ?•Š?Š”?‹¤.
	 * @param regReq ?šŒ?›ê°??… ? •ë³?
	 */
	public void register(RegisterRequest regReq) throws Exception {
		
        UserDTO email = userDao.readByEmail(regReq.getEmail());
        if(email!=null) {
            throw new AlreadyExistingEmailException(regReq.getEmail()+" is duplicate email.");
        }
        
        UserDTO id = userDao.readById(regReq.getId());
        if(id!=null) {
            throw new AlreadyExistingIdException(regReq.getId()+" is duplicate id.");
        }
        
        userDao.insertUser(regReq);
	}

	/**
	 * ë¡œê·¸?¸?œ ?‚¬?Œ?˜ ? •ë³´ë?? ë°˜í™˜?•œ?‹¤.
	 * @param loginCommand ë¡œê·¸?¸ ? •ë³?
	 * @return ë¡œê·¸?¸?•œ ?‚¬?Œ?˜ ID, ?´ë¦?, ì§ì±…?„ ?‹´?? AuthInfo
	 */
	public AuthInfo loginAuth(LoginCommand loginCommand) throws Exception {
		
		UserDTO user = userDao.readById(loginCommand.getId());
        if(user == null) {
            throw new IdPasswordNotMatchingException(loginCommand.getId() + "is not valid.");
        }
        
        if(!user.matchPassword(loginCommand.getPassword())) {
            throw new IdPasswordNotMatchingException(loginCommand.getPassword() + "is not valid.");
        }
        
        return new AuthInfo(user.getUserid(), user.getUname(), user.getRole());
	}
}
