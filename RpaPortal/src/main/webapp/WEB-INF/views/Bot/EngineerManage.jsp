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
	    <div class="search_dtl_box">
	        <table class="search_dtl_tbl">
	            <caption>검색영역</caption>
	            <colgroup>
	                <col style="width:8%;" />
	                <col style="width:20%;" />
	                <col style="width:8%;" />
	                <col style="width:20%;" />
	                <col style="width:8%;" />
	                <col />
	            </colgroup>
	            <tbody>	                
	                <tr>
	                	<th class="search_dtl_th">부문</th>
	                    <td class="search_dtl_td">
	                         ${orgTypeComboBox}
	                    </td>
	                	<th class="search_dtl_th">직급</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="txt_box_l" style="width:160px;" id="duty_nm" />
	                    </td>
	                	<th class="search_dtl_th">성명</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="txt_box_l" style="width:160px;" id="user_nm" />
	                    </td>
	                	
	                </tr>
	            </tbody>
	        </table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common" id="btn_search">조회</a>
	    </div>
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
		searchListEngineerManage();
	});
	
	// 기술인협회 기술자정보 목록 조회
	function ListEngineerManage(pOrgTypeCd, pDutyNm, pUserNm) {
		$.ajax({
			url: "/AjaxBot/ListEngineerManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({"orgTypeCd": pOrgTypeCd, "dutyNm": pDutyNm, "userNm": pUserNm}),
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
        	 { Header: "구분", Type: "Text", Width: 0, SaveName: "orgTypeNm", Align: "Center", Hidden:true },     
        	 { Header: "직급", Type: "Text", Width: 50, SaveName: "dutyNm", Align: "Center" },    
             { Header: "사번", Type: "Text", Width: 70, SaveName: "userId", Align: "Center" },        
             { Header: "성명", Type: "Text", Width: 70, SaveName: "userNm", Align: "Center" },        
             { Header: "생년월일", Type: "Text", Width: 80, SaveName: "resNo", Align: "Center" },
             { Header: "현재등급", Type: "Text", Width: 70, SaveName: "ranking", Align: "Center"},            
             { Header: "현 역량지수", Type: "Float", Width: 80, SaveName: "rankingRate", Align: "Center"},
             { Header: "승급가능등급\n(승급교육 이수 후 등급)", Type: "Text", Width: 140, SaveName: "afterRanking", Align: "Center" },
             { Header: "기본교육/최초교육\n이수여부", Type: "Text", Width: 230, SaveName: "etc", Align: "Center" },
             { Header: "기본교육 현황", Type: "Text", Width: 100, SaveName: "basicEdu", Align: "Center" },
             { Header: "최초교육 현황\n품질관리", Type: "Text", Width: 175, SaveName: "qualityEdu", Align: "Center"},
             { Header: "근무 현장", Type: "Text", Width: 230, SaveName: "deptNm" }
        ];
		
        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetColFontUnderline("userId", true);
        mySheet.SetDataLinkMouse("userId", true);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }  
	
 	// 그리드 클릭 함수
	function mySheet_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}		
		
		if (mySheet.ColSaveName(Col) == "userId") {			
			var userId = mySheet.GetCellValue(Row, "userId");
			libraryFunc.createModal(null, null, null, 1100, 560, "기술경력", "/ModalBot/EngineerManageCareerList.do" + "?pUserId=" + userId );
   		}		
	}
	
	// 목록 조회 공통 함수
	function searchListEngineerManage() {
		var orgTypeCd = $("#org_type_cd").val();
		var dutyNm = $("#duty_nm").val();
		var userNm = $("#user_nm").val();
		
		ListEngineerManage(orgTypeCd, dutyNm, userNm);
	}
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {				
		searchListEngineerManage();
	});
		
</script>