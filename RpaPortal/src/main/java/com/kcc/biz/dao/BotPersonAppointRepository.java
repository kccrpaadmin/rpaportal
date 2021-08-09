package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotPersonAppointVO;

@Repository("botPersonAppointRepository")
public interface BotPersonAppointRepository {	
	List<BotPersonAppointVO> listBotPersonAppointList(BotPersonAppointVO vo) throws Exception;	
}
