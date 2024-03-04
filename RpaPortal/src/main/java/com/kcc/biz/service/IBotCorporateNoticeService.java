package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotCorporateNoticeVO;

public interface IBotCorporateNoticeService {	
	List<BotCorporateNoticeVO> listCorporateNoticeResult(BotCorporateNoticeVO vo) throws Exception;
}