package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotSensoryTemperatureVO;

public interface IBotSensoryTemperatureService {	
	List<BotSensoryTemperatureVO> listSensoryTemperatureResult(BotSensoryTemperatureVO vo) throws Exception;
}