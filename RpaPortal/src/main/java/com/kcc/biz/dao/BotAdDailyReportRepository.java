package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotAdDailyReportVO;

@Repository("botAdDailyReportRepository")
public interface BotAdDailyReportRepository {
	List<BotAdDailyReportVO> listBotAdDailyReport(BotAdDailyReportVO vo) throws Exception;
	List<BotAdDailyReportVO> listBotAdDailyReportTargetAd(BotAdDailyReportVO vo) throws Exception;
	List<BotAdDailyReportVO> listBotAdDailyReportManage(BotAdDailyReportVO vo) throws Exception;
}
