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
		listG2b(requestNo);
	});
	
	//  나라장터 수집 데이터 목록 조회
	function listG2b(pRequestNo) {
		$.ajax({
			url: "/AjaxCrawl/ListG2b.do",
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
            { Header: "관리번호", Type: "Text", Width: 80, SaveName: "manageNo", Align: "Center" },
            { Header: "접수일자", Type: "Text", Width: 80, SaveName: "receiveDate", Align: "Center" },
            { Header: "공사명", Type: "Text", Width: 380, SaveName: "constNm" },
            { Header: "발주기관", Type: "Text", Width: 240, SaveName: "orderAgency" },
            { Header: "공종명", Type: "Text", Width: 180, SaveName: "regJobType", Align: "Center" },
            { Header: "총예산금액", Type: "Float", Width: 100, SaveName: "totalContractAmt" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
</script>