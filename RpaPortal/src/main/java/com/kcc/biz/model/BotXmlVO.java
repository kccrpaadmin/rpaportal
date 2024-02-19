package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BotXmlVO {
	private String RequestNo;
	private String Title;
	private String SupplyAmt;
	private String VATAMT;
	private String ResultCd;
	private String ResultNm;
	private String ResultMsg;
	private String RegUserNm;
	
	public String getRequestNo() {
		return RequestNo;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getSupplyAmt() {
		return SupplyAmt;
	}
	public void setSupplyAmt(String supplyAmt) {
		SupplyAmt = supplyAmt;
	}
	public String getVATAmt() {
		return VATAMT;
	}
	public void setVATAmt(String vatAmt) {
		VATAMT = vatAmt;
	}
	public String getResultCd() {
		return ResultCd;
	}
	public void setResultCd(String resultCd) {
		ResultCd = resultCd;
	}
	public String getResultNm() {
		return ResultNm;
	}
	public void setResultNm(String resultNm) {
		ResultNm = resultNm;
	}
	public String getResultMsg() {
		return ResultMsg;
	}
	public void setgetResultMsg(String resultmsg) {
		ResultMsg = resultmsg;
	}
	public String getRegUserNm() {
		return RegUserNm;
	}
	public void setRegUserNm(String regUserNm) {
		RegUserNm = regUserNm;
	}


}