package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class CrawlG2bVO {
	private String ManageNo;
	private String ReceiveDate;
	private String ConstNm;
	private String OrderAgency;
	private String ContractType;
	private String ContractProperty;
	private String TotalContractAmt;
	private String RegJobType;
	private String ContractAmt;
	private String RegJobTypeDept;
	private String GovernmentAmt;
	private String ConstDateTerm;
	private String EtcAmt;
	private String ConstSite;
	private String TargetContractAmt;
	private String SkillOrderDate;
	private String RequestNo;
	private String ShowYn;
	private String RegDate;
	private String StartDate;
	private String EndDate;
	private String EmpNo;
	
	public String getManageNo() {
		return ManageNo;
	}
	public String getReceiveDate() {
		return ReceiveDate;
	}
	public String getConstNm() {
		return ConstNm;
	}
	public String getOrderAgency() {
		return OrderAgency;
	}
	public String getContractType() {
		return ContractType;
	}
	public String getContractProperty() {
		return ContractProperty;
	}
	public String getTotalContractAmt() {
		return TotalContractAmt;
	}
	public String getRegJobType() {
		return RegJobType;
	}
	public String getContractAmt() {
		return ContractAmt;
	}
	public String getRegJobTypeDept() {
		return RegJobTypeDept;
	}
	public String getGovernmentAmt() {
		return GovernmentAmt;
	}
	public String getConstDateTerm() {
		return ConstDateTerm;
	}
	public String getEtcAmt() {
		return EtcAmt;
	}
	public String getConstSite() {
		return ConstSite;
	}
	public String getTargetContractAmt() {
		return TargetContractAmt;
	}
	public String getSkillOrderDate() {
		return SkillOrderDate;
	}
	public String getRequestNo() {
		return RequestNo;
	}
	public String getShowYn() {
		return ShowYn;
	}
	public String getRegDate() {
		return RegDate;
	}
	public String getStartDate() {
		return StartDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public String getEmpNo() {
		return EmpNo;
	}
	public void setManageNo(String manageNo) {
		ManageNo = manageNo;
	}
	public void setReceiveDate(String receiveDate) {
		ReceiveDate = receiveDate;
	}
	public void setConstNm(String constNm) {
		ConstNm = constNm;
	}
	public void setOrderAgency(String orderAgency) {
		OrderAgency = orderAgency;
	}
	public void setContractType(String contractType) {
		ContractType = contractType;
	}
	public void setContractProperty(String contractProperty) {
		ContractProperty = contractProperty;
	}
	public void setTotalContractAmt(String totalContractAmt) {
		TotalContractAmt = totalContractAmt;
	}
	public void setRegJobType(String regJobType) {
		RegJobType = regJobType;
	}
	public void setContractAmt(String contractAmt) {
		ContractAmt = contractAmt;
	}
	public void setRegJobTypeDept(String regJobTypeDept) {
		RegJobTypeDept = regJobTypeDept;
	}
	public void setGovernmentAmt(String governmentAmt) {
		GovernmentAmt = governmentAmt;
	}
	public void setConstDateTerm(String constDateTerm) {
		ConstDateTerm = constDateTerm;
	}
	public void setEtcAmt(String etcAmt) {
		EtcAmt = etcAmt;
	}
	public void setConstSite(String constSite) {
		ConstSite = constSite;
	}
	public void setTargetContractAmt(String targetContractAmt) {
		TargetContractAmt = targetContractAmt;
	}
	public void setSkillOrderDate(String skillOrderDate) {
		SkillOrderDate = skillOrderDate;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public void setShowYn(String showYn) {
		ShowYn = showYn;
	}
	public void setRegDate(String regDate) {
		RegDate = regDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}	
}