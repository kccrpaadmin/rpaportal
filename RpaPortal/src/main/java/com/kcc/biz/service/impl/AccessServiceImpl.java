package com.kcc.biz.service.impl;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.AccessRepository;
import com.kcc.biz.dao.LoginRepository;
import com.kcc.biz.model.AccessVO;
import com.kcc.biz.model.LoginVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.ILoginService;

@Service("accessService")
public class AccessServiceImpl implements IAccessService {
	private static final Logger logger = LoggerFactory.getLogger(AccessServiceImpl.class);
	
	@Resource(name="accessRepository")
	private AccessRepository accessRepository;
	
	public void createAccess(AccessVO vo) throws Exception {
		accessRepository.createAccess(vo);
	}

	public void createAccessFail(AccessVO vo) throws Exception {
		accessRepository.createAccessFail(vo);
	}
}
