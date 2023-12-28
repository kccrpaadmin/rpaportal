package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId")

public class BotInsuranceVO {
	private String MenuId;
	private String RequestNo;
	private String No;
	private String SiteNm;
	private String StartDate;
	private String FinishDate;
	private String FinishYn;
	private String RegUserNm;
	private String Status;
	private String Seq;
	private String EmpNo;
	private String EditYn;
	private String ContractNo;
	private String ChgSeq;
	private String InsTypeNm;
	private String IssueNo;
	private String BizMngNo;
	private String BizNo;
	private String BizNm;
	private String VendorPayAmt;
	private String ConfirmPayAmt;
	
	public String getMenuId() {
		return MenuId;
	}
	public String getRequestNo() {
		return RequestNo;
	}
	public String getNo() {
		return No;
	}
	public String getSiteNm() {
		return SiteNm;
	}
	public String getStartDate() {
		return StartDate;
	}
	public String getFinishDate() {
		return FinishDate;
	}
	public String getFinishYn() {
		return FinishYn;
	}
	public String getRegUserNm() {
		return RegUserNm;
	}
	public String getStatus() {
		return Status;
	}
	public String getSeq() {
		return Seq;
	}
	public String getEmpNo() {
		return EmpNo;
	}
	public String getEditYn() {
		return EditYn;
	}
	public String getContractNo() {
		return ContractNo;
	}
	public String getChgSeq() {
		return ChgSeq;
	}
	public String getInsTypeNm() {
		return InsTypeNm;
	}
	public String getIssueNo() {
		return IssueNo;
	}
	public String getBizMngNo() {
		return BizMngNo;
	}
	public String getBizNo() {
		return BizNo;
	}
	public String getBizNm() {
		return BizNm;
	}
	public String getVendorPayAmt() {
		return VendorPayAmt;
	}
	public String getConfirmPayAmt() {
		return ConfirmPayAmt;
	}

	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public void setNo(String no) {
		No = no;
	}
	public void setSiteNm(String siteNm) {
		SiteNm = siteNm;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public void setFinishDate(String finishDate) {
		FinishDate = finishDate;
	}
	public void setFinishYn(String finishYn) {
		FinishYn = finishYn;
	}
	public void setRegUserNm(String regUserNm) {
		RegUserNm = regUserNm;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public void setSeq(String seq) {
		Seq = seq;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
	public void setEditYn(String editYn) {
		EditYn = editYn;
	}
	public void setContractNo(String contractNo) {
		ContractNo = contractNo;
	}
	public void setChgSeq(String chgSeq) {
		ChgSeq = chgSeq;
	}
	public void setInsTypeNm(String insTypeNm) {
		InsTypeNm = insTypeNm;
	}
	public void setIssueNo(String issueNo) {
		IssueNo = issueNo;
	}
	public void setBizMngNo(String bizMngNo) {
		BizMngNo = bizMngNo;
	}
	public void setBizNo(String bizNo) {
		BizNo = bizNo;
	}
	public void setBizNm(String bizNm) {
		BizNm = bizNm;
	}
	public void setVendorPayAmt(String vendorPayAmt) {
		VendorPayAmt = vendorPayAmt;
	}
}