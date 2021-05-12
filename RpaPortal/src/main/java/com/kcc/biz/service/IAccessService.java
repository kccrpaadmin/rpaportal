package com.kcc.biz.service;

import com.kcc.biz.model.AccessVO;

public interface IAccessService {
	void createAccess(AccessVO vo) throws Exception;
	void createAccessFail(AccessVO vo) throws Exception;
}
