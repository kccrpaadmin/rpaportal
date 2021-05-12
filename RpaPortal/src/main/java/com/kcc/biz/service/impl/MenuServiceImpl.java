package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.MenuRepository;
import com.kcc.biz.dao.UserRepository;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.IUserService;

@Service("menuService")
public class MenuServiceImpl implements IMenuService {
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Resource(name="menuRepository")
	private MenuRepository menuRepository;
	
	public List<MenuVO> listCrawlMenu(MenuVO vo) throws Exception {
		return menuRepository.listCrawlMenu(vo);
	}
	
	public MenuVO getCrawlMenu(MenuVO vo) throws Exception {
		return menuRepository.getCrawlMenu(vo);
	}	
		
	public List<MenuVO> listOcrMenu(MenuVO vo) throws Exception {
		return menuRepository.listOcrMenu(vo);
	}
	
	public MenuVO getOcrMenu(MenuVO vo) throws Exception {
		return menuRepository.getOcrMenu(vo);
	}
	
	public List<MenuVO> listBotMenu(MenuVO vo) throws Exception {
		return menuRepository.listBotMenu(vo);
	}
	
	public MenuVO getBotMenu(MenuVO vo) throws Exception {
		return menuRepository.getBotMenu(vo);
	}
	
	public MenuVO getMenuAuth(MenuVO vo) throws Exception {
		return menuRepository.getMenuAuth(vo);
	}
}
