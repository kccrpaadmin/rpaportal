<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
		<!-- 그리드영역 -->	
		<div class="grid_btn" style="text-align: right;margin-bottom: 10px;">
	    	<a class="btn_common2" id="btn_download">엑셀</a>
	    </div>	
		<div id="sheet1"></div>
</div>

<script type="text/javascript">

	var menuId = "${menuId}";

	// 페이지 로드 
	$(document).ready(function (e) {		
		listVisitUser();
	});
	
	//  사용자 현황 조회
	function listVisitUser() {
		$.ajax({			
			url: "/AjaxAdmin/ListVisitUser.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "accessUrl": "${accessUrl}", "beginDate": "${beginDate}", "endDate": "${endDate}"}),
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
	
	// 사고사례 사용자 현황 조회 그리드 생성 함수
    function makeGrid(pListDatas) {
    	commonFunc.initSheet("mySheet");

        var initdata = {};

        createIBSheet2(document.getElementById("sheet1"), "mySheet", "650px", "595px");

        initdata.Cfg = { SearchMode: smClientPaging, Page: 20, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	 { Header: "성명", Type: "Text", Width: 100, SaveName: "userNm",  Align: "Center" },
             { Header: "부서명", Type: "Text", Width: 160, SaveName: "deptNm", Align: "Center" },
             { Header: "직급", Type: "Text", Width: 100, SaveName: "titleNm", Align: "Center" },
             { Header: "접속기기", Type: "Text", Width: 90, SaveName: "accessDevice" , Align: "Center" },
             { Header: "접속일자", Type: "Text", Width: 140, SaveName: "useDate" , Align: "Center"}                                  
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.SetCountPosition(3); // 건수 정보 표시
		mySheet.SetPagingPosition(2); // 페이지 네비게이션 버튼 표시
        mySheet.LoadSearchData(pListDatas);
    }
	
    $(document).on("click", "#btn_download", function (e) {		
		var params = { Multipart: 0, FileName: "방문자 목록.xlsx",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 ,HiddenColumn:1}
		mySheet.Down2Excel(params);
	});
		
</script>