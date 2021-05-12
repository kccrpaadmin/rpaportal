package com.kcc.code;

public class CodeConfig {
	/*
	상수 처리 가능하나 객체지향 방법 사용
	getter, setter 사용
	public final static String AuthAll = "permitAll";
	*/
	
	// 속성 값 정의 (tx-context.xml)
	private String adviceRef = "txAdviceErpDb";
	private String pointCutRef = "txPointCut";
	
	public String getAdviceRef() {
		return adviceRef;
	}
	public String getPointCutRef() {
		return pointCutRef;
	}
	public void setAdviceRef(String adviceRef) {
		this.adviceRef = adviceRef;
	}
	public void setPointCutRef(String pointCutRef) {
		this.pointCutRef = pointCutRef;
	}	
}