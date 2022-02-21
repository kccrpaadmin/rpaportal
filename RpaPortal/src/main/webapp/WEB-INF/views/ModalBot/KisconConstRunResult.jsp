<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<!-- 그리드영역 -->
		<div id="sheet"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${menuId}";
	var requestNo = "${requestNo}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listKisconConstSubcontract(requestNo);
	});
	
	//  하도급 변경계약 키스콘 등록 대상 계약 수집 데이터 목록 조회
	function listKisconConstSubcontract(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListKisconConstSubcontract.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeGrid(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 그리드 생성 함수
    function makeGrid(pListDatas) {
    	commonFunc.initSheet("mySheet");

        var initdata = {};

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1060px", "510px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },            
            { Header: "계약정보|계약번호", Type: "Text", Width: 110, SaveName: "contractNo", Align: "Center" },
            { Header: "계약정보|현장명", Type: "Text", Width: 180, SaveName: "siteNm" },
            { Header: "계약정보|공사명", Type: "Text", Width: 140, SaveName: "contractNm" },            
            { Header: "계약정보|업체명", Type: "Text", Width: 120, SaveName: "vendorNm" },
            { Header: "계약정보|계약일자", Type: "Text", Width: 80, SaveName: "contractDate", Align: "Center" },
            { Header: "계약정보|계약금액", Type: "Float", Width: 100, SaveName: "contractAmt", Align: "Right"},
            { Header: "계약정보|계약시작일자", Type: "Text", Width: 80, SaveName: "contractStartDate", Align: "Center" },
            { Header: "계약정보|계약종료일자", Type: "Text", Width: 80, SaveName: "contractFinishDate", Align: "Center" },
            { Header: "하도급대금지급보증|보증기관", Type: "Text", Width: 100, SaveName: "guaranteeOrgNm", Align: "Center" },
            { Header: "하도급대금지급보증|보증서번호", Type: "Text", Width: 120, SaveName: "guaranteePolicyNo", Align: "Center" },
            { Header: "등록상태|등록상태", Type: "Text", Width: 70, SaveName: "successType", Align: "Center" },
            { Header: "오류메세지|오류메세지", Type: "Text", Width: 200, SaveName: "errorMsg" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
</script>