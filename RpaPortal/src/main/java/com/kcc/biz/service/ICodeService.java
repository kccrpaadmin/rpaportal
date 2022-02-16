package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.CodeVO;

public interface ICodeService {
	List<CodeVO> listCodeProcedure(CodeVO vo) throws Exception;
	List<CodeVO> listCodeTree(CodeVO vo) throws Exception;
	List<CodeVO> listCodeChild(CodeVO vo) throws Exception;
	void saveCodeManage(List<CodeVO> vo) throws Exception;
	CodeVO getGridCodeCombo(CodeVO vo) throws Exception;
}