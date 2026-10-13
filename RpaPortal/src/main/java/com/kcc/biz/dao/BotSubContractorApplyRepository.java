package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotSubContractorApplyVO;

@Repository("botSubContractorApplyRepository")
public interface BotSubContractorApplyRepository {
	List<BotSubContractorApplyVO> listBotSubContractorApplyResult(BotSubContractorApplyVO vo) throws Exception;
}
