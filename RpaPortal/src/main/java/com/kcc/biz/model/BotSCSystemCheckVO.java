package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId")

public class BotSCSystemCheckVO {
	private String RequestNo;
	private String SystemCd;
	private String SystemNm;
	private String ActionCd;
	private String ActionNm;
	private String FilePath;
	private String ErrorMsg;
	
	public String getRequestNo() {
		return RequestNo;
	}
	public String getSystemCd() {
		return SystemCd;
	}
	public String getSystemNm() {
		return SystemNm;
	}
	public String getActionCd() {
		return ActionCd;
	}
	public String getActionNm() {
		return ActionNm;
	}
	public String getFilePath() {
		return FilePath;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public void setSystemCd(String systemCd) {
		SystemCd = systemCd;
	}
	public void setSystemNm(String systemNm) {
		SystemNm = systemNm;
	}
	public void setActionCd(String actionCd) {
		ActionCd = actionCd;
	}
	public void setActionNm(String actionNm) {
		ActionNm = actionNm;
	}
	public void setFilePath(String filePath) {
		FilePath = filePath;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}	
}