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
		listAdDailyReport(requestNo);
	});
	
	// 유튜브 광고 현황 수집 목록 조회
	function listAdDailyReport(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListAdDailyReport.do",
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
            { Header: "광고번호", Type: "Text", Width: 81, SaveName: "adNo", Align: "Center" },
            { Header: "광고명", Type: "Text", Width: 360, SaveName: "adNm" },
            { Header: "조회수", Type: "Text", Width: 110, SaveName: "viewCount", Align: "Center" },
            { Header: "좋아요수", Type: "Text", Width: 110, SaveName: "likeCount", Align: "Center" },
            { Header: "댓글수", Type: "Text", Width: 110, SaveName: "commentCount", Align: "Center" },
            { Header: "현황화면스크린샷첨부ID", Type: "Text", Width: 0, SaveName: "screenshotAttId", Hidden:true },
            { Header: "현황화면스크린샷", Type: "Text", Width: 160, SaveName: "screenshotAttFileNm", Align: "Center" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }

    // 그리드 클릭 함수 
	function mySheet_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
		
		if (mySheet.ColSaveName(Col) == "screenshotAttFileNm") {
			var screenshotAttId = mySheet.GetCellValue(Row, "screenshotAttId");
			
			if (screenshotAttId != ""){
				window.location.href = "/FileDownload/Download.do?attId=" + screenshotAttId + "&seq=1";
			}
   		}
	}
	
</script>