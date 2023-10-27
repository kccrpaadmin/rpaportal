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
	private String ScreenshotAttId;
	private String ScreenshotAttFileNm;
	private String WeekCount;
	private String DayCount;
	private String LatestViewCount;
	private String BeforeViewCount;
	private String Type;
	private String Mon;
	private String Tue;
	private String Wed;
	private String Thu;
	private String Fri;
	private String Sat;
	private String Sun;
	private String AdSelect1;
	private String AdSelect2;
	
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
	public String getScreenshotAttId() {
		return ScreenshotAttId;
	}
	public String getScreenshotAttFileNm() {
		return ScreenshotAttFileNm;
	}
	public String getWeekCount() {
		return WeekCount;
	}
	public String getDayCount() {
		return DayCount;
	}
	public String getLatestViewCount() {
		return LatestViewCount;
	}
	public String getBeforeViewCount() {
		return BeforeViewCount;
	}
	public String getType() {
		return Type;
	}
	public String getMon() {
		return Mon;
	}
	public String getTue() {
		return Tue ;
	}
	public String getWed() {
		return Wed;
	}
	public String getThu() {
		return Thu;
	}
	public String getFri() {
		return Fri;
	}
	public String getSat() {
		return Sat;
	}
	public String getSun() {
		return Sun;
	}
	public String getAdSelect1() {
		return AdSelect1;
	}
	public String getAdSelect2() {
		return AdSelect2;
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
	public void setScreenshotAttId(String screenshotAttId) {
		ScreenshotAttId = screenshotAttId;
	}
	public void setScreenshotAttFileNm(String screenshotAttFileNm) {
		ScreenshotAttFileNm = screenshotAttFileNm;
	}
	public void setWeekCount(String weekCount) {
		WeekCount = weekCount;
	}
	public void setDayCount(String dayCount) {
		DayCount = dayCount;
	}
	public void setLatestViewCount(String latestViewCount) {
		LatestViewCount = latestViewCount;
	}
	public void setBeforeViewCount(String beforeViewCount) {
		BeforeViewCount = beforeViewCount;
	}
	public void setType(String type) {
		Type = type;
	}
	public void setMon(String mon) {
		Mon = mon;
	}
	public void setTue(String tue) {
		Tue = tue;
	}
	public void setWed(String wed) {
		Wed = wed;
	}
	public void setThu(String thu) {
		Thu = thu;
	}
	public void setFri(String fri) {
		Fri = fri;
	}
	public void setSat(String sat) {
		Sat = sat;
	}
	public void setSun(String sun) {
		Sun = sun;
	}
	public void setAdSelect1(String adSelect1) {
		AdSelect1 = adSelect1;
	}
	public void setAdSelect2(String adSelect2) {
		AdSelect2 = adSelect2;
	}
}