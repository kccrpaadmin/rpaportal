package com.kcc.biz.model;

// 철처한 원봉 낙타 표기법을 사용해야 함.
// 뷰에서 첫 글자는 대문자로 못쓰기 때문에 카멜표기법을 사용.
// 메소드명이 getSName, getEPUserId 처럼 되면 문제 발생
// 메소드명을 getSname, getEpUserId 처럼 변경
// el(jstl)과 컨트롤러간 VO 사용시 setter에서 생성되는 소문자를 따라가는거 같아서 대문자로 테스트 했으나,
// el(jstl)에서는 무조건 첫 글자를 소문자로 써야하는 것으로 결론 지었다.
// @RequestBody VO vo 매핑시에도 첫 글자 소문자와 매핑 된다. (input name="userId") 

public class BoardVO {
	private String SearchTxt;
	private String BoardTypeCd;
	private String BoardTypeNm;
	private String Seq;
	private String BoardNm;
	private String BoardContent;
	private String AttId;
	private String RegUserId;
	private String RegUserNm;
	private String RegDate;
	private String Mode;
	private String SaveMode;
	private String Ord;
	
	public String getSearchTxt() {
		return SearchTxt;
	}
	public String getBoardTypeCd() {
		return BoardTypeCd;
	}
	public String getBoardTypeNm() {
		return BoardTypeNm;
	}
	public String getSeq() {
		return Seq;
	}
	public String getBoardNm() {
		return BoardNm;
	}
	public String getBoardContent() {
		return BoardContent;
	}
	public String getAttId() {
		return AttId;
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
	public void setSearchTxt(String searchTxt) {
		SearchTxt = searchTxt;
	}
	public void setBoardTypeCd(String boardTypeCd) {
		BoardTypeCd = boardTypeCd;
	}
	public void setBoardTypeNm(String boardTypeNm) {
		BoardTypeNm = boardTypeNm;
	}
	public void setSeq(String seq) {
		Seq = seq;
	}
	public void setBoardNm(String boardNm) {
		BoardNm = boardNm;
	}
	public void setBoardContent(String boardContent) {
		BoardContent = boardContent;
	}
	public void setAttId(String attId) {
		AttId = attId;
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
	public String getMode() {
		return Mode;
	}
	public void setMode(String mode) {
		Mode = mode;
	}	
	public String getSaveMode() {
		return SaveMode;
	}
	public void setSaveMode(String saveMode) {
		SaveMode = saveMode;
	}
	public String getOrd() {
		return Ord;
	}
	public void setOrd(String ord) {
		Ord = ord;
	}
}