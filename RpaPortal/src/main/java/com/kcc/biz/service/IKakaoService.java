package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.KakaoVO;
import com.kcc.biz.model.MenuVO;

public interface IKakaoService {
	List<KakaoVO> listKakaoTemplate(KakaoVO vo) throws Exception;
	void saveKakaoManage(List<KakaoVO> vo) throws Exception;
}