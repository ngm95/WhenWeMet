package com.spring.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.dao.UserDAO;
import com.spring.project.dto.UserDTO;
import com.spring.project.exception.AlreadyExistingEmailException;
import com.spring.project.exception.AlreadyExistingIdException;
import com.spring.project.util.RegisterRequest;

@Service
public class SignUpService {
	
	private UserDAO userDao;
	
	@Inject
	public SignUpService(UserDAO userDao) {
		this.userDao = userDao;
	}

	public void create(UserDTO userDto) {
		userDao.create(userDto);
	}
	
	public UserDTO readById(String userid) {
		return userDao.readById(userid);
	}
	
	public void update(UserDTO userDto) {
		userDao.update(userDto);
	}
	
	public void delete(String userid) {
		userDao.delete(userid);
	}
	
	public List<UserDTO> listAll() {
		return userDao.listAll();
	}
	
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
}
