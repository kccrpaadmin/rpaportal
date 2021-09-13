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
				<div class="location_title">공지사항</div>	
			</div>
			<div class="location_right">
				<span class="location_home">홈</span>
				<span class="location_arrow">RPA 소식</span>
			</div>
		</div>
		<!-- 검색영역 -->
	    <div class="search_dtl_box">
	        <table class="search_dtl_tbl">
	            <caption>검색영역</caption>
	            <colgroup>
	                <col style="width:8%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                    <th class="search_dtl_th">검색어</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="txt_box_l" style="width:200px;" id="search_txt" />
	                    </td>
	                </tr>
	            </tbody>
	        </table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_search">조회</a>
	    	<a class="btn_common1" id="btn_edit" style="display:none">작성</a>
	    </div>
	    <!-- 그리드영역 -->
   	    <div id="sheet"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 페이지 로드 
	$(document).ready(function (e) {
		enableButtonControl();
		searchListBoard();
	});
	
	// 공지사항 목록 조회 함수
	function listBasicBoard(pSearchTxt) {
		$.ajax({
			url: "/AjaxBoard/ListBasicBoard.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "searchTxt": pSearchTxt }),
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1120px", "324px");

        initdata.Cfg = { SearchMode: smClientPaging, Page: 10, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "순번", Type: "Text", Width: 50, SaveName: "ord", Align: "Center" },
        	{ Header: "공지번호", Type: "Text", Width: 0, SaveName: "seq", Hidden:"true"},
        	{ Header: "구분", Type: "Text", Width: 80, SaveName: "boardTypeNm", Align: "Center" },
	        { Header: "제목", Type: "Text", Width: 930, SaveName: "boardNm" },
	        { Header: "작성일자", Type: "Text", Width: 120, SaveName: "regDate", Align: "Center" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetDataLinkMouse("boardNm", true);
        mySheet.SetColFontUnderline("boardNm", true);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.SetCountPosition(3); // 건수 정보 표시
		mySheet.SetPagingPosition(2); // 페이지 네비게이션 버튼 표시
        mySheet.LoadSearchData(pListDatas);
    }  
	
 // 그리드 클릭 함수
	function mySheet_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
		
		if (mySheet.ColSaveName(Col) == "boardNm") {
			var seq = mySheet.GetCellValue(Row, "seq");
			var mode = "BoardDetail"
			
			window.location.href = "/Board/BoardDetail.do?pSeq=" + seq + "&pMode=" + mode;
   		}
	}
 
	// 목록 조회 공통 함수
	function searchListBoard() {
		var searchTxt = $("#search_txt").val()
		
		listBasicBoard(searchTxt);
	}	
	
	// 버튼 활성화, 비활성화 함수
	function enableButtonControl() {		
		if (commonFunc.certInfo.roleType == "ROLE_ADMIN") {
			$("#btn_edit").css("display", "inline-block");   
        }		
	}
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {								
		searchListBoard();
	});
	
	// 작성 버튼 클릭 이벤트
	$(document).on("click", "#btn_edit", function (e) {					
		var mode = "BoardWrite"
		var saveMode = "C"
		
		window.location.href = "/Board/BoardWrite.do?pSeq=0&pMode=" + mode + "&pSaveMode=" + saveMode;
    });
	
	// 업체명 엔터 이벤트
	$(document).on("keydown", "#search_txt", function (e) {
		if (e.keyCode == 13) {
			$("#btn_search").trigger("click");
		}
	});
	
</script>