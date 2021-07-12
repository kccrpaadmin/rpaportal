package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotKisconConstVO;

@Repository("botKisconConstRepository")
public interface BotKisconConstRepository {	
	List<BotKisconConstVO> listBotKisconConstSubcontract(BotKisconConstVO vo) throws Exception;
}
