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
		listContractElecStampTaxResult(requestNo);
	});
	
	//  그룹사 인사발령 데이터 목록 조회
	function listContractElecStampTaxResult(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListContractElecStampTaxResult.do",
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

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            //{ Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", hidden:true},
            { Header: "계약번호", Type: "Text", Width: 100, SaveName: "contractNo", Align: "Center" },
            { Header: "계약차수", Type: "Text", Width: 50, SaveName: "chgSeq", Align: "Center" },
            { Header: "계약명", Type: "Text", Width: 400, SaveName: "contractNm", Align: "Center" },
            { Header: "요청업체", Type: "Text", Width: 110, SaveName: "vendorNm", Align: "Center" },
            { Header: "KCC인지세 납부금액", Type: "Text", Width: 100, SaveName: "kCCStampTaxAmt", Align: "Center" },
            { Header: "상태", Type: "Text", Width: 80, SaveName: "cdNm", Align: "Center" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
</script>