package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class CrawlPersonInfoVO {
	private String MenuId;
	private String RequestNo;
	private String OrgNm;
	private String DeptNm;
	private String UserNm;
	private String DutyNm;
	private String Tel;
	private String Task;
	
	public String getMenuId() {
		return MenuId;
	}
	public String getRequestNo() {
		return RequestNo;
	}
	public String getOrgNm() {
		return OrgNm;
	}
	public String getDeptNm() {
		return DeptNm;
	}
	public String getUserNm() {
		return UserNm;
	}
	public String getDutyNm() {
		return DutyNm;
	}
	public String getTel() {
		return Tel;
	}
	public String getTask() {
		return Task;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public void setOrgNm(String orgNm) {
		OrgNm = orgNm;
	}
	public void setDeptNm(String deptNm) {
		DeptNm = deptNm;
	}
	public void setUserNm(String userNm) {
		UserNm = userNm;
	}
	public void setDutyNm(String dutyNm) {
		DutyNm = dutyNm;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public void setTask(String task) {
		Task = task;
	}	
}