package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotXmlVO;

public interface IBotXmlService {	
	List<BotXmlVO> listXmlResult(BotXmlVO vo) throws Exception;	
}