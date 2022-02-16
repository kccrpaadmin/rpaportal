package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class CodeVO {
	private String ProcedureNm;
	private String Cd;
	private String CdNm;
	private String UpCd;
	private String Lvl;
	private String Ord;
	private String UseYn;
	private String Nm;
	private String ParentCd;
	private String ChildYn;
	private String OpenYn;
	private String ViewNode;
	private String Status;
	private String EmpNo;
	
	public String getProcedureNm() {
		return ProcedureNm;
	}
	public void setProcedureNm(String procedureNm) {
		ProcedureNm = procedureNm;
	}
	public String getCd() {
		return Cd;
	}
	public void setCd(String cd) {
		Cd = cd;
	}
	public String getCdNm() {
		return CdNm;
	}
	public void setCdNm(String cdNm) {
		CdNm = cdNm;
	}
	public String getUpCd() {
		return UpCd;
	}
	public void setUpCd(String upCd) {
		UpCd = upCd;
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
	public String getUseYn() {
		return UseYn;
	}
	public void setUseYn(String useYn) {
		UseYn = useYn;
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
	public String getViewNode() {
		return ViewNode;
	}
	public void setViewNode(String viewNode) {
		ViewNode = viewNode;
	}		
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}	
	public String getEmpNo() {
		return EmpNo;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
}