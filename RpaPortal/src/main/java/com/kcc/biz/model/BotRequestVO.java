package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BotRequestVO {
	private String RequestNo;
	private String NewRequestNo;
	private String MenuId;
	private String MenuNm;
	private String RequestNm;
	private String BotIp;
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
	private String BotParam;
	
	public String getRequestNo() {
		return RequestNo;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public String getNewRequestNo() {
		return NewRequestNo;
	}
	public void setNewRequestNo(String newRequestNo) {
		NewRequestNo = newRequestNo;
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
	public String getRequestNm() {
		return RequestNm;
	}
	public void setRequestNm(String requestNm) {
		RequestNm = requestNm;
	}
	public String getBotIp() {
		return BotIp;
	}
	public void setBotIp(String botIp) {
		BotIp = botIp;
	}
	public String getAttId() {
		return AttId;
	}
	public void setAttId(String attId) {
		AttId = attId;
	}
	public String getApproveId() {
		return ApproveId;
	}
	public void setApproveId(String approveId) {
		ApproveId = approveId;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
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
	public String getChgUserId() {
		return ChgUserId;
	}
	public void setChgUserId(String chgUserId) {
		ChgUserId = chgUserId;
	}
	public String getChgUserNm() {
		return ChgUserNm;
	}
	public void setChgUserNm(String chgUserNm) {
		ChgUserNm = chgUserNm;
	}
	public String getChgDate() {
		return ChgDate;
	}
	public void setChgDate(String chgDate) {
		ChgDate = chgDate;
	}
	public String getEmpNo() {
		return EmpNo;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
	public String getRequestStatus() {
		return RequestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		RequestStatus = requestStatus;
	}
	public String getBotParam() {
		return BotParam;
	}
	public void setBotParam(String botParam) {
		BotParam = botParam;
	}	
}