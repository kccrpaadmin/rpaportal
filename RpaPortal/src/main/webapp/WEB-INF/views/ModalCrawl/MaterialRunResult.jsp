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
		listMaterial(requestNo);
	});
	
	// 원자재정보 목록 조회
	function listMaterial(pRequestNo) {
		$.ajax({
			url: "/AjaxCrawl/ListMaterial.do",
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
            { Header: "요청번호", Type: "Text", Width: 180, SaveName: "requestNo", Align: "Center" },
            { Header: "기준일자", Type: "Text", Width: 200, SaveName: "standardDate", Align: "Center" },
            { Header: "자재분류코드", Type: "Text", Width: 226, SaveName: "materialTypeCd", Align: "Center" },
            { Header: "자재분류명", Type: "Text", Width: 226, SaveName: "materialTypeNm", Align: "Center" },
            { Header: "금액", Type: "Float", Width: 226, SaveName: "amt"}
        ];
		
        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
</script>