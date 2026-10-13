package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotSubContractorApplyRepository;
import com.kcc.biz.model.BotSubContractorApplyVO;
import com.kcc.biz.service.IBotSubContractorApplyService;

@Service("botSubContractorApplyService")
public class BotSubContractorApplyServiceImpl implements IBotSubContractorApplyService {
	private static final Logger logger = LoggerFactory.getLogger(BotSubContractorApplyServiceImpl.class);
	
	@Resource(name="botSubContractorApplyRepository")
	private BotSubContractorApplyRepository botSubContractorApplyRepository;
	
	public List<BotSubContractorApplyVO> listBotSubContractorApplyResult(BotSubContractorApplyVO vo) throws Exception {
		return botSubContractorApplyRepository.listBotSubContractorApplyResult(vo);
	}

}
