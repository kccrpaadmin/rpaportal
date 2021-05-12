package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.CodeVO;

public interface ICodeService {
	List<CodeVO> listCodeProcedure(CodeVO vo) throws Exception;
}