package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.HomeVO;
import com.kcc.biz.model.MenuVO;

public interface IHomeService {
	List<HomeVO> listDeptRunTime(HomeVO vo) throws Exception;
}