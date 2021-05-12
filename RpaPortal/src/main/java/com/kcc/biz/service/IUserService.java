package com.kcc.biz.service;

import com.kcc.biz.model.UserVO;

public interface IUserService {
	UserVO getUserInfo(UserVO vo) throws Exception;
}