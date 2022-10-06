<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<!-- 로케이션 -->
		<div class="location_box">
			<div class="location_left">
				<div class="location_title">${outMenuVO.menuNm}</div>	
			</div>
			<div class="location_right">
				<span class="location_home">홈</span>
				<span class="location_arrow">웹크롤링</span>
				<span class="location_arrow">업무관리</span>
			</div>
		</div>
		<!-- 제목 -->
		<div class="title">상세정보</div>
		<!-- 검색영역 -->
	    <div class="search_dtl_box">
	        <table class="search_dtl_tbl">
	            <caption>검색영역</caption>
	            <colgroup>	                
	                <col style="width:12%;" />
	                <col style="width:30%;" />
	                <col style="width:12%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>	                	
	                	<th class="search_dtl_th">사고발생일자</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="datepicker_ym" readonly="readonly"  id="start_date" value="${startDate}" />
	                        <span>~</span>
	                        <input type="text" class="datepicker_ym" readonly="readonly" id="end_date" value="${endDate}" />
	                    </td>
	                    <th class="search_dtl_th">재해명</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="txt_box_l" style="width:200px;" id="accident_nm" />
	                    </td>
	                </tr>
	            </tbody>
	        </table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">	    	
	    	<a class="btn_common1" id="btn_search">조회</a>
	    	<a class="btn_common1" id="btn_usersearch">사용현황 조회</a>
	    </div>
	    <!-- 그리드영역 -->
	    <div class="grid_box">
		    <div class="grid_title">사고사례정보</div>
		    <div class="grid_btn">
		    	<a class="btn_common2" id="btn_acd_download">엑셀</a>
		    </div>
	    </div>
   	    <div id="sheet"></div>
   	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a href="/Crawl/ListMenu.do"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${outMenuVO.menuId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		commonFunc.createDatepicker(".datepicker_ym", "YearMonth");
		searchListCrawlAcdManage();
	});
	
	// 나라장터 수집 데이터 관리 목록 조회
	function listAcdManage(pStartDate, pEndDate, pAccidentType) {		
		$.ajax({
			url: "/AjaxCrawl/ListAcdManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "startDate": pStartDate, "endDate": pEndDate, "accidentType": pAccidentType }),
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1120px", "510px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	 { Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },
             { Header: "번호", Type: "Int", Width: 70, SaveName: "seq", Align: "Center" },
             { Header: "사고발생일자", Type: "Text", Width: 90, SaveName: "accidentDate", Align: "Left" },
             { Header: "사고개요", Type: "Text", Width: 370, SaveName: "subject" , Align: "Left" },
             { Header: "재해형태", Type: "Text", Width: 140, SaveName: "accidentType" , Align: "Left"},
             { Header: "출처", Type: "Text", Width: 180, SaveName: "source", Align: "Center" },
             { Header: "바로가기", Type: "Text", Width: 270, SaveName: "link" , Align: "Left"}             
        ];
		
        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetColFontUnderline("link", true);
        mySheet.SetDataLinkMouse("link", true);
        mySheet.SetEditable(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
 // 그리드 클릭 함수
	function mySheet_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}		
		
		if (mySheet.ColSaveName(Col) == "link") {			
			var link = mySheet.GetCellValue(Row, "link");
						
			window.open(link, "_blank");
   		}		
	}
	
	// 목록 조회 공통 함수
	function searchListCrawlAcdManage() {		
		listAcdManage(commonFunc.getReplaceAll($("#start_date").val(), "-", ""), commonFunc.getReplaceAll($("#end_date").val(), "-", ""), $("#accident_nm").val());
	}	
	
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {
		searchListCrawlAcdManage();
	});
 	
	// 검색 박스 엔터 이벤트
	$(document).on("keydown", "#accident_nm", function (e) {
		if (e.keyCode == 13) {
			$("#btn_search").trigger("click");
		}
	});	
	
 	// 엑셀 버튼 클릭 이벤트
	$(document).on("click", "#btn_acd_download", function (e) {		
		var params = { Multipart: 0, FileName: "Acd.xls",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		mySheet.Down2Excel(params);
	});
 	
	// 사용자현황 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_usersearch", function (e) {				
		libraryFunc.createModal(null, null, null, 620, 700, "사용현황", "/ModalCrawl/AcdManageUseList.do?pMenuId=" + menuId);
	}); 
 	
</script>