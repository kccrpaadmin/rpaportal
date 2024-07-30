package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BotAutoCADCancelVO {
	private String RequestNo;
	private String Email;
	private String Offering_name;
	private String Assigned_date;
	private String Last_accessed;
	private String RequestDate;
	private String RequestType;
	private String DeptNm;
	private String UserNm;
	
	public String getRequestNo() {
		return RequestNo;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getOffering_name() {
		return Offering_name;
	}
	public void setOffering_name(String offering_name) {
		Offering_name = offering_name;
	}
	
	public String getAssigned_date() {
		return Assigned_date;
	}
	public void setAssigned_date(String assigned_date) {
		Assigned_date = assigned_date;
	}
	public String getLast_accessed() {
		return Last_accessed;
	}
	public void setLast_accessed(String last_accessed) {
		Last_accessed = last_accessed;
	}
	public String getRequestDate() {
		return RequestDate;
	}
	public void setRequestDate(String requestDate) {
		RequestDate = requestDate;
	}
	public String getRequestType() {
		return RequestType;
	}
	public void setRequestType(String requestType) {
		RequestType = requestType;
	}
	public String getDeptNm() {
		return DeptNm;
	}
	public void setDeptNm(String deptNm) {
		DeptNm = deptNm;
	}
	public String getUserNm() {
		return UserNm;
	}
	public void setUserNm(String userNm) {
		UserNm = userNm;
	}


}