package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.CodeRepository;
import com.kcc.biz.dao.LoginRepository;
import com.kcc.biz.model.CodeVO;
import com.kcc.biz.model.LoginVO;
import com.kcc.biz.service.ICodeService;
import com.kcc.biz.service.ILoginService;

@Service("codeService")
public class CodeServiceImpl implements ICodeService {
	private static final Logger logger = LoggerFactory.getLogger(CodeServiceImpl.class);
	
	@Resource(name="codeRepository")
	private CodeRepository codeRepository;
	
	public List<CodeVO> listCodeProcedure(CodeVO vo) throws Exception {
		return codeRepository.listCodeProcedure(vo);
	}
}
