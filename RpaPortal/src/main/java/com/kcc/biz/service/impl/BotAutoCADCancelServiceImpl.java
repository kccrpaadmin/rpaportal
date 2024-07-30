package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotAutoCADCancelRepository;
import com.kcc.biz.model.BotAutoCADCancelVO;
import com.kcc.biz.service.IBotAutoCADCancelService;

@Service("botAutoCADCancelService")
public class BotAutoCADCancelServiceImpl implements IBotAutoCADCancelService {
	private static final Logger logger = LoggerFactory.getLogger(BotAutoCADCancelServiceImpl.class);
	
	@Resource(name="botAutoCADCancelRepository")
	private BotAutoCADCancelRepository botAutoCADCancelRepository;
	
	public List<BotAutoCADCancelVO> listAutoCADCancelResult(BotAutoCADCancelVO vo) throws Exception {
		return botAutoCADCancelRepository.listAutoCADCancelResult(vo);
	}
}
