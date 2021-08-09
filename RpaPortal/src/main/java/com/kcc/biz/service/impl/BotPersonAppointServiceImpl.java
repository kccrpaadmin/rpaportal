package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotPersonAppointRepository;
import com.kcc.biz.model.BotPersonAppointVO;
import com.kcc.biz.service.IBotPersonAppointService;

@Service("botPersonAppointService")
public class BotPersonAppointServiceImpl implements IBotPersonAppointService {
	private static final Logger logger = LoggerFactory.getLogger(BotPersonAppointServiceImpl.class);
	
	@Resource(name="botPersonAppointRepository")
	private BotPersonAppointRepository botPersonAppointRepository;
			
	public List<BotPersonAppointVO> listBotPersonAppointList(BotPersonAppointVO vo) throws Exception {		
		return botPersonAppointRepository.listBotPersonAppointList(vo);
	}
}
