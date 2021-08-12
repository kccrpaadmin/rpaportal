package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class AccessVO21111 {
	private String UserId;
	private String RoleType;
	private String AccessUrl;
	private String AccessIp;
	private String AccessDevice;
	private String Params;
	private String LoginYn;
	private String EmpNo;
	private String Pwd;
	
	public String getUserId() {
		return UserId;
	}
	public String getRoleType() {
		return RoleType;
	}
	public String getAccessUrl() {
		return AccessUrl;
	}
	public String getAccessIp() {
		return AccessIp;
	}
	public String getAccessDevice() {
		return AccessDevice;
	}
	public String getParams() {
		return Params;
	}
	public String getLoginYn() {
		return LoginYn;
	}
	public String getEmpNo() {
		return EmpNo;
	}
	public String getPwd() {
		return Pwd;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public void setRoleType(String roleType) {
		RoleType = roleType;
	}
	public void setAccessUrl(String accessUrl) {
		AccessUrl = accessUrl;
	}
	public void setAccessIp(String accessIp) {
		AccessIp = accessIp;
	}
	public void setAccessDevice(String accessDevice) {
		AccessDevice = accessDevice;
	}
	public void setParams(String params) {
		Params = params;
	}
	public void setLoginYn(String loginYn) {
		LoginYn = loginYn;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}	
}