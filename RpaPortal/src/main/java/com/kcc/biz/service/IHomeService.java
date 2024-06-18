package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.HomeVO;
import com.kcc.biz.model.MenuVO;

public interface IHomeService {
	List<HomeVO> listTimeLine(HomeVO vo) throws Exception;
	List<HomeVO> listDeptRunTime(HomeVO vo) throws Exception;
	List<HomeVO> listBotRunTime() throws Exception;
	List<HomeVO> listBotNumberTime() throws Exception;
	HomeVO getTaskRunTime() throws Exception;
	HomeVO getNoticeTask() throws Exception;
}