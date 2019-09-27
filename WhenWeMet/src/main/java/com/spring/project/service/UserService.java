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
public class UserService {
	
	private UserDAO userDao;
	
	@Inject
	public UserService(UserDAO userDao) {
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
	 * 회원가입을 시도한다.
	 * 이메일이나 아이디가 중복되면 예외를 던지고 회원가입을 허가하지 않는다.
	 * @param regReq 회원가입 정보
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
	 * 로그인된 사람의 정보를 반환한다.
	 * @param loginCommand 로그인 정보
	 * @return 로그인한 사람의 ID, 이름, 직책을 담은 AuthInfo
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
