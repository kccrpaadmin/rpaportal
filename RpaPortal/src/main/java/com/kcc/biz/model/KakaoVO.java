package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId")

public class KakaoVO {
	private String Kakao_Plusid;
	private String Kakao_Task_Number;
	private String Kakao_Tempid;
	private String Kakao_Tempnm;
	private String Kakao_Tempcn;
	private String Status;
	private String Kakao_Seq;
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getKakao_Seq() {
		return Kakao_Seq;
	}
	public void setKakao_Seq(String kakao_Seq) {
		Kakao_Seq = kakao_Seq;
	}
	public String getKakao_Plusid() {
		return Kakao_Plusid;
	}
	public void setKakao_Plusid(String kakao_Plusid) {
		Kakao_Plusid = kakao_Plusid;
	}
	public String getKakao_Task_Number() {
		return Kakao_Task_Number;
	}
	public void setKakao_Task_Number(String kakao_Task_Number) {
		Kakao_Task_Number = kakao_Task_Number;
	}
	public String getKakao_Tempid() {
		return Kakao_Tempid;
	}
	public void setKakao_Tempid(String kakao_Tempid) {
		Kakao_Tempid = kakao_Tempid;
	}
	public String getKakao_Tempnm() {
		return Kakao_Tempnm;
	}
	public void setKakao_Tempnm(String kakao_Tempnm) {
		Kakao_Tempnm = kakao_Tempnm;
	}
	public String getKakao_Tempcn() {
		return Kakao_Tempcn;
	}
	public void setKakao_Tempcn(String kakao_Tempcn) {
		Kakao_Tempcn = kakao_Tempcn;
	}
}