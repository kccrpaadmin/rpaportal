package com.kcc.biz.dao;

import org.springframework.stereotype.Repository;
import com.kcc.biz.model.LoginVO;;

@Repository("loginRepository")
public interface LoginRepository {
	LoginVO getLoginInfo(LoginVO vo) throws Exception;
}
