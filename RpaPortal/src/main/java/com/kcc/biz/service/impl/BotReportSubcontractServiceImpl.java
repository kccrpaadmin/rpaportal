package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotReportSubcontractRepository;
import com.kcc.biz.model.BotReportSubcontractVO;
import com.kcc.biz.service.IBotReportSubcontractService;

@Service("botReportSubcontractService")
public class BotReportSubcontractServiceImpl implements IBotReportSubcontractService {
	private static final Logger logger = LoggerFactory.getLogger(BotReportSubcontractServiceImpl.class);
	
	@Resource(name="botReportSubcontractRepository")
	private BotReportSubcontractRepository botReportSubcontractRepository;
	
	public List<BotReportSubcontractVO> listBotReportSubcontractResult(BotReportSubcontractVO vo) throws Exception {
		return botReportSubcontractRepository.listBotReportSubcontractResult(vo);
	}

}
