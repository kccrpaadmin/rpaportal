package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BotKisconConstVO {
	private String RequestNo;
	private String ContractNo;
	private String SiteNm;
	private String ContractNm;
	private String VendorNm;
	private String ContractDate;
	private String ContractChgReason;
	private String ContractAmt;
	private String ContractStartDate;
	private String ContractFinishDate;
	private String GuaranteeOrgNm;
	private String GuaranteeDrawDate;
	private String GuaranteePolicyNo;
	private String GuaranteeDepAmt;
	private String SuccessType;
	private String ErrorMsg;
	private String NotificationCloseDate;	
	private String PricePaymentYn;	
	private String RegJobTypeNm;
	private String LicenseType;
	
	public String getRequestNo() {
		return RequestNo;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public String getContractNo() {
		return ContractNo;
	}
	public void setContractNo(String contractNo) {
		ContractNo = contractNo;
	}
	public String getSiteNm() {
		return SiteNm;
	}
	public void setSiteNm(String siteNm) {
		SiteNm = siteNm;
	}
	public String getContractNm() {
		return ContractNm;
	}
	public void setContractNm(String contractNm) {
		ContractNm = contractNm;
	}
	public String getVendorNm() {
		return VendorNm;
	}
	public void setVendorNm(String vendorNm) {
		VendorNm = vendorNm;
	}
	public String getContractDate() {
		return ContractDate;
	}
	public void setContractDate(String contractDate) {
		ContractDate = contractDate;
	}
	public String getContractChgReason() {
		return ContractChgReason;
	}
	public void setContractChgReason(String contractChgReason) {
		ContractChgReason = contractChgReason;
	}
	public String getContractAmt() {
		return ContractAmt;
	}
	public void setContractAmt(String contractAmt) {
		ContractAmt = contractAmt;
	}
	public String getContractStartDate() {
		return ContractStartDate;
	}
	public void setContractStartDate(String contractStartDate) {
		ContractStartDate = contractStartDate;
	}
	public String getContractFinishDate() {
		return ContractFinishDate;
	}
	public void setContractFinishDate(String contractFinishDate) {
		ContractFinishDate = contractFinishDate;
	}
	public String getGuaranteeOrgNm() {
		return GuaranteeOrgNm;
	}
	public void setGuaranteeOrgNm(String guaranteeOrgNm) {
		GuaranteeOrgNm = guaranteeOrgNm;
	}
	public String getGuaranteeDrawDate() {
		return GuaranteeDrawDate;
	}
	public void setGuaranteeDrawDate(String guaranteeDrawDate) {
		GuaranteeDrawDate = guaranteeDrawDate;
	}
	public String getGuaranteePolicyNo() {
		return GuaranteePolicyNo;
	}
	public void setGuaranteePolicyNo(String guaranteePolicyNo) {
		GuaranteePolicyNo = guaranteePolicyNo;
	}
	public String getGuaranteeDepAmt() {
		return GuaranteeDepAmt;
	}
	public void setGuaranteeDepAmt(String guaranteeDepAmt) {
		GuaranteeDepAmt = guaranteeDepAmt;
	}
	public String getSuccessType() {
		return SuccessType;
	}
	public void setSuccessType(String successType) {
		SuccessType = successType;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	public String getNotificationCloseDate() {
		return NotificationCloseDate;
	}
	public void setNotificationCloseDate(String notificationCloseDate) {
		NotificationCloseDate = notificationCloseDate;
	}
	public String getPricePaymentYn() {
		return PricePaymentYn;
	}
	public void setPricePaymentYn(String pricePaymentYn) {
		PricePaymentYn = pricePaymentYn;
	}
	public String getRegJobTypeNm() {
		return RegJobTypeNm;
	}
	public void setRegJobTypeNm(String regJobTypeNm) {
		RegJobTypeNm = regJobTypeNm;
	}
	public String getLicenseType() {
		return LicenseType;
	}
	public void setLicenseType(String licenseType) {
		LicenseType = licenseType;
	}
}