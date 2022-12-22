package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BotPersonalBccVO {
	private String MenuId;
	private String UserId;
	private String EmpNo;
	private String RequestNo;
	private String ApplicantId;
	private String UserNm;
	private String DeptNm;
	private String DutyNm;
	private String AmtLmt;
	private String ApplyCdNm;
	private String EngLastNm;
	private String EngFirstNm;
	private String ZipCd;
	private String CompAddress;
	private String SuccessTypeCdNm;
	private String ErrorMsg;
	
		
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}	
	public String getEmpNo() {
		return EmpNo;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
	public String getRequestNo() {
		return RequestNo;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public String getApplicantId() {
		return ApplicantId;
	}
	public void setApplicantId(String applicantId) {
		ApplicantId = applicantId;
	}
	public String getUserNm() {
		return UserNm;
	}
	public void setUserNm(String userNm) {
		UserNm = userNm;
	}
	public String getDeptNm() {
		return DeptNm;
	}
	public void setDeptNm(String deptNm) {
		DeptNm = deptNm;
	}
	public String getDutyNm() {
		return DutyNm;
	}
	public void setDutyNm(String dutyNm) {
		DutyNm = dutyNm;
	}
	public String getAmtLmt() {
		return AmtLmt;
	}
	public void setAmtLmt(String amtLmt) {
		AmtLmt = amtLmt;
	}
	public String getApplyCdNm() {
		return ApplyCdNm;
	}
	public void setApplyCdNm(String applyCdNm) {
		ApplyCdNm = applyCdNm;
	}
	public String getEngLastNm() {
		return EngLastNm;
	}
	public void setEngLastNm(String engLastNm) {
		EngLastNm = engLastNm;
	}
	public String getEngFirstNm() {
		return EngFirstNm;
	}
	public void setEngFirstNm(String engFirstNm) {
		EngFirstNm = engFirstNm;
	}
	public String getZipCd() {
		return ZipCd;
	}
	public void setZipCd(String zipCd) {
		ZipCd = zipCd;
	}
	public String getCompAddress() {
		return CompAddress;
	}
	public void setCompAddress(String compAddress) {
		CompAddress = compAddress;
	}
	public String getSuccessTypeCdNm() {
		return SuccessTypeCdNm;
	}
	public void setSuccessTypeCdNm(String successTypeCdNm) {
		SuccessTypeCdNm = successTypeCdNm;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}	
}