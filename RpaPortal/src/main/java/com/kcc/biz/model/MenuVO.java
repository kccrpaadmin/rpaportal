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
	private String TaskTypeCd;
	private String TaskTypeNm;
	private String ExecTypeCd;
	private String ExecTypeNm;
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
	private String ViewNode;
	private String Cd;
	private String Nm;
	private String ParentCd;
	private String ChildYn;
	private String OpenYn;
	private String Status;
	private String AuthTypeCdNm;
	private String AuthTypeNm;
	private String UserNm;
	private String MenuType;
    private String EpUserId;
    private String DegreeCd;
    private String DegreeNm;
    private String BtnAddDept;
    private String DutyCd;
	
	public String getSearchTxt() {
		return SearchTxt;
	}
	public void setSearchTxt(String searchTxt) {
		SearchTxt = searchTxt;
	}
	public String getUrlType() {
		return UrlType;
	}
	public void setUrlType(String urlType) {
		UrlType = urlType;
	}
	public String getEmpNo() {
		return EmpNo;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public String getMenuNm() {
		return MenuNm;
	}
	public void setMenuNm(String menuNm) {
		MenuNm = menuNm;
	}
	public String getUpMenuId() {
		return UpMenuId;
	}
	public void setUpMenuId(String upMenuId) {
		UpMenuId = upMenuId;
	}
	public String getLvl() {
		return Lvl;
	}
	public void setLvl(String lvl) {
		Lvl = lvl;
	}
	public String getOrd() {
		return Ord;
	}
	public void setOrd(String ord) {
		Ord = ord;
	}
	public String getDeptCd() {
		return DeptCd;
	}
	public void setDeptCd(String deptCd) {
		DeptCd = deptCd;
	}
	public String getDeptNm() {
		return DeptNm;
	}
	public void setDeptNm(String deptNm) {
		DeptNm = deptNm;
	}
	public String getWorkTypeCd() {
		return WorkTypeCd;
	}
	public void setWorkTypeCd(String workTypeCd) {
		WorkTypeCd = workTypeCd;
	}
	public String getWorkTypeNm() {
		return WorkTypeNm;
	}
	public void setWorkTypeNm(String workTypeNm) {
		WorkTypeNm = workTypeNm;
	}
	public String getTimeTypeCd() {
		return TimeTypeCd;
	}
	public void setTimeTypeCd(String timeTypeCd) {
		TimeTypeCd = timeTypeCd;
	}
	public String getTimeTypeNm() {
		return TimeTypeNm;
	}
	public void setTimeTypeNm(String timeTypeNm) {
		TimeTypeNm = timeTypeNm;
	}
	public String getRunSeq() {
		return RunSeq;
	}
	public void setRunSeq(String runSeq) {
		RunSeq = runSeq;
	}
	public String getRunTime() {
		return RunTime;
	}
	public void setRunTime(String runTime) {
		RunTime = runTime;
	}
	public String getTaskTypeCd() {
		return TaskTypeCd;
	}
	public void setTaskTypeCd(String taskTypeCd) {
		TaskTypeCd = taskTypeCd;
	}
	public String getTaskTypeNm() {
		return TaskTypeNm;
	}
	public void setTaskTypeNm(String taskTypeNm) {
		TaskTypeNm = taskTypeNm;
	}
	public String getExecTypeCd() {
		return ExecTypeCd;
	}
	public void setExecTypeCd(String execTypeCd) {
		ExecTypeCd = execTypeCd;
	}
	public String getExecTypeNm() {
		return ExecTypeNm;
	}
	public void setExecTypeNm(String execTypeNm) {
		ExecTypeNm = execTypeNm;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getRunUrl() {
		return RunUrl;
	}
	public void setRunUrl(String runUrl) {
		RunUrl = runUrl;
	}
	public String getManageUrl() {
		return ManageUrl;
	}
	public void setManageUrl(String manageUrl) {
		ManageUrl = manageUrl;
	}
	public String getUseYn() {
		return UseYn;
	}
	public void setUseYn(String useYn) {
		UseYn = useYn;
	}
	public String getAuthTypeCd() {
		return AuthTypeCd;
	}
	public void setAuthTypeCd(String authTypeCd) {
		AuthTypeCd = authTypeCd;
	}
	public String getAuthType() {
		return AuthType;
	}
	public void setAuthType(String authType) {
		AuthType = authType;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getTitleCd() {
		return TitleCd;
	}
	public void setTitleCd(String titleCd) {
		TitleCd = titleCd;
	}
	public String getAuthYn() {
		return AuthYn;
	}
	public void setAuthYn(String authYn) {
		AuthYn = authYn;
	}
	public String getRequestNo() {
		return RequestNo;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public String getStatusCd() {
		return StatusCd;
	}
	public void setStatusCd(String statusCd) {
		StatusCd = statusCd;
	}
	public String getStatusNm() {
		return StatusNm;
	}
	public void setStatusNm(String statusNm) {
		StatusNm = statusNm;
	}
	public String getRegUserId() {
		return RegUserId;
	}
	public void setRegUserId(String regUserId) {
		RegUserId = regUserId;
	}
	public String getRegUserNm() {
		return RegUserNm;
	}
	public void setRegUserNm(String regUserNm) {
		RegUserNm = regUserNm;
	}
	public String getRegDate() {
		return RegDate;
	}
	public void setRegDate(String regDate) {
		RegDate = regDate;
	}
	public String getChgDate() {
		return ChgDate;
	}
	public void setChgDate(String chgDate) {
		ChgDate = chgDate;
	}
	public String getMenuDetailNm() {
		return MenuDetailNm;
	}
	public void setMenuDetailNm(String menuDetailNm) {
		MenuDetailNm = menuDetailNm;
	}
	public String getMenuDetailUrl() {
		return MenuDetailUrl;
	}
	public void setMenuDetailUrl(String menuDetailUrl) {
		MenuDetailUrl = menuDetailUrl;
	}	
	public String getViewNode() {
		return ViewNode;
	}
	public void setViewNode(String viewNode) {
		ViewNode = viewNode;
	}	
	public String getCd() {
		return Cd;
	}
	public void setCd(String cd) {
		Cd = cd;
	}	
	public String getNm() {
		return Nm;
	}
	public void setNm(String nm) {
		Nm = nm;
	}	
	public String getParentCd() {
		return ParentCd;
	}
	public void setParentCd(String parentCd) {
		ParentCd = parentCd;
	}	
	public String getChildYn() {
		return ChildYn;
	}
	public void setChildYn(String childYn) {
		ChildYn = childYn;
	}	
	public String getOpenYn() {
		return OpenYn;
	}
	public void setOpenYn(String openYn) {
		OpenYn = openYn;
	}	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getAuthTypeCdNm() {
		return AuthTypeCdNm;
	}
	public void setAuthTypeCdNm(String authTypeCdNm) {
		AuthTypeCdNm = authTypeCdNm;
	}	
	public String getAuthTypeNm() {
		return AuthTypeNm;
	}
	public void setAuthTypeNm(String authTypeNm) {
		AuthTypeNm = authTypeNm;
	}	
	public String getUserNm() {
		return UserNm;
	}
	public void setUserNm(String userNm) {
		UserNm = userNm;
	}	
	public String getMenuType() {
		return MenuType;
	}
	public void setMenuType(String menuType) {
		MenuType = menuType;
	}	
	public String getEpUserId() {
		return EpUserId;
	}
	public void setEpUserId(String epUserId) {
		EpUserId = epUserId;
	}	
	public String getDegreeCd() {
		return DegreeCd;
	}
	public void setDegreeCd(String degreeCd) {
		DegreeCd = degreeCd;
	}	
	public String getDegreeNm() {
		return DegreeNm;
	}
	public void setDegreeNm(String degreeNm) {
		DegreeNm = degreeNm;
	}	
	public String getBtnAddDept() {
		return BtnAddDept;
	}
	public void setBtnAddDept(String btnAddDept) {
		BtnAddDept = btnAddDept;
	}		
	public String getDutyCd() {
		return DutyCd;
	}
	public void setDutyCd(String dutyCd) {
		DutyCd = dutyCd;
	}	
	
}