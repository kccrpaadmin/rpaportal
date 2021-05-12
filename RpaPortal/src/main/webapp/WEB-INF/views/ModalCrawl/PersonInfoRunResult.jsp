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
	var menuId = "${crawlRequestVO.menuId}";
	var requestNo = "${crawlRequestVO.requestNo}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listPersonInfo(requestNo);
	});
	
	// 발주처 직원 정보 목록 조회
	function listPersonInfo(pRequestNo) {
		$.ajax({
			url: "/AjaxCrawl/ListPersonInfo.do",
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
            { Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },
            { Header: "발주처", Type: "Text", Width: 200, SaveName: "orgNm", Align: "Center" },
            { Header: "부서명", Type: "Text", Width: 200, SaveName: "deptNm" },
            { Header: "이름", Type: "Text", Width: 100, SaveName: "userNm", Align: "Center" },
            { Header: "직위", Type: "Text", Width: 100, SaveName: "dutyNm", Align: "Center" },
            { Header: "전화번호", Type: "Text", Width: 100, SaveName: "tel", Align: "Center" },
            { Header: "담당업무", Type: "Text", Width: 360, SaveName: "task" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
</script>