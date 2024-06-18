package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.HomeRepository;
import com.kcc.biz.dao.MenuRepository;
import com.kcc.biz.dao.UserRepository;
import com.kcc.biz.model.HomeVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IHomeService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.IUserService;

@Service("homeService")
public class HomeServiceImpl implements IHomeService {
	private static final Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);
	
	@Resource(name="homeRepository")
	private HomeRepository homeRepository;
	
	public List<HomeVO> listTimeLine(HomeVO vo) throws Exception {
		return homeRepository.listTimeLine(vo);
	}
	
	public List<HomeVO> listDeptRunTime(HomeVO vo) throws Exception {
		return homeRepository.listDeptRunTime(vo);
	}
	
	public HomeVO getTaskRunTime() throws Exception {
		return homeRepository.getTaskRunTime();
	}
	
	public HomeVO getNoticeTask() throws Exception {
		return homeRepository.getNoticeTask();
	}
	
	public List<HomeVO> listBotRunTime() throws Exception {
		return homeRepository.listBotRunTime();
	}
	
	public List<HomeVO> listBotNumberTime() throws Exception {
		return homeRepository.listBotNumberTime();
	}
}