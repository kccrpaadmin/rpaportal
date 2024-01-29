package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotEaisRepository;
import com.kcc.biz.model.BotEaisVO;
import com.kcc.biz.service.IBotEaisService;

@Service("botEaisService")
public class BotEaisServiceImpl implements IBotEaisService {
	private static final Logger logger = LoggerFactory.getLogger(BotEaisServiceImpl.class);
	
	@Resource(name="botEaisRepository")
	private BotEaisRepository botEaisRepository;
	
	public List<BotEaisVO> listBotEaisSchedule(BotEaisVO vo) throws Exception {
		return botEaisRepository.listBotEaisSchedule(vo);
	}
	
	public void saveEaisSchedule(List<BotEaisVO> vo) throws Exception {
		for (BotEaisVO botEaisVO : vo) {
			botEaisRepository.saveEaisSchedule(botEaisVO);
		}
	}

	public List<BotEaisVO> listEais(BotEaisVO vo) throws Exception {
		return botEaisRepository.listEais(vo);
	}

	// 세움터 '보완' 대지위치 수집 목록 조회
	public String getEaisSiteLocationCombo(String colNm) {
		// CodeVO 출력
		BotEaisVO outEaisVO = new BotEaisVO();
		String returnValue = "";
				
		try {
			outEaisVO = getEaisSiteLocationComboSub();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if(colNm.equals("Seq")) {
			returnValue = outEaisVO.getSeq();
		}
		else	{
			returnValue = outEaisVO.getSiteLocation();
		}
				
		return returnValue;
	}
	
	public BotEaisVO getEaisSiteLocationComboSub() throws Exception {
		return botEaisRepository.getEaisSiteLocationCombo();
	}
	
	// 세움터 현장명 목록 조회
	public String getEaisSiteCdCombo(String colNm) {
		// CodeVO 출력
		BotEaisVO outEaisVO = new BotEaisVO();
		String returnValue = "";
				
		try {
			outEaisVO = getEaisSiteCdComboSub();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if(colNm.equals("SiteCd")) {
			returnValue = outEaisVO.getSiteCd();
		}
		else	{
			returnValue = outEaisVO.getSiteNm();
		}
				
		return returnValue;
	}
	
	public BotEaisVO getEaisSiteCdComboSub() throws Exception {
		return botEaisRepository.getEaisSiteCdCombo();
	}
}
