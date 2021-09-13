package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.HomeVO;
import com.kcc.biz.model.MenuVO;

@Repository("homeRepository")
public interface HomeRepository {
	List<HomeVO> listDeptRunTime(HomeVO vo) throws Exception;
}
