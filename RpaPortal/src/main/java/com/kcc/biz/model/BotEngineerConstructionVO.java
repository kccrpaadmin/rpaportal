package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BotEngineerConstructionVO {
	private String MenuId;
	private String RequestNo;
	private String UserNm;
	private String Seq;
	private String ResNo;
	private String JobField;
	private String Ranking;
	private String RankingRate;
	private String AfterRanking;
	private String UserId;
	private String Etc;
	private String Jikmu1;
	private String Jikmu2;
	private String Jikmu3;
	private String Jikmu4;
	private String Jikmu5;
	private String Jikmu6;
	private String Jikmu7;
	private String Jikmu8;
	private String Jikmu9;
	private String Jikmu10;
	private String DutyNm;
	private String DeptNm;
	private String OrgTypeCd;
	private String Rk1;
	private String Rk2;
	private String Rk3;
	private String Rk4;
	private String Rk5;
	private String Rk6;
	private String Rk7;
	private String Rk8;
	private String Rk9;
	private String Rk10;
	
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
	public String getJobField() {
		return JobField;
	}
	public void setJobField(String jobField) {
		JobField = jobField;
	}
	public String getRanking() {
		return Ranking;
	}
	public void setRanking(String ranking) {
		Ranking = ranking;
	}
	public String getRankingRate() {
		return RankingRate;
	}
	public void setRankingRate(String rankingRate) {
		RankingRate = rankingRate;
	}
	public String getAfterRanking() {
		return AfterRanking;
	}
	public void setAfterRanking(String afterRanking) {
		AfterRanking = afterRanking;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getEtc() {
		return Etc;
	}
	public void setEtc(String etc) {
		Etc = etc;
	}
	public String getJikmu1() {
		return Jikmu1;
	}
	public void setJikmu1(String jikmu1) {
		Jikmu1 = jikmu1;
	}
	public String getJikmu2() {
		return Jikmu2;
	}
	public void setJikmu2(String jikmu2) {
		Jikmu2 = jikmu2;
	}
	public String getJikmu3() {
		return Jikmu3;
	}
	public void setJikmu3(String jikmu3) {
		Jikmu3 = jikmu3;
	}
	public String getJikmu4() {
		return Jikmu4;
	}
	public void setJikmu4(String jikmu4) {
		Jikmu4 = jikmu4;
	}
	public String getJikmu5() {
		return Jikmu5;
	}
	public void setJikmu5(String jikmu5) {
		Jikmu5 = jikmu5;
	}
	public String getJikmu6() {
		return Jikmu6;
	}
	public void setJikmu6(String jikmu6) {
		Jikmu6 = jikmu6;
	}
	public String getJikmu7() {
		return Jikmu7;
	}
	public void setJikmu7(String jikmu7) {
		Jikmu7 = jikmu7;
	}
	public String getJikmu8() {
		return Jikmu8;
	}
	public void setJikmu8(String jikmu8) {
		Jikmu8 = jikmu8;
	}
	public String getJikmu9() {
		return Jikmu9;
	}
	public void setJikmu9(String jikmu9) {
		Jikmu9 = jikmu9;
	}
	public String getJikmu10() {
		return Jikmu10;
	}
	public void setJikmu10(String jikmu10) {
		Jikmu10 = jikmu10;
	}
	public String getDutyNm() {
		return DutyNm;
	}
	public void setDutyNm(String dutyNm) {
		DutyNm = dutyNm;
	}
	public String getDeptNm() {
		return DeptNm;
	}
	public void setDeptNm(String deptNm) {
		DeptNm = deptNm;
	}
	public String getOrgTypeCd() {
		return OrgTypeCd;
	}
	public void setOrgTypeCd(String orgTypeCd) {
		OrgTypeCd = orgTypeCd;
	}
	public String getRk1() {
		return Rk1;
	}
	public void setRk1(String rk1) {
		Rk1 = rk1;
	}
	public String getRk2() {
		return Rk2;
	}
	public void setRk2(String rk2) {
		Rk2 = rk2;
	}
	public String getRk3() {
		return Rk3;
	}
	public void setRk3(String rk3) {
		Rk3 = rk3;
	}
	public String getRk4() {
		return Rk4;
	}
	public void setRk4(String rk4) {
		Rk4 = rk4;
	}
	public String getRk5() {
		return Rk5;
	}
	public void setRk5(String rk5) {
		Rk5 = rk5;
	}
	public String getRk6() {
		return Rk6;
	}
	public void setRk6(String rk6) {
		Rk6 = rk6;
	}
	public String getRk7() {
		return Rk7;
	}
	public void setRk7(String rk7) {
		Rk7 = rk7;
	}
	public String getRk8() {
		return Rk8;
	}
	public void setRk8(String rk8) {
		Rk8 = rk8;
	}
	public String getRk9() {
		return Rk9;
	}
	public void setRk9(String rk9) {
		Rk9 = rk9;
	}
	public String getRk10() {
		return Rk10;
	}
	public void setRk10(String rk10) {
		Rk10 = rk10;
	}
}