package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotAutoCADCancelVO;

@Repository("botAutoCADCancelRepository")
public interface BotAutoCADCancelRepository {
	List<BotAutoCADCancelVO> listAutoCADCancelResult(BotAutoCADCancelVO vo) throws Exception;
}
