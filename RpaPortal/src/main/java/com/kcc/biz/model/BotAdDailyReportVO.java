package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId")

public class BotAdDailyReportVO {
	private String MenuId;
	private String RequestNo;
	private String AdNo;
	private String AdNm;
	private String ViewCount;
	private String LikeCount;
	private String CommentCount;
	private String StartDate;
	private String AdUrl;
	private String RegDate;
	private String RunDate;
	private String RunTime;
	
	public String getMenuId() {
		return MenuId;
	}
	public String getRequestNo() {
		return RequestNo;
	}
	public String getAdNo() {
		return AdNo;
	}
	public String getAdNm() {
		return AdNm;
	}
	public String getViewCount() {
		return ViewCount;
	}
	public String getLikeCount() {
		return LikeCount;
	}
	public String getCommentCount() {
		return CommentCount;
	}
	public String getStartDate() {
		return StartDate;
	}
	public String getAdUrl() {
		return AdUrl;
	}
	public String getRegDate() {
		return RegDate;
	}
	public String getRunDate() {
		return RunDate;
	}
	public String getRunTime() {
		return RunTime;
	}

	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public void setAdNo(String adNo) {
		AdNo = adNo;
	}
	public void setAdNm(String adNm) {
		AdNm = adNm;
	}
	public void setViewCount(String viewCount) {
		ViewCount = viewCount;
	}
	public void setLikeCount(String likeCount) {
		LikeCount = likeCount;
	}
	public void setCommentCount(String commentCount) {
		CommentCount = commentCount;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public void setAdUrl(String adUrl) {
		AdUrl = adUrl;
	}
	public void setRegDate(String regDate) {
		RegDate = regDate;
	}
	public void setRunDate(String runDate) {
		RunDate = runDate;
	}
	public void setRunTime(String runTime) {
		RunTime = runTime;
	}
}