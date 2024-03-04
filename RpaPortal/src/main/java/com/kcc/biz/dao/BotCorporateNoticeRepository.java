package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotCorporateNoticeVO;

@Repository("botCorporateNoticeRepository")
public interface BotCorporateNoticeRepository {
	List<BotCorporateNoticeVO> listCorporateNoticeResult(BotCorporateNoticeVO vo) throws Exception;
}
