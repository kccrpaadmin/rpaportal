package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotEngineerEduRepository;
import com.kcc.biz.dao.BotEngineerRepository;
import com.kcc.biz.model.BotEngineerEduVO;
import com.kcc.biz.model.BotEngineerVO;
import com.kcc.biz.service.IBotEngineerEduService;
import com.kcc.biz.service.IBotEngineerService;

@Service("botEngineerEduService")
public class BotEngineerEduServiceImpl implements IBotEngineerEduService {
	private static final Logger logger = LoggerFactory.getLogger(BotEngineerEduServiceImpl.class);
	
	@Resource(name="botEngineerEduRepository")
	private BotEngineerEduRepository botEngineerEduRepository;
		
	public List<BotEngineerEduVO> listBotEngineerConstEduPass(BotEngineerEduVO vo) throws Exception {		
		return botEngineerEduRepository.listBotEngineerConstEduPass(vo);
	}
	
	public List<BotEngineerEduVO> listBotEngineerConstEduFail(BotEngineerEduVO vo) throws Exception {		
		return botEngineerEduRepository.listBotEngineerConstEduFail(vo);
	}	
}
