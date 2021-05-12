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
				<div class="location_title">RPA 소식</div>	
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
	    	<a class="btn_common" id="btn_edit">작성</a>
	    	<a class="btn_common" id="btn_search">조회</a>
	    </div>
	    <!-- 그리드영역 -->
   	    <div id="sheet"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listBasicBoard($("#search_txt").val());
	});
	
	// 웹크롤링 메뉴 목록 생성 함수
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
            { Header: "요청번호", Type: "Text", Width: 150, SaveName: "boardTypeCd", Align: "Center" },
            { Header: "메뉴ID", Type: "Text", Width: 100, SaveName: "boardTypeNm", Align: "Center" },
            { Header: "요청명", Type: "Text", Width: 450, SaveName: "seq", Align: "Center" },
            { Header: "진행상태", Type: "Text", Width: 110, SaveName: "boardNm", Align: "Center" },
            { Header: "요청자", Type: "Text", Width: 110, SaveName: "regUserNm", Align: "Center" },
            { Header: "시작시간", Type: "Text", Width: 150, SaveName: "regDate", Align: "Center" }
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
	
</script>