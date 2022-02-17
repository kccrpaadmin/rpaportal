package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class CrawlMaterialVO {
	private String MenuId	;
	private String RequestNo	;
	private String StandardDate;	
	private String MaterialTypeCd;
	private String MaterialTypeNm;
	private String Amt;
	private String StartDate;
	private String EndDate;
	private String ScrapRate;
	private String RebarRate;
	
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public String getRequestNo() {
		return RequestNo;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public String getStandardDate() {
		return StandardDate;
	}
	public void setStandardDate(String standardDate) {
		StandardDate = standardDate;
	}
	public String getMaterialTypeCd() {
		return MaterialTypeCd;
	}
	public void setMaterialTypeCd(String materialTypeCd) {
		MaterialTypeCd = materialTypeCd;
	}
	public String getMaterialTypeNm() {
		return MaterialTypeNm;
	}
	public void setMaterialTypeNm(String materialTypeNm) {
		MaterialTypeNm = materialTypeNm;
	}
	public String getAmt() {
		return Amt;
	}
	public void setAmt(String amt) {
		Amt = amt;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public String getScrapRate() {
		return ScrapRate;
	}
	public void setScrapRate(String scrapRate) {
		ScrapRate = scrapRate;
	}
	public String getRebarRate() {
		return RebarRate;
	}
	public void setRebarRate(String rebarRate) {
		RebarRate = rebarRate;
	}
}