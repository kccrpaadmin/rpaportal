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
		listPersonAppointList(requestNo);
	});
	
	//  그룹사 인사발령 데이터 목록 조회
	function listPersonAppointList(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListPersonAppointList.do",
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
            { Header: "그룹사", Type: "Text", Width: 100, SaveName: "corpNm", Align: "Center" },
            { Header: "발령일자", Type: "Text", Width: 150, SaveName: "makeDate", Align: "Center"},  
            { Header: "제목", Type: "Text", Width: 300, SaveName: "title", Align: "Center" },          
            { Header: "발령정보", Type: "Text", Width: 110, SaveName: "subOfficerNo", Align: "Center"},
            { Header: "소제목", Type: "Text", Width: 200, SaveName: "subTitle", Align: "Center" },
            { Header: "첨부ID", Type: "Text", Width: 10, SaveName: "attId", Align: "Center", Hidden:true },
            { Header: "이관여부", Type: "Text", Width: 80, SaveName: "transferYn", Align: "Center"}           
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
</script>