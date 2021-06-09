package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId")

public class MenuVO {
	private String SearchTxt;
	private String UrlType;
	private String EmpNo;
	private String MenuId;
	private String MenuNm;
	private String UpMenuId;
	private String Lvl;
	private String Ord;
	private String DeptCd;
	private String DeptNm;
	private String WorkTypeCd;
	private String WorkTypeNm;
	private String TimeTypeCd;
	private String TimeTypeNm;
	private String RunSeq;
	private String RunTime;
	// 추가컬럼 있음 ExecTypeCd	
	private String Content;
	private String RunUrl;
	private String ManageUrl;
	private String UseYn;
	private String AuthTypeCd;
	private String AuthType;
	private String UserId;
	private String TitleCd;
	private String AuthYn;
	private String RequestNo;
	private String StatusCd;
	private String StatusNm;
	private String RegUserId;
	private String RegUserNm;
	private String RegDate;
	private String ChgDate;
	private String MenuDetailNm;
	private String MenuDetailUrl;
	
	public String getSearchTxt() {
		return SearchTxt;
	}
	public String getUrlType() {
		return UrlType;
	}
	public String getEmpNo() {
		return EmpNo;
	}
	public String getMenuId() {
		return MenuId;
	}
	public String getMenuNm() {
		return MenuNm;
	}
	public String getUpMenuId() {
		return UpMenuId;
	}
	public String getLvl() {
		return Lvl;
	}
	public String getOrd() {
		return Ord;
	}
	public String getDeptCd() {
		return DeptCd;
	}
	public String getDeptNm() {
		return DeptNm;
	}
	public String getWorkTypeCd() {
		return WorkTypeCd;
	}
	public String getWorkTypeNm() {
		return WorkTypeNm;
	}
	public String getTimeTypeCd() {
		return TimeTypeCd;
	}
	public String getTimeTypeNm() {
		return TimeTypeNm;
	}
	public String getRunSeq() {
		return RunSeq;
	}
	public String getRunTime() {
		return RunTime;
	}
	public String getContent() {
		return Content;
	}
	public String getRunUrl() {
		return RunUrl;
	}
	public String getManageUrl() {
		return ManageUrl;
	}
	public String getUseYn() {
		return UseYn;
	}
	public String getAuthTypeCd() {
		return AuthTypeCd;
	}
	public String getAuthType() {
		return AuthType;
	}
	public String getUserId() {
		return UserId;
	}
	public String getTitleCd() {
		return TitleCd;
	}
	public String getAuthYn() {
		return AuthYn;
	}
	public String getRequestNo() {
		return RequestNo;
	}
	public String getStatusCd() {
		return StatusCd;
	}
	public String getStatusNm() {
		return StatusNm;
	}
	public String getRegUserId() {
		return RegUserId;
	}
	public String getRegUserNm() {
		return RegUserNm;
	}
	public String getRegDate() {
		return RegDate;
	}
	public String getChgDate() {
		return ChgDate;
	}
	public String getMenuDetailNm() {
		return MenuDetailNm;
	}
	public String getMenuDetailUrl() {
		return MenuDetailUrl;
	}
	public void setSearchTxt(String searchTxt) {
		SearchTxt = searchTxt;
	}
	public void setUrlType(String urlType) {
		UrlType = urlType;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public void setMenuNm(String menuNm) {
		MenuNm = menuNm;
	}
	public void setUpMenuId(String upMenuId) {
		UpMenuId = upMenuId;
	}
	public void setLvl(String lvl) {
		Lvl = lvl;
	}
	public void setOrd(String ord) {
		Ord = ord;
	}
	public void setDeptCd(String deptCd) {
		DeptCd = deptCd;
	}
	public void setDeptNm(String deptNm) {
		DeptNm = deptNm;
	}
	public void setWorkTypeCd(String workTypeCd) {
		WorkTypeCd = workTypeCd;
	}
	public void setWorkTypeNm(String workTypeNm) {
		WorkTypeNm = workTypeNm;
	}
	public void setTimeTypeCd(String timeTypeCd) {
		TimeTypeCd = timeTypeCd;
	}
	public void setTimeTypeNm(String timeTypeNm) {
		TimeTypeNm = timeTypeNm;
	}
	public void setRunSeq(String runSeq) {
		RunSeq = runSeq;
	}
	public void setRunTime(String runTime) {
		RunTime = runTime;
	}
	public void setContent(String content) {
		Content = content;
	}
	public void setRunUrl(String runUrl) {
		RunUrl = runUrl;
	}
	public void setManageUrl(String manageUrl) {
		ManageUrl = manageUrl;
	}
	public void setUseYn(String useYn) {
		UseYn = useYn;
	}
	public void setAuthTypeCd(String authTypeCd) {
		AuthTypeCd = authTypeCd;
	}
	public void setAuthType(String authType) {
		AuthType = authType;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public void setTitleCd(String titleCd) {
		TitleCd = titleCd;
	}
	public void setAuthYn(String authYn) {
		AuthYn = authYn;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public void setStatusCd(String statusCd) {
		StatusCd = statusCd;
	}
	public void setStatusNm(String statusNm) {
		StatusNm = statusNm;
	}
	public void setRegUserId(String regUserId) {
		RegUserId = regUserId;
	}
	public void setRegUserNm(String regUserNm) {
		RegUserNm = regUserNm;
	}
	public void setRegDate(String regDate) {
		RegDate = regDate;
	}
	public void setChgDate(String chgDate) {
		ChgDate = chgDate;
	}
	public void setMenuDetailNm(String menuDetailNm) {
		MenuDetailNm = menuDetailNm;
	}
	public void setMenuDetailUrl(String menuDetailUrl) {
		MenuDetailUrl = menuDetailUrl;
	}	
}