package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kcc.biz.dao.AccessRepository;
import com.kcc.biz.dao.AttFileRepository;
import com.kcc.biz.dao.LoginRepository;
import com.kcc.biz.model.AccessVO;
import com.kcc.biz.model.AttFileVO;
import com.kcc.biz.model.CrawlG2bVO;
import com.kcc.biz.model.LoginVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.IAttFileService;
import com.kcc.biz.service.ILoginService;

@Service("attFileService")
public class AttFileServiceImpl implements IAttFileService {
	private static final Logger logger = LoggerFactory.getLogger(AttFileServiceImpl.class);
	
	@Resource(name="attFileRepository")
	private AttFileRepository attFileRepository;
	
	public void createAttAndAttFile(AttFileVO vo, List<AttFileVO> lvo) throws Exception {
		attFileRepository.createAtt(vo);
		
		for (AttFileVO attFileVO : lvo) {
			attFileRepository.createAttFile(attFileVO);	
		}
	}
	
	public List<AttFileVO> listAttFile(AttFileVO vo) throws Exception {
		return attFileRepository.listAttFile(vo);
	}
}
