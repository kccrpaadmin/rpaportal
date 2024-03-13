package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotSensoryTemperatureVO;

@Repository("botSensoryTemperatureRepository")
public interface BotSensoryTemperatureRepository {
	List<BotSensoryTemperatureVO> listSensoryTemperatureResult(BotSensoryTemperatureVO vo) throws Exception;
}
