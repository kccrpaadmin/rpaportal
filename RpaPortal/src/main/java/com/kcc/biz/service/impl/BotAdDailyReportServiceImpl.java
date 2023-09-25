package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotAdDailyReportRepository;
import com.kcc.biz.model.BotAdDailyReportVO;
import com.kcc.biz.service.IBotAdDailyReportService;

@Service("botAdDailyReportService")
public class BotAdDailyReportServiceImpl implements IBotAdDailyReportService {
	private static final Logger logger = LoggerFactory.getLogger(BotAdDailyReportServiceImpl.class);
	
	@Resource(name="botAdDailyReportRepository")
	private BotAdDailyReportRepository botAdDailyReportRepository;
	
	public List<BotAdDailyReportVO> listBotAdDailyReport(BotAdDailyReportVO vo) throws Exception {
		return botAdDailyReportRepository.listBotAdDailyReport(vo);
	}

	public List<BotAdDailyReportVO> listBotAdDailyReportTargetAd(BotAdDailyReportVO vo) throws Exception {
		return botAdDailyReportRepository.listBotAdDailyReportTargetAd(vo);
	}

	public List<BotAdDailyReportVO> listBotAdDailyReportManage(BotAdDailyReportVO vo) throws Exception {
		return botAdDailyReportRepository.listBotAdDailyReportManage(vo);
	}
}
