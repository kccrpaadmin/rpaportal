package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BotEngineerRegisterVO {
	private String RequestNo;
	private String Seq;
	private String RegisterDate;
	private String UserNm;
	private String RegisterType;
	private String SiteNm;
	private String Etc;
	
	public String getRequestNo() {
		return RequestNo;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public String getContractNo() {
		return Seq;
	}
	public void setContractNo(String seq) {
		Seq = seq;
	}
	public String getChgSeq() {
		return RegisterDate;
	}
	public void setChgSeq(String registerDate) {
		RegisterDate = registerDate;
	}
	public String getContractNm() {
		return UserNm;
	}
	public void setContractNm(String userNm) {
		UserNm = userNm;
	}
	public String getVendorCd() {
		return RegisterType;
	}
	public void setVendorCd(String registerType) {
		RegisterType = registerType;
	}
	public String getBizNo() {
		return SiteNm;
	}
	public void setBizNo(String siteNm) {
		SiteNm = siteNm;
	}
	public String getVendorNm() {
		return Etc;
	}
	public void setVendorNm(String etc) {
		Etc = etc;
	}

}