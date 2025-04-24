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
		listEngineerConstructionResult(requestNo);
	});
	
	// 설계시공 조회
	function listEngineerConstructionResult(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListBotEngineerConstructionResult.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeGrid1(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	    
    // 설계시공 그리드 생성 함수
    function makeGrid1(pListDatas) {
    	commonFunc.initSheet("mySheet1");

        var initdata = {};
        
        createIBSheet2(document.getElementById("sheet"), "mySheet1", "1060px", "417px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 00, SaveName: "requestNo", Hidden:true },            
            { Header: "성명", Type: "Text", Width: 80, SaveName: "userNm", Align: "Center" },
            { Header: "생년월일", Type: "Text", Width: 80, SaveName: "resNo", Align: "Center"},  
            { Header: "직무분야", Type: "Text", Width: 100, SaveName: "jobField", Align: "Center" },          
            { Header: "등급", Type: "Text", Width: 100, SaveName: "ranking", Align: "Center"},
            { Header: "점수", Type: "Text", Width: 100, SaveName: "rankingRate", Align: "Center" },
            { Header: "비고", Type: "Text", Width: 580, SaveName: "etc", Align: "Center" },
        ];

        IBS_InitSheet(mySheet1, initdata);
        mySheet1.SetEditable(0);
        mySheet1.SetEditableColorDiff(0);
        mySheet1.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet1.LoadSearchData(pListDatas);
    }
    	
</script>