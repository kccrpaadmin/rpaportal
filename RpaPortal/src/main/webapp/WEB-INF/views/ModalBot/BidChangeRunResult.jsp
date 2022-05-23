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
		listBidChange(requestNo);
	});
	
	//  하도급 변경계약 키스콘 등록 대상 계약 수집 데이터 목록 조회
	function listBidChange(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListBidChange.do",
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

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 0, SaveName: "requestNo", Hidden:true },            
            { Header: "입찰공고번호", Type: "Text", Width: 100, SaveName: "bidNo", Align: "Center" },
            { Header: "공고명", Type: "Text", Width: 300, SaveName: "constNm" },
            { Header: "변경구분", Type: "Text", Width: 60, SaveName: "changeType", Align: "Center" },            
            { Header: "심사업체명단 파일명", Type: "Text", Width: 245, SaveName: "pqVendorAttNm" },
            { Header: "물량내역서 파일명", Type: "Text", Width: 245, SaveName: "bidDetailAttNm" },
            { Header: "기초금액", Type: "Float", Width: 100, SaveName: "basePrice", Align: "Right"}
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
</script>