package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BotBidChangeVO {
	private String MenuId;
	private String ConstNm;
	private String ManageNo;
	private String BidNo;
	private String ReceiveDate;
	private String StartReceiveDate;
	private String EndReceiveDate;
	private String OrderAgency;
	private String ContractType;
	private String ContractProperty;
	private String TotalContractAmt;
	private String RegJobType;
	private String ContractAmt;
	private String RegJobTypeDept;
	private String UserId;
	private String EmpNo;
		
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public String getConstNm() {
		return ConstNm;
	}
	public void setConstNm(String constNm) {
		ConstNm = constNm;
	}
	public String getManageNo() {
		return ManageNo;
	}
	public void setManageNo(String manageNo) {
		ManageNo = manageNo;
	}
	public String getBidNo() {
		return BidNo;
	}
	public void setBidNo(String bidNo) {
		BidNo = bidNo;
	}
	public String getReceiveDate() {
		return ReceiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		ReceiveDate = receiveDate;
	}
	public String getStartReceiveDate() {
		return StartReceiveDate;
	}
	public void setStartReceiveDate(String startReceiveDate) {
		StartReceiveDate = startReceiveDate;
	}
	public String getEndReceiveDate() {
		return EndReceiveDate;
	}
	public void setEndReceiveDate(String endReceiveDate) {
		EndReceiveDate = endReceiveDate;
	}
	public String getOrderAgency() {
		return OrderAgency;
	}
	public void setOrderAgency(String orderAgency) {
		OrderAgency = orderAgency;
	}
	public String getContractType() {
		return ContractType;
	}
	public void setContractType(String contractType) {
		ContractType = contractType;
	}
	public String getContractProperty() {
		return ContractProperty;
	}
	public void setContractProperty(String contractProperty) {
		ContractProperty = contractProperty;
	}
	public String getTotalContractAmt() {
		return TotalContractAmt;
	}
	public void setTotalContractAmt(String totalContractAmt) {
		TotalContractAmt = totalContractAmt;
	}
	public String getRegJobType() {
		return RegJobType;
	}
	public void setRegJobType(String regJobType) {
		RegJobType = regJobType;
	}
	public String getContractAmt() {
		return ContractAmt;
	}
	public void setContractAmt(String contractAmt) {
		ContractAmt = contractAmt;
	}
	public String getRegJobTypeDept() {
		return RegJobTypeDept;
	}
	public void setRegJobTypeDept(String regJobTypeDept) {
		RegJobTypeDept = regJobTypeDept;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}	
	public String getEmpNo() {
		return EmpNo;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
}