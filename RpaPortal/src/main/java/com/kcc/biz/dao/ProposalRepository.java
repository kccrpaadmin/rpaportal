package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.ProposalVO;

@Repository("proposalRepository")
public interface ProposalRepository {
	List<ProposalVO> listProposal(ProposalVO vo) throws Exception;
	ProposalVO getProposalDetail(ProposalVO vo) throws Exception;
	void saveProposalWrite(ProposalVO vo) throws Exception;
}
