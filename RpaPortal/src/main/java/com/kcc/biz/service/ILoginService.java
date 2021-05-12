package com.kcc.biz.service;

import com.kcc.biz.model.LoginVO;

public interface ILoginService {
	LoginVO getLoginInfo(LoginVO vo) throws Exception;
}