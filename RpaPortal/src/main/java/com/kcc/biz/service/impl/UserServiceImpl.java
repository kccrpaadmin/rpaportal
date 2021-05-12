package com.kcc.biz.service.impl;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.UserRepository;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource(name="userRepository")
	private UserRepository userRepository;
	
	public UserVO getUserInfo(UserVO vo) throws Exception {
		return userRepository.getUserInfo(vo);
	}
}
