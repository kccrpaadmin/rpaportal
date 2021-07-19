package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BotMoneySendVO {
	private String MenuId;
	private String RequestNo;
	private String EmpNo;
	private String SendDate;
	private String RemitBankNm;
	private String RemitAccountNo;
	private String RemitteeNm;
	private String SendAmt;
	private String SendFee;
	private String SendBankNm;
	private String SendAccountNo;
	private String SerialNo;
	private String Ord;
	private String VendorCd;
	private String VendorNm;
	private String BizNo;
	private String InvoiceDate;
	private String GlDate;
	private String TransDate;
	private String InvoiceDescription;
	private String InvoiceAmount;
	private String Amount;
	private String AttId;
	private String BtnPrint;
	private String AttFilePath;
	private String CheckId;
	
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public String getRequestNo() {
		return RequestNo;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public String getEmpNo() {
		return EmpNo;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
	public String getSendDate() {
		return SendDate;
	}
	public void setSendDate(String sendDate) {
		SendDate = sendDate;
	}
	public String getRemitBankNm() {
		return RemitBankNm;
	}
	public void setRemitBankNm(String remitBankNm) {
		RemitBankNm = remitBankNm;
	}
	public String getRemitAccountNo() {
		return RemitAccountNo;
	}
	public void setRemitAccountNo(String remitAccountNo) {
		RemitAccountNo = remitAccountNo ;
	}
	public String getRemitteeNm() {
		return RemitteeNm;
	}
	public void setRemitteeNm(String remitteeNm) {
		RemitteeNm = remitteeNm;
	}
	public String getSendAmt() {
		return SendAmt;
	}
	public void setSendAmt(String sendAmt) {
		SendAmt = sendAmt;
	}
	public String getSendFee() {
		return SendFee;
	}
	public void setSendFee(String sendFee) {
		SendFee = sendFee;
	}
	public String getSendBankNm() {
		return SendBankNm;
	}
	public void setSendBankNm(String sendBankNm) {
		SendBankNm = sendBankNm;
	}
	public String getSendAccountNo() {
		return SendAccountNo;
	}
	public void setSendAccountNo(String sendAccountNo) {
		SendAccountNo = sendAccountNo;
	}
	public String getSerialNo() {
		return SerialNo;
	}
	public void setSerialNo(String serialNo) {
		SerialNo = serialNo;
	}
	public String getOrd() {
		return Ord;
	}
	public void setOrd (String ord) {
		Ord = ord;
	}
	public String getVendorCd() {
		return VendorCd;
	}
	public void setVendorCd (String vendorCd) {
		VendorCd = vendorCd;
	}
	public String getVendorNm() {
		return VendorNm;
	}
	public void setVendorNm (String vendorNm) {
		VendorNm = vendorNm;
	}	
	public String getBizNo() {
		return BizNo;
	}
	public void setBizNo (String bizNo) {
		BizNo = bizNo;
	}	
	public String getGlDate() {
		return GlDate;
	}
	public void setGlDate (String glDate) {
		GlDate = glDate;
	}
	public String getInvoiceDate() {
		return InvoiceDate;
	}
	public void setInvoiceDate (String invoiceDate) {
		InvoiceDate = invoiceDate;
	}
	public String getTransDate() {
		return TransDate;
	}
	public void setTransDate (String transDate) {
		TransDate = transDate;
	}
	public String getInvoiceDescription() {
		return InvoiceDescription;
	}
	public void setInvoiceDescription (String invoiceDescription) {
		InvoiceDescription = invoiceDescription;
	}
	public String getInvoiceAmount() {
		return InvoiceAmount;
	}
	public void setInvoiceAmount (String invoiceAmount) {
		InvoiceAmount = invoiceAmount;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount (String amount) {
		Amount = amount;
	}
	public String getAttId() {
		return AttId;
	}
	public void setAttId (String attId) {
		AttId = attId;
	}
	public String getBtnPrint() {
		return BtnPrint;
	}
	public void setBtnPrint (String btnPrint) {
		BtnPrint = btnPrint;
	}
	public String getAttFilePath() {
		return AttFilePath;
	}
	public void setAttFilePath (String attFilePath) {
		AttFilePath = attFilePath;
	}
	public String getCheckId() {
		return CheckId;
	}
	public void setCheckId (String checkId) {
		CheckId = checkId;
	}
}