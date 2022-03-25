package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.MenuRepository;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.service.IMenuService;

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
	
	public List<MenuVO> listMenuTree(MenuVO vo) throws Exception {
		return menuRepository.listMenuTree(vo);
	}
	
	public List<MenuVO> listMenuChild(MenuVO vo) throws Exception {
		return menuRepository.listMenuChild(vo);
	}
	
	public void saveMenuManage(List<MenuVO> vo) throws Exception {
		for (MenuVO menuVO : vo) {
			menuRepository.saveMenuManage(menuVO);
		}
	}
	
	public List<MenuVO> listMenuAuthRunChild(MenuVO vo) throws Exception {
		return menuRepository.listMenuAuthRunChild(vo);
	}

	public List<MenuVO> listMenuAuthManageChild(MenuVO vo) throws Exception {
		return menuRepository.listMenuAuthManageChild(vo);
	}
	
	public List<MenuVO> listAuthManageSearchUser(MenuVO vo) throws Exception {
		return menuRepository.listAuthManageSearchUser(vo);
	}
	
	public List<MenuVO> listAuthManageSearchDegree(MenuVO vo) throws Exception {
		return menuRepository.listAuthManageSearchDegree(vo);
	}
	
	public List<MenuVO> listAuthManageSearchDept(MenuVO vo) throws Exception {
		return menuRepository.listAuthManageSearchDept(vo);
	}
	
	public void createAuthManage(MenuVO vo) throws Exception {
		menuRepository.createAuthManage(vo);
	}
	
	public void deleteAuthManage(List<MenuVO> vo) throws Exception {
		for (MenuVO menuVO : vo) {
			menuRepository.deleteAuthManage(menuVO);
		}
	}
	
	public List<MenuVO> listMenuManageSearchDept(MenuVO vo) throws Exception {
		return menuRepository.listMenuManageSearchDept(vo);
	}
	
}
