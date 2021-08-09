package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BotPersonAppointVO {
	private String RequestNo;
	private String SectionId;
	private String CorpNm;
	private String Title;
	private String MakeDate;
	private String SubSectionId;
	private String SubOfficerNo;
	private String SubTitle;
	private String AttId;
	private String TransferYn;
	
	public String getRequestNo() {
		return RequestNo;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public String getSectionId() {
		return SectionId;
	}
	public void setSectionId(String sectionId) {
		SectionId = sectionId;
	}
	public String getCorpNm() {
		return CorpNm;
	}
	public void setCorpNm(String corpNm) {
		CorpNm = corpNm;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getMakeDate() {
		return MakeDate;
	}
	public void setMakeDate(String makeDate) {
		MakeDate = makeDate;
	}
	public String getSubSectionId() {
		return SubSectionId;
	}
	public void setSubSectionId(String subSectionId) {
		SubSectionId = subSectionId;
	}
	public String getSubOfficerNo() {
		return SubOfficerNo;
	}
	public void setSubOfficerNo(String subOfficerNo) {
		SubOfficerNo = subOfficerNo;
	}
	public String getSubTitle() {
		return SubTitle;
	}
	public void setSubTitle(String subTitle) {
		SubTitle = subTitle;
	}
	public String getAttId() {
		return AttId;
	}
	public void setAttId(String attId) {
		AttId = attId;
	}
	public String getTransferYn() {
		return TransferYn;
	}
	public void setTransferYn(String transferYn) {
		TransferYn = transferYn;
	}


}