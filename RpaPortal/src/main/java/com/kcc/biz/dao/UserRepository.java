package com.kcc.biz.dao;

import org.springframework.stereotype.Repository;
import com.kcc.biz.model.UserVO;;

@Repository("userRepository")
public interface UserRepository {
	UserVO getUserInfo(UserVO vo) throws Exception;
}
