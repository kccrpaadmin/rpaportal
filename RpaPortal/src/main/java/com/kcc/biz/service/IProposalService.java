package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.ProposalVO;

public interface IProposalService {
	List<ProposalVO> listProposal(ProposalVO vo) throws Exception;
	ProposalVO getProposalDetail(ProposalVO vo) throws Exception;
	void createProposalWrite(ProposalVO vo) throws Exception;
	void updateProposalWrite(ProposalVO vo) throws Exception;
	void deleteProposal(ProposalVO vo) throws Exception;
	void saveProposalReview(ProposalVO vo) throws Exception;
}