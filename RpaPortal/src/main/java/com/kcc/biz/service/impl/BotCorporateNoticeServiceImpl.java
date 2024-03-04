package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotCorporateNoticeRepository;
import com.kcc.biz.model.BotCorporateNoticeVO;
import com.kcc.biz.service.IBotCorporateNoticeService;

@Service("botCorporateNoticeService")
public class BotCorporateNoticeServiceImpl implements IBotCorporateNoticeService {
	private static final Logger logger = LoggerFactory.getLogger(BotCorporateNoticeServiceImpl.class);
	
	@Resource(name="botCorporateNoticeRepository")
	private BotCorporateNoticeRepository botCorporateNoticeRepository;
	
	public List<BotCorporateNoticeVO> listCorporateNoticeResult(BotCorporateNoticeVO vo) throws Exception {
		return botCorporateNoticeRepository.listCorporateNoticeResult(vo);
	}
}
