package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId")

public class UserVO {
	private String RoleType;
	private String UserId;
	private String Pwd;
	private String UserNm;
	private String Email;
	private String DeptCd;
	private String DeptNm;
	private String TitleCd;
	private String TitleNm;
	private String DutyCd;
	private String DutyNm;
	private String EmpNo;
	private String CompTel;
	private String CompAddr;
	private String Tel;
	private String Mobile;
	private String Addr;
	private String UserSignUrl;
	private String UserPhotoUrl;
	private String JobNm;
	private String DbServer;
	
	public String getRoleType() {
		return RoleType;
	}
	public String getUserId() {
		return UserId;
	}
	public String getPwd() {
		return Pwd;
	}
	public String getUserNm() {
		return UserNm;
	}
	public String getEmail() {
		return Email;
	}
	public String getDeptCd() {
		return DeptCd;
	}
	public String getDeptNm() {
		return DeptNm;
	}
	public String getTitleCd() {
		return TitleCd;
	}
	public String getTitleNm() {
		return TitleNm;
	}
	public String getDutyCd() {
		return DutyCd;
	}
	public String getDutyNm() {
		return DutyNm;
	}
	public String getEmpNo() {
		return EmpNo;
	}
	public String getCompTel() {
		return CompTel;
	}
	public String getCompAddr() {
		return CompAddr;
	}
	public String getTel() {
		return Tel;
	}
	public String getMobile() {
		return Mobile;
	}
	public String getAddr() {
		return Addr;
	}
	public String getUserSignUrl() {
		return UserSignUrl;
	}
	public String getUserPhotoUrl() {
		return UserPhotoUrl;
	}
	public String getJobNm() {
		return JobNm;
	}
	public String getDbServer() {
		return DbServer;
	}
	public void setRoleType(String roleType) {
		RoleType = roleType;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	public void setUserNm(String userNm) {
		UserNm = userNm;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public void setDeptCd(String deptCd) {
		DeptCd = deptCd;
	}
	public void setDeptNm(String deptNm) {
		DeptNm = deptNm;
	}
	public void setTitleCd(String titleCd) {
		TitleCd = titleCd;
	}
	public void setTitleNm(String titleNm) {
		TitleNm = titleNm;
	}
	public void setDutyCd(String dutyCd) {
		DutyCd = dutyCd;
	}
	public void setDutyNm(String dutyNm) {
		DutyNm = dutyNm;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
	public void setCompTel(String compTel) {
		CompTel = compTel;
	}
	public void setCompAddr(String compAddr) {
		CompAddr = compAddr;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public void setAddr(String addr) {
		Addr = addr;
	}
	public void setUserSignUrl(String userSignUrl) {
		UserSignUrl = userSignUrl;
	}
	public void setUserPhotoUrl(String userPhotoUrl) {
		UserPhotoUrl = userPhotoUrl;
	}
	public void setJobNm(String jobNm) {
		JobNm = jobNm;
	}
	public void setDbServer(String dbServer) {
		DbServer = dbServer;
	}	
}