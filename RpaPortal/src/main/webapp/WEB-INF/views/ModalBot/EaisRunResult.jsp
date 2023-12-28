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
		listEais(requestNo);
	});
	
	// 세움터 사용승인(허가) 수집 목록 조회
	function listEais(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListEais.do",
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "951px", "417px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 0, SaveName: "requestNo", Hidden:true },
            { Header: "구분", Type: "Text", Width: 50, SaveName: "menuTypeNm", Align: "Center" },
            { Header: "대지위치", Type: "Text", Width: 240, SaveName: "siteLocation" },
            { Header: "대표자", Type: "Text", Width: 100, SaveName: "repNm" },
            { Header: "민원명", Type: "Text", Width: 170, SaveName: "complNm" },
            { Header: "작성일", Type: "Text", Width: 75, SaveName: "complRegDate", Align: "Center" },
            { Header: "접수일", Type: "Text", Width: 75, SaveName: "complReqDate", Align: "Center" },
            { Header: "처리예정일", Type: "Text", Width: 75, SaveName: "complProcExpDate", Align: "Center" },
            { Header: "처리일", Type: "Text", Width: 75, SaveName: "complProcExpDate", Align: "Center" },
            { Header: "SMS발송여부", Type: "Text", Width: 91, SaveName: "smsSendYn", Align: "Center" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
</script>