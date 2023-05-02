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
		listSCSystemCheck(requestNo);
	});
	
	// 시스템체크 수집 파일경로 목록 조회
	function listSCSystemCheck(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListSCSystemCheck.do",
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1051px", "517px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 130, SaveName: "requestNo", Hidden:true },
            { Header: "시스템", Type: "Text", Width: 130, SaveName: "systemNm" },
            { Header: "동작상태코드", Type: "Text", Width: 130, SaveName: "actionCd", Align: "Center" },
            { Header: "동작상태", Type: "Text", Width: 130, SaveName: "actionNm", Align: "Center" },
            { Header: "오류메세지", Type: "Text", Width: 531, SaveName: "errorMsg" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
	// 실패된 행에 폰트 색상 변경 함수
	function mySheet_OnRowSearchEnd(row) {
    	if (mySheet.GetCellValue(row, "actionCd") == "RA005002"){
			mySheet.SetRowFontColor(row , "#FF0000");
		}
	}
	
</script>