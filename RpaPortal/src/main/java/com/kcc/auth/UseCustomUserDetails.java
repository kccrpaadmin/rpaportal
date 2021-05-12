package com.kcc.auth;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UseCustomUserDetails implements UserDetails {
	private static final Logger logger = LoggerFactory.getLogger(UseCustomUserDetails.class);
	private static final long serialVersionUID = 1L;

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public String getPassword() {
		return null;
	}
}