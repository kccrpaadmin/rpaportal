package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId")

public class AnalysisVO {

	private String SearchCondition;
    private String MenuNm;
    private String AccessUrl;
    private String AccessCnt;
    private String AccessPercent;
    private String BeginDate;
    private String EndDate;
    private String UserNm;
    private String DeptNm;
    private String TitleNm;
    private String AccessDevice;
    private String UseDate;
    private String MenuId;
    private String RunCnt;
    private String TotTime;
    
	public String getSearchCondition() {
		return SearchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		SearchCondition = searchCondition;
	}
	public String getMenuNm() {
		return MenuNm;
	}
	public void setMenuNm(String menuNm) {
		MenuNm = menuNm;
	}
	
	public String getAccessUrl() {
		return AccessUrl;
	}
	public void setAccessUrl(String accessUrl) {
		AccessUrl = accessUrl;
	}
	public String getAccessCnt() {
		return AccessCnt;
	}
	public void setAccessCnt(String accessCnt) {
		AccessCnt = accessCnt;
	}
	public String getAccessPercent() {
		return AccessPercent;
	}
	public void setAccessPercent(String accessPercent) {
		AccessPercent = accessPercent;
	}
	public String getBeginDate() {
		return BeginDate;
	}
	public void setBeginDate(String beginDate) {
		BeginDate = beginDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
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
	public String getTitleNm() {
		return TitleNm;
	}
	public void setTitleNm(String titleNm) {
		TitleNm = titleNm;
	}
	public String getAccessDevice() {
		return AccessDevice;
	}
	public void setAccessDevice(String accessDevice) {
		AccessDevice = accessDevice;
	}
	public String getUseDate() {
		return UseDate;
	}
	public void setUseDate(String useDate) {
		UseDate = useDate;
	}
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public String getRunCnt() {
		return RunCnt;
	}
	public void setRunCnt(String runCnt) {
		RunCnt = runCnt;
	}
	public String getTotTime() {
		return TotTime;
	}
	public void setTotTime(String totTime) {
		TotTime = totTime;
	}
    
    
}