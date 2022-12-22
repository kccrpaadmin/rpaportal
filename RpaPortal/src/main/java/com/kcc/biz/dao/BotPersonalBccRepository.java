package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotPersonalBccVO;

@Repository("botPersonalBccRepository")
public interface BotPersonalBccRepository {	
	List<BotPersonalBccVO> listBotPersonalBcc(BotPersonalBccVO vo) throws Exception;	
}
