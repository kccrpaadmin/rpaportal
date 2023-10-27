package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotAdDailyReportVO;

public interface IBotAdDailyReportService {
	List<BotAdDailyReportVO> listBotAdDailyReport(BotAdDailyReportVO vo) throws Exception;
	List<BotAdDailyReportVO> listBotAdDailyReportTargetAd(BotAdDailyReportVO vo) throws Exception;
	List<BotAdDailyReportVO> listBotAdDailyReportManage(BotAdDailyReportVO vo) throws Exception;
	List<BotAdDailyReportVO> listBotAdDailyReportManageChart(BotAdDailyReportVO vo) throws Exception;
}