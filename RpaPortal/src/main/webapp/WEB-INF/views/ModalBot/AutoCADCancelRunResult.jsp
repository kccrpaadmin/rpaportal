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
		listAutoCADCancelResult(requestNo);
	});
	
	//  그룹사 인사발령 데이터 목록 조회
	function listAutoCADCancelResult(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListAutoCADCancelRunResult.do",
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
            { Header: "이메일", Type: "Text", Width: 150, SaveName: "email", Align: "Left" },
            { Header: "부서", Type: "Text", Width: 150, SaveName: "deptNm", Align: "Center"},  
            { Header: "사용자", Type: "Text", Width: 80, SaveName: "userNm", Align: "Center"},  
            { Header: "라이센스 명", Type: "Text", Width: 80, SaveName: "offering_name", Align: "Center"},  
            { Header: "할당일", Type: "Text", Width: 80, SaveName: "assigned_date", Align: "Center" },          
            { Header: "최종사용일", Type: "Text", Width: 80, SaveName: "last_accessed", Align: "Center"},
            { Header: "작업일자", Type: "Text", Width: 120, SaveName: "requestDate", Align: "Center" },   
            { Header: "작업구분", Type: "Text", Width: 80, SaveName: "requestType", Align: "Center" },        
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
</script>