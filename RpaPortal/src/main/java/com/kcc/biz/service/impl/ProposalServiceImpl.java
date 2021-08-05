package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.ProposalRepository;
import com.kcc.biz.model.ProposalVO;
import com.kcc.biz.service.IProposalService;

@Service("proposalService")
public class ProposalServiceImpl implements IProposalService {
	private static final Logger logger = LoggerFactory.getLogger(ProposalServiceImpl.class);
	
	@Resource(name="proposalRepository")
	private ProposalRepository proposalRepository;
	
	public List<ProposalVO> listProposal(ProposalVO vo) throws Exception {
		return proposalRepository.listProposal(vo);
	}
	
	public ProposalVO getProposalDetail(ProposalVO vo) throws Exception {
		return proposalRepository.getProposalDetail(vo);
	}
	
	public void saveProposalWrite(ProposalVO vo) throws Exception {
		proposalRepository.saveProposalWrite(vo);
	}
}
