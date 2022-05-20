package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class CrawlAcdVO {
	private String Seq;
	private String AccidentDate;
	private String Subject;
	private String AccidentType;
	private String Source;
	private String RequestNo;
	private String Link;
	private String RegDate;
	private String EmpNo;
	private String StartDate;
	private String EndDate;
	
	public String getSeq() {
		return Seq;
	}
	public String getAccidentDate() {
		return AccidentDate;
	}
	public String getSubject() {
		return Subject;
	}
	public String getAccidentType() {
		return AccidentType;
	}
	public String getSource() {
		return Source;
	}
	public String getRequestNo() {
		return RequestNo;
	}
	public String getLink() {
		return Link;
	}
	public String getRegDate() {
		return RegDate;
	}	
	public String getEmpNo() {
		return EmpNo;
	}
	public String getStartDate() {
		return StartDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setSeq(String seq) {
		Seq = seq;
	}
	public void setAccidentDate(String accidentDate) {
		AccidentDate = accidentDate;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public void setAccidentType(String accidentType) {
		AccidentType = accidentType;
	}
	public void setSource(String source) {
		Source = source;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public void setLink(String link) {
		Link = link;
	}	
	public void setRegDate(String regDate) {
		RegDate = regDate;
	}	
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}	
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
}