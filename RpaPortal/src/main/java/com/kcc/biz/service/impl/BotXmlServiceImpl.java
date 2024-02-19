package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotXmlRepository;
import com.kcc.biz.model.BotXmlVO;
import com.kcc.biz.service.IBotXmlService;

@Service("botXmlService")
public class BotXmlServiceImpl implements IBotXmlService {
	private static final Logger logger = LoggerFactory.getLogger(BotXmlServiceImpl.class);
	
	@Resource(name="botXmlRepository")
	private BotXmlRepository botXmlRepository;
		
	public List<BotXmlVO> listXmlResult(BotXmlVO vo) throws Exception {
		return botXmlRepository.listXmlResult(vo);
	}
}
