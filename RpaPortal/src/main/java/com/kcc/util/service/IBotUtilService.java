package com.kcc.util.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.kcc.biz.model.BotRunVO;

public interface IBotUtilService {
	String requestBot(BotRunVO vo);
}