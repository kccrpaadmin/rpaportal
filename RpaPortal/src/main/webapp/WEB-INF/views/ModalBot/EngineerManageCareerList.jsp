<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<div></div>
		<!-- 그리드영역 -->
		<div id="sheet"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var userId = "${userId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		ListEngineerManageCareerList(userId);
	});
		
	// 업체 조회
	function ListEngineerManageCareerList(pUserId) {
		$.ajax({
			url: "/AjaxBot/ListEngineerManageCareerList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "userId": pUserId }),
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
        	{ Header: "업체명", Type: "Text", Width: 130, SaveName: "vendorNm", Align: "Center"},
        	{ Header: "참여기간", Type: "Text", Width: 150, SaveName: "playDateTerm", Align: "Center"},
            { Header: "인정일", Type: "Text", Width: 80, SaveName: "approveDays", Align: "Center" },
            { Header: "사업명", Type: "Text", Width: 300, SaveName: "constNm", Align: "Center" },            
            { Header: "발주자", Type: "Text", Width: 180, SaveName: "orderVendorNm", Align: "Center" },
            { Header: "공사종류", Type: "Text", Width: 150, SaveName: "constType", Align: "Center" },      
            { Header: "공법", Type: "Text", Width: 100, SaveName: "constMethod", Align: "Center" },
            { Header: "직무분야", Type: "Text", Width: 80, SaveName: "jobKind", Align: "Center" },
            { Header: "전문분야", Type: "Text", Width: 100, SaveName: "specialKind", Align: "Center" },
            { Header: "담당업무", Type: "Text", Width: 100, SaveName: "assignWork", Align: "Center" },
            { Header: "직위", Type: "Text", Width: 80, SaveName: "titleNm", Align: "Center" },
            { Header: "신고구분", Type: "Text", Width: 80, SaveName: "reportType", Align: "Center" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
        
    }
	
</script>