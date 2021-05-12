package com.kcc.biz.dao;

import org.springframework.stereotype.Repository;
import com.kcc.biz.model.AccessVO;

@Repository("accessRepository")
public interface AccessRepository {
	void createAccess(AccessVO vo) throws Exception;
	void createAccessFail(AccessVO vo) throws Exception;
}
