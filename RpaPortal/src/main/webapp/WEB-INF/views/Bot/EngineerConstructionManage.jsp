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
	    	<a class="btn_common1" id="btn_search">조회</a>
	    	<!-- <a class="btn_common1" id="btn_searchCareerList">전체기술경력조회</a>  -->
	    </div>
	    <div>
	    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_jikmu1" /><span class="spn_every">도시·교통</span></label>
	    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_jikmu2" /><span class="spn_every">건설지원</span></label>
	    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_jikmu3" /><span class="spn_every">건축</span></label>
	    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_jikmu4" /><span class="spn_every">토목</span></label>
	    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_jikmu5" /><span class="spn_every">안전관리</span></label>
	    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_jikmu6" /><span class="spn_every">환경</span></label>
	    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_jikmu7" /><span class="spn_every">광업</span></label>
	    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_jikmu8" /><span class="spn_every">조경</span></label>
	    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_jikmu9" /><span class="spn_every">기계</span></label>
	    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_jikmu10" /><span class="spn_every">전기·전자</span></label>
	    </div>
	    <!-- 그리드영역 -->
	    <div class="grid_box">
		    <div class="grid_btn">
		    	<a class="btn_common2" id="btn_engineer_list_download" style="margin-bottom:3px;">엑셀</a>
		    </div>	  
	    </div>  
   	    <div id="sheet"></div>
   	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a href="/Bot/ListMenu.do?pCategoryCd=${outMenuVO.categoryCd}"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
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
	function ListEngineerManage(pMenuId, pOrgTypeCd, pDutyNm, pUserNm) {
		$.ajax({
			url: "/AjaxBot/ListEngineerConstructionManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({"menuId": pMenuId, "orgTypeCd": pOrgTypeCd, "dutyNm": pDutyNm, "userNm": pUserNm}),
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
             { Header: "도시·교통", Type: "Text", Width: 70, SaveName: "rk1", Align: "Center"},
             { Header: "도시·교통", Type: "Text", Width: 70, SaveName: "jikmu1", Align: "Center"},
             { Header: "건설지원", Type: "Text", Width: 70, SaveName: "rk2", Align: "Center"},
             { Header: "건설지원", Type: "Text", Width: 70, SaveName: "jikmu2", Align: "Center"},
             { Header: "건축", Type: "Text", Width: 70, SaveName: "rk3", Align: "Center"},
             { Header: "건축", Type: "Text", Width: 70, SaveName: "jikmu3", Align: "Center"},
             { Header: "토목", Type: "Text", Width: 70, SaveName: "rk4", Align: "Center"},
             { Header: "토목", Type: "Text", Width: 70, SaveName: "jikmu4", Align: "Center"},
             { Header: "안전관리", Type: "Text", Width: 70, SaveName: "rk5", Align: "Center"},
             { Header: "안전관리", Type: "Text", Width: 70, SaveName: "jikmu5", Align: "Center"},
             { Header: "환경", Type: "Text", Width: 70, SaveName: "rk6", Align: "Center"},
             { Header: "환경", Type: "Text", Width: 70, SaveName: "jikmu6", Align: "Center"},
             { Header: "광업", Type: "Text", Width: 70, SaveName: "rk7", Align: "Center"},
             { Header: "광업", Type: "Text", Width: 70, SaveName: "jikmu7", Align: "Center"},
             { Header: "조경", Type: "Text", Width: 70, SaveName: "rk8", Align: "Center"},
             { Header: "조경", Type: "Text", Width: 70, SaveName: "jikmu8", Align: "Center"},
             { Header: "기계", Type: "Text", Width: 70, SaveName: "rk9", Align: "Center"},
             { Header: "기계", Type: "Text", Width: 70, SaveName: "jikmu9", Align: "Center"},
             { Header: "전기·전자", Type: "Text", Width: 70, SaveName: "rk10", Align: "Center"},
             { Header: "전기·전자", Type: "Text", Width: 70, SaveName: "jikmu10", Align: "Center"},
             { Header: "근무 현장", Type: "Text", Width: 230, SaveName: "deptNm" }
        ];
		
        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        //mySheet.SetColFontUnderline("userId", true);
        //mySheet.SetDataLinkMouse("userId", true);
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
			var userNm = mySheet.GetCellValue(Row, "userNm");
			var resNo = mySheet.GetCellValue(Row, "resNo");

			//libraryFunc.createModal(null, null, null, 1100, 560, "기술경력", "/ModalBot/EngineerManageCareerList.do" + "?pUserId=" + userId + "&pUserNm=" + userNm + "&pResNo=" + resNo );
   		}		
	}
	
	// 목록 조회 공통 함수
	function searchListEngineerManage() {
		var orgTypeCd = $("#org_type_cd").val();
		var dutyNm = $("#duty_nm").val();
		var userNm = $("#user_nm").val();
		
		ListEngineerManage(menuId, orgTypeCd, dutyNm, userNm);
	}
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {				
		searchListEngineerManage();
	});
	
	// 전체기술경력조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_searchCareerList", function (e) {				
		var userId = "";		
		var userNm = "";		
		var resNo = "";
		
		libraryFunc.createModal(null, null, null, 1100, 560, "기술경력", "/ModalBot/EngineerManageCareerList.do" + "?pUserId=" + userId + "&pUserNm=" + userNm + "&pResNo=" + resNo );
	});
	
	// 엑셀 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_engineer_list_download", function (e) {		
		var params = { Multipart: 0, FileName: "EngineerList.xls",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20, HiddenColumn:1 }
		mySheet.Down2Excel(params);
	});
		
	$(document).on("click", ".chk_every", function (e) {
		var chk_array = new Array();
		var chk_string;
    	if ($("#chk_jikmu1").prop("checked") == true) {
    		chk_array.push("5");
    		chk_array.push("6");
    	}
    	if ($("#chk_jikmu2").prop("checked") == true) {
    		chk_array.push("7");
    		chk_array.push("8");
    	}
    	if ($("#chk_jikmu3").prop("checked") == true) {
    		chk_array.push("9");
    		chk_array.push("10");
    	}
    	if ($("#chk_jikmu4").prop("checked") == true) {
    		chk_array.push("11");
    		chk_array.push("12");
    	}
    	if ($("#chk_jikmu5").prop("checked") == true) {
    		chk_array.push("13");
    		chk_array.push("14");
    	}
    	if ($("#chk_jikmu6").prop("checked") == true) {
    		chk_array.push("15");
    		chk_array.push("16");
    	}
    	if ($("#chk_jikmu7").prop("checked") == true) {
    		chk_array.push("17");
    		chk_array.push("18");
    	}
    	if ($("#chk_jikmu8").prop("checked") == true) {
    		chk_array.push("19");
    		chk_array.push("20");
    	}
    	if ($("#chk_jikmu9").prop("checked") == true) {
    		chk_array.push("21");
    		chk_array.push("22");
    	}
    	if ($("#chk_jikmu10").prop("checked") == true) {
    		chk_array.push("23");
    		chk_array.push("24");
    	}
    	
    	mySheet.SetColHidden([
    		{Col : 5, Hidden:0},
    		{Col : 6, Hidden:0},
    		{Col : 7, Hidden:0},
    		{Col : 8, Hidden:0},
    		{Col : 9, Hidden:0},
    		{Col : 10, Hidden:0},
    		{Col : 11, Hidden:0},
    		{Col : 12, Hidden:0},
    		{Col : 13, Hidden:0},
    		{Col : 14, Hidden:0},
    	]);
    	
    	
    	for(var i = 0; i < chk_array.length; i++)
   		{
	    	mySheet.SetColHidden([
	    		{Col : chk_array[i], Hidden:1}
	    	]);
   		}
    });
</script>