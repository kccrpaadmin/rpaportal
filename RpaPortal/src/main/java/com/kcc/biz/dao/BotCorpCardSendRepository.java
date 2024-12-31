package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotCorpCardSendVO;

@Repository("botCorpCardSendRepository")
public interface BotCorpCardSendRepository {
	List<BotCorpCardSendVO> listCorpCardSendResult(BotCorpCardSendVO vo) throws Exception;
}
