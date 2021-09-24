package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BotEngineerVO {
	private String MenuId;
	private String RequestNo;
	private String UserNm;
	private String ResNo;
	private String BasicEdu;
	private String PlanEdu;
	private String BusinessEdu;
	private String QualityEdu;
	private String Etc;
	private String UserId;
	private String Seq;
	private String VendorNm;
	private String PlayDateTerm;
	private String ApproveDays;
	private String ConstNm;
	private String OrderVendorNm;
	private String ConstType;
	private String ConstMethod;
	private String JobKind;
	private String SpecialKind;
	private String AssignWork;
	private String DutyNm;
	private String ReportType;
	private String EngineerExistTypeCd;
	private String Phase;
	private String InitDate;
	private String RunDays;
	private String ThreeYearDate;
	private String BeforeThreeYearDate;
	private String AfterThreeYearDate;
	private String EduType;
	private String EduTimes;
	private String DelayDate;
	private String PassDate;
	private String PassTimes;
	private String ReceiptDate;
	private String Ranking;
	private String RankingRate;
	private String AfterRanking;
	private String OrgTypeCd;
	private String OrgTypeNm;
	private String DeptNm;
	private String ChkYn;
	private String EmpNo;
	private String EngineerTargetUser;
	private String EngineerExistType;
		
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
	public String getUserNm() {
		return UserNm;
	}
	public void setUserNm(String userNm) {
		UserNm = userNm;
	}
	public String getResNo() {
		return ResNo;
	}
	public void setResNo(String resNo) {
		ResNo = resNo;
	}
	public String getBasicEdu() {
		return BasicEdu;
	}
	public void setBasicEdu(String basicEdu) {
		BasicEdu = basicEdu;
	}
	public String getPlanEdu() {
		return PlanEdu;
	}
	public void setPlanEdu(String planEdu) {
		PlanEdu = planEdu;
	}
	public String getBusinessEdu() {
		return BusinessEdu;
	}
	public void setBusinessEdu(String businessEdu) {
		BusinessEdu = businessEdu;
	}
	public String getQualityEdu() {
		return QualityEdu;
	}
	public void setQualityEdu(String qualityEdu) {
		QualityEdu = qualityEdu;
	}
	public String getEtc() {
		return Etc;
	}
	public void setEtc(String etc) {
		Etc = etc;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getSeq() {
		return Seq;
	}
	public void setSeq(String seq) {
		Seq = seq;
	}
	public String getVendorNm() {
		return VendorNm;
	}
	public void setVendorNm(String vendorNm) {
		VendorNm = vendorNm;
	}
	public String getPlayDateTerm() {
		return PlayDateTerm;
	}
	public void setPlayDateTerm(String playDateTerm) {
		PlayDateTerm = playDateTerm;
	}
	public String getApproveDays() {
		return ApproveDays;
	}
	public void setApproveDays(String approveDays) {
		ApproveDays = approveDays;
	}
	public String getConstNm() {
		return ConstNm;
	}
	public void setConstNm(String constNm) {
		ConstNm = constNm;
	}
	public String getOrderVendorNm() {
		return OrderVendorNm;
	}
	public void setOrderVendorNm(String orderVendorNm) {
		OrderVendorNm = orderVendorNm;
	}
	public String getConstType() {
		return ConstType;
	}
	public void setConstType(String constType) {
		ConstType = constType;
	}
	public String getConstMethod() {
		return ConstMethod;
	}
	public void setConstMethod(String constMethod) {
		ConstMethod = constMethod;
	}
	public String getJobKind() {
		return JobKind;
	}
	public void setJobKind(String jobKind) {
		JobKind = jobKind;
	}
	public String getSpecialKind() {
		return SpecialKind;
	}
	public void setSpecialKind(String specialKind) {
		SpecialKind = specialKind;
	}
	public String getAssignWork() {
		return AssignWork;
	}
	public void setAssignWork(String assignWork) {
		AssignWork = assignWork;
	}
	public String getDutyNm() {
		return DutyNm;
	}
	public void setDutyNm(String dutyNm) {
		DutyNm = dutyNm;
	}
	public String getReportType() {
		return ReportType;
	}
	public void setReportType(String reportType) {
		ReportType = reportType;
	}
	public String getEngineerExistTypeCd() {
		return EngineerExistTypeCd;
	}
	public void setEngineerExistTypeCd(String engineerExistTypeCd) {
		EngineerExistTypeCd = engineerExistTypeCd;
	}
	public String getPhase() {
		return Phase;
	}
	public void setPhase(String phase) {
		Phase = phase;
	}
	public String getInitDate() {
		return InitDate;
	}
	public void setInitDate(String initDate) {
		InitDate = initDate;
	}
	public String getRunDays() {
		return RunDays;
	}
	public void setRunDays(String rundays) {
		RunDays = rundays;
	}
	public String getThreeYearDate() {
		return ThreeYearDate;
	}
	public void ThreeYearDate(String threeYearDate) {
		ThreeYearDate = threeYearDate;
	}
	public String getBeforeThreeYearDate() {
		return BeforeThreeYearDate;
	}
	public void setBeforeThreeYearDate(String beforeThreeYearDate) {
		BeforeThreeYearDate = beforeThreeYearDate;
	}
	public String getAfterThreeYearDate() {
		return AfterThreeYearDate;
	}
	public void setAfterThreeYearDate(String afterThreeYearDate) {
		AfterThreeYearDate = afterThreeYearDate;
	}
	public String getEduType() {
		return EduType;
	}
	public void setEduType(String eduType) {
		EduType = eduType;
	}
	public String getEduTimes() {
		return EduTimes;
	}
	public void setEduTimes(String eduTimes) {
		EduTimes = eduTimes;
	}
	public String getDelayDate() {
		return DelayDate;
	}
	public void setDelayDate(String delayDate) {
		DelayDate = delayDate;
	}
	public String getPassDate() {
		return PassDate;
	}
	public void setPassDate(String passDate) {
		PassDate = passDate;
	}
	public String getPassTimes() {
		return PassTimes;
	}
	public void setPassTimes(String passTimes) {
		PassTimes = passTimes;
	}
	public String getReceiptDate() {
		return ReceiptDate;
	}
	public void setReceiptDate(String receiptDate) {
		ReceiptDate = receiptDate;
	}
	public String getRanking() {
		return Ranking;
	}
	public void setRanking(String ranking) {
		Ranking = ranking;
	}
	public String getRankingRate() {
		return RankingRate;
	}
	public void setRankingRate(String rankingRate) {
		RankingRate = rankingRate;
	}
	public String getAfterRanking() {
		return AfterRanking;
	}
	public void setAfterRanking(String afterRanking) {
		AfterRanking = afterRanking;
	}
	public String getOrgTypeCd() {
		return OrgTypeCd;
	}
	public void setOrgTypeCd(String orgTypeCd) {
		OrgTypeCd = orgTypeCd;
	}
	public String getOrgTypeNm() {
		return OrgTypeNm;
	}
	public void setOrgTypeNm(String orgTypeNm) {
		OrgTypeNm = orgTypeNm;
	}
	public String getDeptNm() {
		return DeptNm;
	}
	public void setDeptNm(String deptNm) {
		DeptNm = deptNm;
	}
	public String getChkYn() {
		return ChkYn;
	}
	public void setChkYn(String chkYn) {
		ChkYn = chkYn;
	}
	public String getEmpNo() {
		return EmpNo;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
	public String getEngineerTargetUser() {
		return EngineerTargetUser;
	}
	public void setEngineerTargetUser(String engineerTargetUser) {
		EngineerTargetUser = engineerTargetUser;
	}
	public String getEngineerExistType() {
		return EngineerExistType;
	}
	public void setEngineerExistType(String engineerExistType) {
		EngineerExistType = engineerExistType;
	}
}