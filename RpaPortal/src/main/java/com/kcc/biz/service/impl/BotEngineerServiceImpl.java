package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotEngineerRepository;
import com.kcc.biz.model.BotEngineerVO;
import com.kcc.biz.service.IBotEngineerService;

@Service("botEngineerService")
public class BotEngineerServiceImpl implements IBotEngineerService {
	private static final Logger logger = LoggerFactory.getLogger(BotEngineerServiceImpl.class);
	
	@Resource(name="botEngineerRepository")
	private BotEngineerRepository botEngineerRepository;
			
	public List<BotEngineerVO> listBotEngineerBasic(BotEngineerVO vo) throws Exception {		
		return botEngineerRepository.listBotEngineerBasic(vo);
	}
	
	public List<BotEngineerVO> listBotEngineerQualityRank(BotEngineerVO vo) throws Exception {		
		return botEngineerRepository.listBotEngineerQualityRank(vo);
	}
	
	public List<BotEngineerVO> listBotEngineerQualityEduPass(BotEngineerVO vo) throws Exception {		
		return botEngineerRepository.listBotEngineerQualityEduPass(vo);
	}
	
	public List<BotEngineerVO> listBotEngineerQualityEduFail(BotEngineerVO vo) throws Exception {		
		return botEngineerRepository.listBotEngineerQualityEduFail(vo);
	}
	
	public List<BotEngineerVO> listBotEngineerCareerList(BotEngineerVO vo) throws Exception {		
		return botEngineerRepository.listBotEngineerCareerList(vo);
	}
	
	public List<BotEngineerVO> listBotEngineerManage(BotEngineerVO vo) throws Exception {		
		return botEngineerRepository.listBotEngineerManage(vo);
	}
	
	public List<BotEngineerVO> listBotEngineerManageCareerList(BotEngineerVO vo) throws Exception {		
		return botEngineerRepository.listBotEngineerManageCareerList(vo);
	}
	
	public List<BotEngineerVO> listBotEngineerTargetUser(BotEngineerVO vo) throws Exception {		
		return botEngineerRepository.listBotEngineerTargetUser(vo);
	}
	
	public void saveBotEngineerTargetUser(BotEngineerVO vo) throws Exception {
		botEngineerRepository.saveBotEngineerTargetUser(vo);
	}
	
	public List<BotEngineerVO> listBotEngineerTargetUserSearchUser(BotEngineerVO vo) throws Exception {		
		return botEngineerRepository.listBotEngineerTargetUserSearchUser(vo);
	}
}
