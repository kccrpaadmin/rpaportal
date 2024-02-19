package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotXmlVO;

@Repository("botXmlRepository")
public interface BotXmlRepository {	
	List<BotXmlVO> listXmlResult(BotXmlVO vo) throws Exception;
	
}
