package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotCorpCardSendVO;

public interface IBotCorpCardSendService {	
	List<BotCorpCardSendVO> listCorpCardSendResult(BotCorpCardSendVO vo) throws Exception;
}