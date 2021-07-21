package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotKisconConstRepository;
import com.kcc.biz.model.BotKisconConstVO;
import com.kcc.biz.service.IBotKisconConstService;

@Service("botKisconConstService")
public class BotKisconConstServiceImpl implements IBotKisconConstService {
	private static final Logger logger = LoggerFactory.getLogger(BotKisconConstServiceImpl.class);
	
	@Resource(name="botKisconConstRepository")
	private BotKisconConstRepository botKisconConstRepository;
		
	public List<BotKisconConstVO> listBotKisconConstSubcontract(BotKisconConstVO vo) throws Exception {
		return botKisconConstRepository.listBotKisconConstSubcontract(vo);
	}
	
	public List<BotKisconConstVO> listBotKisconConstManage(BotKisconConstVO vo) throws Exception {
		return botKisconConstRepository.listBotKisconConstManage(vo);
	}
}
