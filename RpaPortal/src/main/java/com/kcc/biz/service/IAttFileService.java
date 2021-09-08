package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.AttFileVO;

public interface IAttFileService {
	void createAttAndAttFiles(AttFileVO vo, List<AttFileVO> lvo) throws Exception;
	List<AttFileVO> listAttFile(AttFileVO vo) throws Exception;
	AttFileVO getAttFile(AttFileVO vo) throws Exception;
	void deleteAttFile(AttFileVO vo) throws Exception;
	void createAttFiles(List<AttFileVO> lvo) throws Exception;
}
