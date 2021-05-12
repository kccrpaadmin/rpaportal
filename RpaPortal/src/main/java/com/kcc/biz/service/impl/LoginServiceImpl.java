package com.kcc.biz.service.impl;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.LoginRepository;
import com.kcc.biz.model.LoginVO;
import com.kcc.biz.service.ILoginService;

@Service("loginService")
public class LoginServiceImpl implements ILoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Resource(name="loginRepository")
	private LoginRepository loginRepository;
	
	public LoginVO getLoginInfo(LoginVO vo) throws Exception {
		return loginRepository.getLoginInfo(vo);
	}
}
