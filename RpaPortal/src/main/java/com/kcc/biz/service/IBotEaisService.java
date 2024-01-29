package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotEaisVO;
import com.kcc.biz.model.CodeVO;

public interface IBotEaisService {
	List<BotEaisVO> listBotEaisSchedule(BotEaisVO vo) throws Exception;
	void saveEaisSchedule(List<BotEaisVO> vo) throws Exception;
	List<BotEaisVO> listEais(BotEaisVO vo) throws Exception;
	String getEaisSiteLocationCombo(String colNm);
	BotEaisVO getEaisSiteLocationComboSub() throws Exception;
	String getEaisSiteCdCombo(String colNm);
	BotEaisVO getEaisSiteCdComboSub() throws Exception;
}