package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId")

public class BotSpecialConditionVO {
	private String MenuId;
	private String RequestNo;
	private String ErpRequestNo;
	private String SiteNm;
	private String ConstNm;
	private String RegUserNm;
	private String SaveFileNm;
	private String SuccessTypeNm;
	private String ErrorMsg;
	
	public String getMenuId() {
		return MenuId;
	}
	public String getRequestNo() {
		return RequestNo;
	}
	public String getErpRequestNo() {
		return ErpRequestNo;
	}
	public String getSiteNm() {
		return SiteNm;
	}
	public String getConstNm() {
		return ConstNm;
	}
	public String getRegUserNm() {
		return RegUserNm;
	}
	public String getSaveFileNm() {
		return SaveFileNm;
	}
	public String getSuccessTypeNm() {
		return SuccessTypeNm;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}

	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public void setErpRequestNo(String erpRequestNo) {
		ErpRequestNo = erpRequestNo;
	}
	public void setSiteNm(String siteNm) {
		SiteNm = siteNm;
	}
	public void setConstNm(String constNm) {
		ConstNm = constNm;
	}
	public void setRegUserNm(String regUserNm) {
		RegUserNm = regUserNm;
	}
	public void setSaveFileNm(String saveFileNm) {
		SaveFileNm = saveFileNm;
	}
	public void setSuccessTypeNm(String successTypeNm) {
		SuccessTypeNm = successTypeNm;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
}