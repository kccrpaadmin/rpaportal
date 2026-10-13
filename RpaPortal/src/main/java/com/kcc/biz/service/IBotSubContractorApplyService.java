package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotSubContractorApplyVO;

public interface IBotSubContractorApplyService {	
	List<BotSubContractorApplyVO> listBotSubContractorApplyResult(BotSubContractorApplyVO vo) throws Exception;
}