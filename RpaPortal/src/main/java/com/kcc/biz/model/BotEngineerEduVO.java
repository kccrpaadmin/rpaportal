package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BotEngineerEduVO {
	private String MenuId;
	private String RequestNo;
	private String UserNm;
	private String Seq;
	private String ResNo;
	private String Phase;
	private String InitDate;
	private String RunDays;
	private String ThreeYearDate;
	private String EduType;
	private String EduTimes;
	private String RecogScore;
	private String DelayDate;
	private String PassDate;
	private String PassTimes;
	private String PassScore;
	private String ReceiptDate;
	private String DutyCd;
	private String DutyNm;
	private String UserId;
	private String OrgTypeCd;
	private String SocietyUserNm;
	private String SocietyResNo;
	private String Etc;
	
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
	public String getUserNm() {
		return UserNm;
	}
	public void setUserNm(String userNm) {
		UserNm = userNm;
	}
	public String getSeq() {
		return Seq;
	}
	public void setSeq(String seq) {
		Seq = seq;
	}
	public String getResNo() {
		return ResNo;
	}
	public void setResNo(String resNo) {
		ResNo = resNo;
	}
	public String getPhase() {
		return Phase;
	}
	public void setPhase(String phase) {
		Phase = phase;
	}
	public String getInitDate() {
		return InitDate;
	}
	public void setInitDate(String initDate) {
		InitDate = initDate;
	}
	public String getRunDays() {
		return RunDays;
	}
	public void setRunDays(String runDays) {
		RunDays = runDays;
	}
	public String getThreeYearDate() {
		return ThreeYearDate;
	}
	public void setThreeYearDate(String threeYearDate) {
		ThreeYearDate = threeYearDate;
	}
	public String getEduType() {
		return EduType;
	}
	public void setEduType(String eduType) {
		EduType = eduType;
	}
	public String getEduTimes() {
		return EduTimes;
	}
	public void setEduTimes(String eduTimes) {
		EduTimes = eduTimes;
	}
	public String getRecogScore() {
		return RecogScore;
	}
	public void setRecogScore(String recogScore) {
		RecogScore = recogScore;
	}
	public String getDelayDate() {
		return DelayDate;
	}
	public void setDelayDate(String delayDate) {
		DelayDate = delayDate;
	}
	public String getPassDate() {
		return PassDate;
	}
	public void setPassDate(String passDate) {
		PassDate = passDate;
	}
	public String getPassTimes() {
		return PassTimes;
	}
	public void setPassTimes(String passTimes) {
		PassTimes = passTimes;
	}
	public String getPassScore() {
		return PassScore;
	}
	public void setPassScore(String passScore) {
		PassScore = passScore;
	}
	public String getReceiptDate() {
		return ReceiptDate;
	}
	public void setReceiptDate(String receiptDate) {
		ReceiptDate = receiptDate;
	}
	public String getDutyCd() {
		return DutyCd;
	}
	public void setDutyCd(String dutyCd) {
		DutyCd = dutyCd;
	}
	public String getDutyNm() {
		return DutyNm;
	}
	public void setDutyNm(String dutyNm) {
		DutyNm = dutyNm;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getOrgTypeCd() {
		return OrgTypeCd;
	}
	public void setOrgTypeCd(String orgTypeCd) {
		OrgTypeCd = orgTypeCd;
	}
	public String getSocietyUserNm() {
		return SocietyUserNm;
	}
	public void setSocietyUserNm(String societyUserNm) {
		SocietyUserNm = societyUserNm;
	}
	public String getSocietyResNo() {
		return SocietyResNo;
	}
	public void setSocietyResNo(String societyResNo) {
		SocietyResNo = societyResNo;
	}
	public String getEtc() {
		return Etc;
	}
	public void setEtc(String etc) {
		Etc = etc;
	}	
}