package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class CrawlScheduleVO {
	private String CallDate;
	private String MenuId;
	private String Seq;
	private String SeqStr;	
	private String Monthz;
	private String Dayz;
	private String Weekz;
	private String WeekDayz;
	private String StartTime;
	private String ExpDate;
	private String EmpNo;
	private String RegUserId;
	private String RegUserNm;
	
	public String getCallDate() {
		return CallDate;
	}
	public String getMenuId() {
		return MenuId;
	}
	public String getSeq() {
		return Seq;
	}
	public String getSeqStr() {
		return SeqStr;
	}
	public String getMonthz() {
		return Monthz;
	}
	public String getDayz() {
		return Dayz;
	}
	public String getWeekz() {
		return Weekz;
	}
	public String getWeekDayz() {
		return WeekDayz;
	}
	public String getStartTime() {
		return StartTime;
	}
	public String getExpDate() {
		return ExpDate;
	}
	public String getEmpNo() {
		return EmpNo;
	}
	public String getRegUserId() {
		return RegUserId;
	}
	public String getRegUserNm() {
		return RegUserNm;
	}
	public void setCallDate(String callDate) {
		CallDate = callDate;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public void setSeq(String seq) {
		Seq = seq;
	}
	public void setSeqStr(String seqStr) {
		SeqStr = seqStr;
	}
	public void setMonthz(String monthz) {
		Monthz = monthz;
	}
	public void setDayz(String dayz) {
		Dayz = dayz;
	}
	public void setWeekz(String weekz) {
		Weekz = weekz;
	}
	public void setWeekDayz(String weekDayz) {
		WeekDayz = weekDayz;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public void setExpDate(String expDate) {
		ExpDate = expDate;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
	public void setRegUserId(String regUserId) {
		RegUserId = regUserId;
	}
	public void setRegUserNm(String regUserNm) {
		RegUserNm = regUserNm;
	}	
}