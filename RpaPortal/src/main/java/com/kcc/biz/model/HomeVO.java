package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class HomeVO {
	private String WorkTypeCd;
	private String CallDate;
	private String DeptNm;
	private String DeptRunTime;
	private String StatusType;
	private String MenuId;
	private String MenuNm;
	private String StartTime;
	private String TotalCnt;
	private String CrawlCnt;
	private String OcrCnt;
	private String BotCnt;
	private String RunTime;
	private String Content;
	private String RunUrl;
	private String Ord;
	private String Year;
	private String MonthWeek;
	private String BotIP;
	private String SumMin;
	private String Bot1;
	private String Bot2;
	private String Bot3;
	private String RegMonth;
	private String Year1;
	private String Year2;
	private String Year3;
	private String BotType;
	private String Week;
	
	public String getWorkTypeCd() {
		return WorkTypeCd;
	}
	public void setWorkTypeCd(String workTypeCd) {
		WorkTypeCd = workTypeCd;
	}
	public String getCallDate() {
		return CallDate;
	}
	public void setCallDate(String callDate) {
		CallDate = callDate;
	}
	public String getDeptNm() {
		return DeptNm;
	}
	public void setDeptNm(String deptNm) {
		DeptNm = deptNm;
	}
	public String getDeptRunTime() {
		return DeptRunTime;
	}
	public void setDeptRunTime(String deptRunTime) {
		DeptRunTime = deptRunTime;
	}
	public String getStatusType() {
		return StatusType;
	}
	public void setStatusType(String statusType) {
		StatusType = statusType;
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
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getTotalCnt() {
		return TotalCnt;
	}
	public void setTotalCnt(String totalCnt) {
		TotalCnt = totalCnt;
	}
	public String getCrawlCnt() {
		return CrawlCnt;
	}
	public void setCrawlCnt(String crawlCnt) {
		CrawlCnt = crawlCnt;
	}
	public String getOcrCnt() {
		return OcrCnt;
	}
	public void setOcrCnt(String ocrCnt) {
		OcrCnt = ocrCnt;
	}
	public String getBotCnt() {
		return BotCnt;
	}
	public void setBotCnt(String botCnt) {
		BotCnt = botCnt;
	}
	public String getRunTime() {
		return RunTime;
	}
	public void setRunTime(String runTime) {
		RunTime = runTime;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getRunUrl() {
		return RunUrl;
	}
	public void setRunUrl(String runUrl) {
		RunUrl = runUrl;
	}
	public String getOrd() {
		return Ord;
	}
	public void setOrd(String ord) {
		Ord = ord;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getMonthWeek() {
		return MonthWeek;
	}
	public void setMonthWeek(String monthWeek) {
		MonthWeek = monthWeek;
	}
	public String getBotIP() {
		return BotIP;
	}
	public void setBotIP(String botIP) {
		BotIP = botIP;
	}
	public String getSumMin() {
		return SumMin;
	}
	public void setSumMin(String sumMin) {
		SumMin = sumMin;
	}
	public String getBot1() {
		return Bot1;
	}
	public void setBot1(String bot1) {
		Bot1 = bot1;
	}
	public String getBot2() {
		return Bot2;
	}
	public void setBot2(String bot2) {
		Bot2 = bot2;
	}
	public String getBot3() {
		return Bot3;
	}
	public void setBot3(String bot3) {
		Bot3 = bot3;
	}
	public String getRegMonth() {
		return RegMonth;
	}
	public void setRegMonth(String regMonth) {
		RegMonth = regMonth;
	}
	public String getYear1() {
		return Year1;
	}
	public void setYear1(String year1) {
		Year1 = year1;
	}
	public String getYear2() {
		return Year2;
	}
	public void setYear2(String year2) {
		Year2 = year2;
	}
	public String getYear3() {
		return Year3;
	}
	public void setYear3(String year3) {
		Year3 = year3;
	}
	public String getBotType() {
		return BotType;
	}
	public void setBotType(String botType) {
		BotType = botType;
	}
	public String getWeek() {
		return Week;
	}
	public void setWeek(String week) {
		Week = week;
	}
}