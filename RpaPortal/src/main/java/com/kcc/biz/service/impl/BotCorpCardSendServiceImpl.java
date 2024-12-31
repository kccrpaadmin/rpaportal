package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotCorpCardSendRepository;
import com.kcc.biz.model.BotCorpCardSendVO;
import com.kcc.biz.service.IBotCorpCardSendService;

@Service("botCorpCardSendService")
public class BotCorpCardSendServiceImpl implements IBotCorpCardSendService {
	private static final Logger logger = LoggerFactory.getLogger(BotCorpCardSendServiceImpl.class);
	
	@Resource(name="botCorpCardSendRepository")
	private BotCorpCardSendRepository botCorpCardSendRepository;
	
	public List<BotCorpCardSendVO> listCorpCardSendResult(BotCorpCardSendVO vo) throws Exception {
		return botCorpCardSendRepository.listCorpCardSendResult(vo);
	}
}
