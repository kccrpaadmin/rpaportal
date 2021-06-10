package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class CrawlRequestVO {
	private String RequestNo;
	private String NewRequestNo;
	private String MenuId;
	private String MenuNm;
	private String RequestNm;
	private String AttId;
	private String ApproveId;
	private String ErrorMsg;	
	private String StatusCd;
	private String StatusNm;
	private String RegUserId;
	private String RegUserNm;
	private String RegDate;
	private String ChgUserId;
	private String ChgUserNm;
	private String ChgDate;
	private String EmpNo;
	private String RequestStatus;
	
	public String getRequestNo() {
		return RequestNo;
	}
	public String getNewRequestNo() {
		return NewRequestNo;
	}
	public String getMenuId() {
		return MenuId;
	}
	public String getMenuNm() {
		return MenuNm;
	}
	public String getRequestNm() {
		return RequestNm;
	}
	public String getAttId() {
		return AttId;
	}
	public String getApproveId() {
		return ApproveId;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public String getStatusCd() {
		return StatusCd;
	}
	public String getStatusNm() {
		return StatusNm;
	}
	public String getRegUserId() {
		return RegUserId;
	}
	public String getRegUserNm() {
		return RegUserNm;
	}
	public String getRegDate() {
		return RegDate;
	}
	public String getChgUserId() {
		return ChgUserId;
	}
	public String getChgUserNm() {
		return ChgUserNm;
	}
	public String getChgDate() {
		return ChgDate;
	}
	public String getEmpNo() {
		return EmpNo;
	}
	public String getRequestStatus() {
		return RequestStatus;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public void setNewRequestNo(String newRequestNo) {
		NewRequestNo = newRequestNo;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public void setMenuNm(String menuNm) {
		MenuNm = menuNm;
	}
	public void setRequestNm(String requestNm) {
		RequestNm = requestNm;
	}
	public void setAttId(String attId) {
		AttId = attId;
	}
	public void setApproveId(String approveId) {
		ApproveId = approveId;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	public void setStatusCd(String statusCd) {
		StatusCd = statusCd;
	}
	public void setStatusNm(String statusNm) {
		StatusNm = statusNm;
	}
	public void setRegUserId(String regUserId) {
		RegUserId = regUserId;
	}
	public void setRegUserNm(String regUserNm) {
		RegUserNm = regUserNm;
	}
	public void setRegDate(String regDate) {
		RegDate = regDate;
	}
	public void setChgUserId(String chgUserId) {
		ChgUserId = chgUserId;
	}
	public void setChgUserNm(String chgUserNm) {
		ChgUserNm = chgUserNm;
	}
	public void setChgDate(String chgDate) {
		ChgDate = chgDate;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
	public void setRequestStatus(String requestStatus) {
		RequestStatus = requestStatus;
	}	
}