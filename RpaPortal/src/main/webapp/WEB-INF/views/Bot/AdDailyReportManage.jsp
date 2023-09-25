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
				<span class="location_arrow">BOT</span>
				<span class="location_arrow">업무관리</span>
			</div>
		</div>
		<!-- 제목 -->
		<div class="title">상세정보</div>
		<!-- 검색영역 -->
	    
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_search">조회</a>
	    </div>	    
	    <br>
	    <!-- 그리드영역 -->
   	    <div id="sheet"></div>
   	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a href="/Bot/ListMenu.do"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${outMenuVO.menuId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {		
		listAdDailyReportManage()
	});
	
	// 송금확인증 목록 조회
	function listAdDailyReportManage() {
		$.ajax({
			url: "/AjaxBot/ListAdDailyReportManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({}),
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
        	{ Header: "광고번호", Type: "Text", Width: 80, SaveName: "adNo", Align: "Center" },
        	{ Header: "광고명", Type: "Text", Width: 460, SaveName: "adNm" },
            { Header: "수행일자", Type: "Date", Width: 100, SaveName: "runDate", Align: "Center" },
            { Header: "수행시간", Type: "Text", Width: 100, SaveName: "runTime", Align: "Center" },            
            { Header: "조회 수", Type: "Float", Width: 120, SaveName: "viewCount", Align: "Right" },
            { Header: "좋아요 수", Type: "Float", Width: 120, SaveName: "likeCount", Align: "Right" },
            { Header: "댓글 수", Type: "Float", Width: 120, SaveName: "commentCount", Align: "Right" }    
        ];
		
        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {		
		listAdDailyReportManage();
	});

</script>