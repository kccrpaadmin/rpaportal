package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class CrawlMaterialVO {
	private String RequestNo;
	private String StandardDate;
	private String CopperAmt;
	private String ExchangeAmt;
	private String MinCopperAmt;
	private String MaxCopperAmt;
	private String MinExchangeAmt;
	private String MaxExchangeAmt;
	private String YearMon;
	private String SortType;
	
	public String getRequestNo() {
		return RequestNo;
	}
	public String getStandardDate() {
		return StandardDate;
	}
	public String getCopperAmt() {
		return CopperAmt;
	}
	public String getExchangeAmt() {
		return ExchangeAmt;
	}
	public String getMinCopperAmt() {
		return MinCopperAmt;
	}
	public String getMaxCopperAmt() {
		return MaxCopperAmt;
	}
	public String getMinExchangeAmt() {
		return MinExchangeAmt;
	}
	public String getMaxExchangeAmt() {
		return MaxExchangeAmt;
	}
	public String getYearMon() {
		return YearMon;
	}
	public String getSortType() {
		return SortType;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public void setStandardDate(String standardDate) {
		StandardDate = standardDate;
	}
	public void setCopperAmt(String copperAmt) {
		CopperAmt = copperAmt;
	}
	public void setExchangeAmt(String exchangeAmt) {
		ExchangeAmt = exchangeAmt;
	}
	public void setMinCopperAmt(String minCopperAmt) {
		MinCopperAmt = minCopperAmt;
	}
	public void setMaxCopperAmt(String maxCopperAmt) {
		MaxCopperAmt = maxCopperAmt;
	}
	public void setMinExchangeAmt(String minExchangeAmt) {
		MinExchangeAmt = minExchangeAmt;
	}
	public void setMaxExchangeAmt(String maxExchangeAmt) {
		MaxExchangeAmt = maxExchangeAmt;
	}
	public void setYearMon(String yearMon) {
		YearMon = yearMon;
	}
	public void setSortType(String sortType) {
		SortType = sortType;
	}	
}