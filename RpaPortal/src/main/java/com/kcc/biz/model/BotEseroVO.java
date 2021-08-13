package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BotEseroVO {
	private String MenuId;
	private String RequestNo;
	private String EmpNo;
	private String YearMon;
	private String EseroTargetDate;
	private String IssueNo;
	private String InvoiceTaxType;
	private String MakeDate;
	private String DrawDate;
	private String SendDate;
	private String SalesBizNo;
	private String SalesPlaceNo;
	private String SalesVendorNm;
	private String SalesRepNm;
	private String SalesAddr;
	private String BuyBizNo;
	private String BuyPlaceNo;
	private String BuyVendorNm;
	private String BuyRepNm;
	private String BuyAddr;
	private String TotAmt;
	private String SupplyAmt;
	private String VatAmt;
	private String InvoiceType;
	private String InvoiceKind;
	private String InvoiceSource;
	private String Etc;
	private String ReqResType;
	private String SalesEmail;
	private String BuyEmail1;
	private String BuyEmail2;
	private String ItemDate;
	private String ItemNm;
	private String ItemUnit;
	private String ItemQty;
	private String ItemCost;
	private String ItemSupplyAmt;
	private String ItemVatAmt;
	private String ItemEtc;
	private String SlipNo;
	private String DeptNm;
	private String PaymentDate;
	private String BizNo;
	private String VendorNm;
	private String Summary;
	private String CorpType;
	private String MergeSlipNo;
	private String BuySalesType;
	private String ConfirmDate;
	private String OriSlipNo;
	private String OnOffType;
	private String InvoiceYearMon;
	private String InvoiceBizNo;
	private String VendorCd;
	private String InvoiceCnt;
	private String InvoiceSupplyAmt;
	private String InvoiceVatAmt;
	private String InvoiceTotAmt;
	private String SlipCnt;
	private String SlipSupplyAmt;
	private String SlipVatAmt;
	private String SlipTotAmt;
	private String ErrorYn;	
	private String SlipPaymentDate;
	private String SlipBizNo;
	private String SlipDrawDate;
	private String SlipIssueNo;
	private String InvoiceIssueNo;
	private String InvoiceMakeDate;
	private String IssueNoYn;
	private String BizNoYn;
	private String DrawDateYn;
	private String SupplyAmtYn;
	private String VatAmtYn;
	private String TotAmtYn;
	private String SlipId;
	private String GlDate;
	private String Addr;
	private String TelNo;
	private String UserMobileNo;		
	private String DutyNm;		
	private String UserId;		
	private String UserNm;		
	
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
	public String getYearMon() {
		return YearMon;
	}
	public void setYearMon(String yearMon) {
		YearMon = yearMon;
	}
	public String getEseroTargetDate() {
		return EseroTargetDate;
	}
	public void setEseroTargetDate(String eseroTargetDate) {
		EseroTargetDate = eseroTargetDate;
	}	
	public String getIssueNo()  {
		return IssueNo;
	}
	public void setIssueNo (String issueNo) {
		IssueNo = issueNo;
	}
	public String getInvoiceTaxType()  {
		return InvoiceTaxType;
	}
	public void setInvoiceTaxType (String invoiceTaxType) {
		InvoiceTaxType = invoiceTaxType;
	}
	public String getMakeDate()  {
		return MakeDate;
	}
	public void setMakeDate (String makeDate) {
		MakeDate = makeDate;
	}
	public String getDrawDate()  {
		return DrawDate;
	}
	public void setDrawDate (String drawDate) {
		DrawDate = drawDate;
	}
	public String getSendDate()  {
		return SendDate;
	}
	public void setSendDate (String sendDate) {
		SendDate = sendDate;
	}
	public String getSalesBizNo()  {
		return SalesBizNo;
	}
	public void setSalesBizNo (String salesBizNo) {
		SalesBizNo = salesBizNo;
	}
	public String getSalesPlaceNo() {
		return SalesPlaceNo;
	}
	public void setSalesPlaceNo (String salesPlaceNo) {
		SalesPlaceNo = salesPlaceNo;
	}
	public String getSalesVendorNm() {
		return SalesVendorNm;
	}
	public void setSalesVendorNm (String salesVendorNm) {
		SalesVendorNm = salesVendorNm;
	}
	public String getSalesRepNm() {
		return  SalesRepNm;
	}
	public void setSalesRepNm (String salesRepNm) {
		SalesRepNm = salesRepNm;
	}
	public String getSalesAddr() {
		return SalesAddr;
	}
	public void setSalesAddr (String salesAddr) {
		SalesAddr = salesAddr;
	}
	public String getBuyBizNo() {
		return BuyBizNo;
	}
	public void setBuyBizNo (String buyBizNo) {
		BuyBizNo = buyBizNo;
	}
	public String getBuyPlaceNo() {
		return BuyPlaceNo;
	}
	public void setBuyPlaceNo (String buyPlaceNo) {
		BuyPlaceNo = buyPlaceNo;
	}
	public String getBuyVendorNm() {
		return BuyVendorNm;
	}
	public void setBuyVendorNm (String buyVendorNm) {
		BuyVendorNm = buyVendorNm;
	}
	public String getBuyRepNm() {
		return BuyRepNm;
	}
	public void setBuyRepNm (String buyRepNm) {
		BuyRepNm = buyRepNm;
	}
	public String getBuyAddr() {
		return BuyAddr;
	}
	public void setBuyAddr (String buyAddr) {
		BuyAddr = buyAddr;
	}
	public String getTotAmt() {
		return TotAmt;
	}
	public void setTotAmt (String totAmt) {
		TotAmt = totAmt;
	}
	public String getSupplyAmt() {
		return SupplyAmt;
	}
	public void setSupplyAmt (String supplyAmt) {
		SupplyAmt = supplyAmt;
	}
	public String getVatAmt() {
		return VatAmt;
	}
	public void setVatAmt (String vatAmt) {
		VatAmt = vatAmt;
	}
	public String getInvoiceType() {
		return InvoiceType;
	}
	public void setInvoiceType (String invoiceType) {
		InvoiceType = invoiceType;
	}
	public String getInvoiceKind() {
		return InvoiceKind;
	}
	public void setInvoiceKind (String invoiceKind) {
		InvoiceKind = invoiceKind;
	}
	public String getInvoiceSource() {
		return InvoiceSource;
	}
	public void setInvoiceSource (String invoiceSource) {
		InvoiceSource = invoiceSource;
	}
	public String getEtc() {
		return Etc;
	}
	public void setEtc (String etc) {
		Etc = etc;
	}
	public String getReqResType() {
		return ReqResType;
	}
	public void setReqResType (String reqResType) {
		ReqResType = reqResType;
	}
	public String getSalesEmail() {
		return SalesEmail;
	}
	public void setSalesEmail (String salesEmail) {
		SalesEmail = salesEmail;
	}
	public String getBuyEmail1() {
		return BuyEmail1;
	}
	public void setBuyEmail1 (String buyEmail1) {
		BuyEmail1 = buyEmail1;
	}
	public String getBuyEmail2() {
		return BuyEmail2;
	}
	public void setBuyEmail2 (String buyEmail2) {
		BuyEmail2 = buyEmail2;
	}
	public String getItemDate() {
		return ItemDate;
	}
	public void setItemDate (String itemDate) {
		ItemDate = itemDate;
	}
	public String getItemNm() {
		return ItemNm;
	}
	public void setItemNm (String itemNm) {
		ItemNm = itemNm;
	}
	public String getItemUnit() {
		return ItemUnit;
	}
	public void setItemUnit (String itemUnit) {
		ItemUnit = itemUnit;
	}
	public String getItemQty() {
		return ItemQty;
	}
	public void setItemQty (String itemQty) {
		ItemQty = itemQty;
	}
	public String getItemCost() {
		return ItemCost;
	}
	public void setItemCost (String itemCost) {
		ItemCost = itemCost;
	}
	public String getItemSupplyAmt() {
		return ItemSupplyAmt;
	}
	public void setItemSupplyAmt (String itemSupplyAmt) {
		ItemSupplyAmt = itemSupplyAmt;
	}
	public String getItemVatAmt() {
		return ItemVatAmt;
	}
	public void setItemVatAmt (String itemVatAmt) {
		ItemVatAmt = itemVatAmt;
	}
	public String getItemEtc() {
		return ItemEtc;
	}
	public void setItemEtc (String itemEtc) {
		ItemEtc = itemEtc;
	}	
	public String getSlipNo() {
		return SlipNo;
	}
	public void setSlipNo (String slipNo) {
		SlipNo = slipNo;
	}	
	public String getDeptNm() {
		return DeptNm;
	}
	public void setDeptNm (String deptNm) {
		DeptNm = deptNm;
	}	
	public String getPaymentDate() {
		return PaymentDate;
	}
	public void setPaymentDate (String paymentDate) {
		PaymentDate = paymentDate;
	}		
	public String getBizNo() {
		return BizNo;
	}
	public void setBizNo (String bizNo) {
		BizNo = bizNo;
	}	
	public String getVendorNm() {
		return VendorNm;
	}
	public void setVendorNm (String vendorNm) {
		VendorNm = vendorNm;
	}	
	public String getSummary() {
		return Summary;
	}
	public void setSummary (String summary) {
		Summary = summary;
	}	
	public String getCorpType() {
		return CorpType;
	}
	public void setCorpType (String corpType) {
		CorpType = corpType;
	}	
	public String getMergeSlipNo() {
		return MergeSlipNo;
	}
	public void setMergeSlipNo (String mergeSlipNo) {
		MergeSlipNo = mergeSlipNo;
	}	
	public String getBuySalesType() {
		return BuySalesType;
	}
	public void setBuySalesType (String buySalesType) {
		BuySalesType = buySalesType;
	}	
	public String getConfirmDate() {
		return ConfirmDate;
	}
	public void setConfirmDate (String confirmDate) {
		ConfirmDate = confirmDate;
	}	
	public String getOriSlipNo() {
		return OriSlipNo;
	}
	public void setOriSlipNo (String oriSlipNo) {
		OriSlipNo = oriSlipNo;
	}	
	public String getOnOffType() {
		return OnOffType;
	}
	public void setOnOffType (String onOffType) {
		OnOffType = onOffType;
	}	
	public String getInvoiceYearMon() {
		return InvoiceYearMon;
	}
	public void setInvoiceYearMon (String invoiceYearMon) {
		InvoiceYearMon = invoiceYearMon;
	}	
	public String getInvoiceBizNo() {
		return InvoiceBizNo;
	}
	public void setInvoiceBizNo (String invoiceBizNo) {
		InvoiceBizNo = invoiceBizNo;
	}	
	public String getVendorCd() {
		return VendorCd;
	}
	public void setVendorCd (String vendorCd) {
		VendorCd = vendorCd;
	}	
	public String getInvoiceCnt() {
		return InvoiceCnt;
	}
	public void setInvoiceCnt (String invoiceCnt) {
		InvoiceCnt = invoiceCnt;
	}	
	public String getInvoiceSupplyAmt() {
		return InvoiceSupplyAmt;
	}
	public void setInvoiceSupplyAmt (String invoiceSupplyAmt) {
		InvoiceSupplyAmt = invoiceSupplyAmt;
	}	
	public String getInvoiceVatAmt() {
		return InvoiceVatAmt;
	}
	public void setInvoiceVatAmt (String invoiceVatAmt) {
		InvoiceVatAmt = invoiceVatAmt;
	}	
	public String getInvoiceTotAmt() {
		return InvoiceTotAmt;
	}
	public void setInvoiceTotAmt (String invoiceTotAmt) {
		InvoiceTotAmt = invoiceTotAmt;
	}	
	public String getSlipCnt() {
		return SlipCnt;
	}
	public void setSlipCnt (String slipCnt) {
		SlipCnt = slipCnt;
	}	
	public String getSlipSupplyAmt() {
		return SlipSupplyAmt;
	}
	public void setSlipSupplyAmt (String slipSupplyAmt) {
		SlipSupplyAmt = slipSupplyAmt;
	}	
	public String getSlipVatAmt() {
		return SlipVatAmt;
	}
	public void setSlipVatAmt (String slipVatAmt) {
		SlipVatAmt = slipVatAmt;
	}	
	public String getSlipTotAmt() {
		return SlipTotAmt;
	}
	public void setSlipTotAmt (String slipTotAmt) {
		SlipTotAmt = slipTotAmt;
	}	
	public String getErrorYn() {
		return ErrorYn;
	}
	public void setErrorYn (String errorYn) {
		ErrorYn = errorYn;
	}	
	public String getSlipPaymentDate() {
		return SlipPaymentDate;
	}
	public void setSlipPaymentDate (String slipPaymentDate) {
		SlipPaymentDate = slipPaymentDate;
	}	
	public String getSlipBizNo() {
		return SlipBizNo;
	}
	public void setSlipBizNo (String slipBizNo) {
		SlipBizNo = slipBizNo;
	}	
	public String getSlipDrawDate() {
		return SlipDrawDate;
	}
	public void setSlipDrawDate (String slipDrawDate) {
		SlipDrawDate = slipDrawDate;
	}	
	public String getSlipIssueNo() {
		return SlipIssueNo;
	}
	public void setSlipIssueNo (String slipIssueNo) {
		SlipIssueNo = slipIssueNo;
	}	
	public String getInvoiceIssueNo() {
		return InvoiceIssueNo;
	}
	public void setInvoiceIssueNo (String invoiceIssueNo) {
		InvoiceIssueNo = invoiceIssueNo;
	}	
	public String getInvoiceMakeDate() {
		return InvoiceMakeDate;
	}
	public void setEInvoiceMakeDate (String invoiceMakeDate) {
		InvoiceMakeDate = invoiceMakeDate;
	}	
	public String getIssueNoYn() {
		return IssueNoYn;
	}
	public void setIssueNoYn (String issueNoYn) {
		IssueNoYn = issueNoYn;
	}	
	public String getBizNoYn() {
		return BizNoYn;
	}
	public void setBizNoYn (String bizNoYn) {
		BizNoYn = bizNoYn;
	}	
	public String getDrawDateYn() {
		return DrawDateYn;
	}
	public void setDrawDateYn (String drawDateYn) {
		DrawDateYn = drawDateYn;
	}	
	public String getSupplyAmtYn() {
		return SupplyAmtYn;
	}
	public void setSupplyAmtYn (String supplyAmtYn) {
		SupplyAmtYn = supplyAmtYn;
	}	
	public String getVatAmtYn() {
		return VatAmtYn;
	}
	public void setVatAmtYn (String vatAmtYn) {
		VatAmtYn = vatAmtYn;
	}	
	public String getTotAmtYn() {
		return TotAmtYn;
	}
	public void setTotAmtYn (String totAmtYn) {
		TotAmtYn = totAmtYn;
	}
	public String getSlipId() {
		return SlipId;
	}
	public void setSlipId (String slipId) {
		SlipId = slipId;
	}
	public String getGlDate() {
		return GlDate;
	}
	public void setGlDate (String glDate) {
		GlDate = glDate;
	}
	public String getAddr() {
		return Addr;
	}
	public void setAddr (String addr) {
		Addr = addr;
	}
	public String getTelNo() {
		return TelNo;
	}
	public void setTelNo (String telNo) {
		TelNo = telNo;
	}
	public String getUserMobileNo() {
		return UserMobileNo;
	}
	public void setUserMobileNo (String userMobileNo) {
		UserMobileNo = userMobileNo;
	}
	public String getDutyNm() {
		return DutyNm;
	}
	public void setDutyNm (String dutyNm) {
		DutyNm = dutyNm;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId (String userId) {
		UserId = userId;
	}
	public String getUserNm() {
		return UserNm;
	}
	public void setUserNm (String userNm) {
		UserNm = userNm;
	}
	
}