package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotKisconConstVO;

@Repository("botKisconConstRepository")
public interface BotKisconConstRepository {	
	List<BotKisconConstVO> listKisconConstSubcontract(BotKisconConstVO vo) throws Exception;
	List<BotKisconConstVO> listKisconConstManage(BotKisconConstVO vo) throws Exception;
	List<BotKisconConstVO> listKisconFirstConstSubcontract(BotKisconConstVO vo) throws Exception;
}
