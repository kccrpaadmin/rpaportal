package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotEngineerRegisterRepository;
import com.kcc.biz.dao.BotStampTaxSlipDataRepository;
import com.kcc.biz.model.BotEngineerRegisterVO;
import com.kcc.biz.model.BotStampTaxSlipDataVO;
import com.kcc.biz.service.IBotEngineerRegisterService;
import com.kcc.biz.service.IBotStampTaxSlipDataService;

@Service("botEngineerRegisterService")
public class BotEngineerRegisterServiceImpl implements IBotEngineerRegisterService {
	private static final Logger logger = LoggerFactory.getLogger(BotEngineerRegisterServiceImpl.class);
	
	@Resource(name="botEngineerRegisterRepository")
	private BotEngineerRegisterRepository botEngineerRegisterRepository;
	
	public List<BotEngineerRegisterVO> listEngineerRegisterResult(BotEngineerRegisterVO vo) throws Exception {
		return botEngineerRegisterRepository.listEngineerRegisterResult(vo);
	}
}
