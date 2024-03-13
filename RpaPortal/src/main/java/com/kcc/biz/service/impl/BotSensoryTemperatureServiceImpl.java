package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotSensoryTemperatureRepository;
import com.kcc.biz.model.BotSensoryTemperatureVO;
import com.kcc.biz.service.IBotSensoryTemperatureService;

@Service("botSensoryTemperatureService")
public class BotSensoryTemperatureServiceImpl implements IBotSensoryTemperatureService {
	private static final Logger logger = LoggerFactory.getLogger(BotSensoryTemperatureServiceImpl.class);
	
	@Resource(name="botSensoryTemperatureRepository")
	private BotSensoryTemperatureRepository botSensoryTemperatureRepository;
	
	public List<BotSensoryTemperatureVO> listSensoryTemperatureResult(BotSensoryTemperatureVO vo) throws Exception {
		return botSensoryTemperatureRepository.listSensoryTemperatureResult(vo);
	}
}
