package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class ProposalVO {
	private String ProposalNo;
	private String Seq;
	private String MenuId;
	private String MenuNm;
	private String ProposalNm;
	private String ProposalContent;
	private String ProposalDeptCd;
	private String ProposalDeptNm;
	private String ReviewContent;
	private String ReviewUserNm;
	private String StatusCd;
	private String StatusNm;
	private String AttId;
	private String RegUserId;
	private String RegUserNm;
	private String RegDate;
	private String SearchTxt;
	private String Mode;
	
	public String getProposalNo() {
		return ProposalNo;
	}
	public void setProposalNo(String proposalNo) {
		ProposalNo = proposalNo;
	}
	public String getSeq() {
		return Seq;
	}
	public void setSeq(String seq) {
		Seq = seq;
	}
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public String getMenuNm() {
		return MenuNm;
	}
	public void setMenuNm(String menuNm) {
		MenuNm = menuNm;
	}
	public String getProposalNm() {
		return ProposalNm;
	}
	public void setProposalNm(String proposalNm) {
		ProposalNm = proposalNm;
	}
	public String getProposalContent() {
		return ProposalContent;
	}
	public void setProposalContent(String proposalContent) {
		ProposalContent = proposalContent;
	}
	public String getProposalDeptCd() {
		return ProposalDeptCd;
	}
	public void setProposalDeptCd(String proposalDeptCd) {
		ProposalDeptCd = proposalDeptCd;
	}
	public String getProposalDeptNm() {
		return ProposalDeptNm;
	}
	public void setProposalDeptNm(String proposalDeptNm) {
		ProposalDeptNm = proposalDeptNm;
	}
	public String getReviewContent() {
		return ReviewContent;
	}
	public void setReviewContent(String reviewContent) {
		ReviewContent = reviewContent;
	}
	public String getReviewUserNm() {
		return ReviewUserNm;
	}
	public void setReviewUserNm(String reviewUserNm) {
		ReviewUserNm = reviewUserNm;
	}
	public String getStatusCd() {
		return StatusCd;
	}
	public void setStatusCd(String statusCd) {
		StatusCd = statusCd;
	}
	public String getStatusNm() {
		return StatusNm;
	}
	public void setStatusNm(String statusNm) {
		StatusNm = statusNm;
	}
	public String getAttId() {
		return AttId;
	}
	public void setAttId(String attId) {
		AttId = attId;
	}
	public String getRegUserId() {
		return RegUserId;
	}
	public void setRegUserId(String regUserId) {
		RegUserId = regUserId;
	}
	public String getRegUserNm() {
		return RegUserNm;
	}
	public void setRegUserNm(String regUserNm) {
		RegUserNm = regUserNm;
	}
	public String getRegDate() {
		return RegDate;
	}
	public void setRegDate(String regDate) {
		RegDate = regDate;
	}
	public String getSearchTxt() {
		return SearchTxt;
	}
	public void setSearchTxt(String searchTxt) {
		SearchTxt = searchTxt;
	}	
	public String getMode() {
		return Mode;
	}
	public void setMode(String mode) {
		Mode = mode;
	}	
}