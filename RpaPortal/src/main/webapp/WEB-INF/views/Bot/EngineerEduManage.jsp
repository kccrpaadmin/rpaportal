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
	    </div>
	    <!-- 그리드영역 -->
	    <div class="grid_box">
		    <div class="grid_title">품질관리교육</div>
		    <div class="grid_btn">
		    	<a class="btn_common2" id="btn_engineer_quality_edu_list_download">엑셀</a>
		    </div>
	    </div>
   	    <div id="sheet1"></div>
   	    <div class="grid_box">
   	    	<div class="grid_title">설계시공교육</div>
   	    	<div class="grid_btn">
		    	<a class="btn_common2" id="btn_engineer_const_edu_list_download">엑셀</a>
		    </div>
   	    </div>
   	    <div id="sheet2"></div>
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
		searchListEngineerEduManage();
	});
	
	// 기술인협회 교육정보 업무관리 품질관리교육 목록 조회
	function listEngineerEduManageQualityEdu(pOrgTypeCd, pDutyNm, pUserNm) {
		$.ajax({
			url: "/AjaxBot/ListBotEngineerEduManageQualityEdu.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({"orgTypeCd": pOrgTypeCd, "dutyNm": pDutyNm, "userNm": pUserNm}),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeGrid1(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 기술인협회 교육정보 업무관리 설계시공교육 목록 조회
	function listEngineerEduManageConstEdu(pOrgTypeCd, pDutyNm, pUserNm) {
		$.ajax({
			url: "/AjaxBot/ListBotEngineerEduManageConstEdu.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({"orgTypeCd": pOrgTypeCd, "dutyNm": pDutyNm, "userNm": pUserNm}),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeGrid2(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 그리드 생성 함수
    function makeGrid1(pListDatas) {
    	commonFunc.initSheet("mySheet1");
		
        var initdata = {};

        createIBSheet2(document.getElementById("sheet1"), "mySheet1", "1120px", "510px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	 { Header: "구분", Type: "Text", Width: 0, SaveName: "orgTypeNm", Align: "Center", Hidden:true },     
        	 { Header: "직급", Type: "Text", Width: 60, SaveName: "dutyNm", Align: "Center" },    
             { Header: "사번", Type: "Text", Width: 80, SaveName: "userId", Align: "Center" },        
             { Header: "성명", Type: "Text", Width: 80, SaveName: "userNm", Align: "Center" },        
             { Header: "생년월일", Type: "Text", Width: 80, SaveName: "resNo", Align: "Center" },
             { Header: "차수", Type: "Text", Width: 60, SaveName: "phase", Align: "Center"},            
             { Header: "기산일", Type: "Text", Width: 100, SaveName: "initDate", Align: "Center"},
             { Header: "수행일", Type: "Text", Width: 80, SaveName: "runDays", Align: "Center" },
             { Header: "3년되는날", Type: "Text", Width: 100, SaveName: "threeYearDate", Align: "Center" },
             { Header: "이수/교육종류", Type: "Text", Width: 100, SaveName: "eduType", Align: "Center" },
             { Header: "교육시간", Type: "Text", Width: 80, SaveName: "eduTimes", Align: "Center" },
             { Header: "이수일", Type: "Text", Width: 100, SaveName: "passDate", Align: "Center" },
             { Header: "이수시간", Type: "Text", Width: 80, SaveName: "passTimes", Align: "Center" },
             { Header: "비고", Type: "Text", Width: 120, SaveName: "etc", Align: "Center" }
        ];
		
        IBS_InitSheet(mySheet1, initdata);
        mySheet1.SetEditable(0);
        mySheet1.SetEditableColorDiff(0);
        mySheet1.SetColFontUnderline("userId", true);
        mySheet1.SetDataLinkMouse("userId", true);
        mySheet1.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet1.LoadSearchData(pListDatas);
    }  
	
 	// 그리드 생성 함수
    function makeGrid2(pListDatas) {
    	commonFunc.initSheet("mySheet2");
		
        var initdata = {};

        createIBSheet2(document.getElementById("sheet2"), "mySheet2", "1120px", "510px");
		
        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "구분", Type: "Text", Width: 0, SaveName: "orgTypeNm", Align: "Center", Hidden:true },     
       	 	{ Header: "직급", Type: "Text", Width: 60, SaveName: "dutyNm", Align: "Center" },    
            { Header: "사번", Type: "Text", Width: 80, SaveName: "userId", Align: "Center" },        
            { Header: "성명", Type: "Text", Width: 80, SaveName: "userNm", Align: "Center" },        
            { Header: "생년월일", Type: "Text", Width: 80, SaveName: "resNo", Align: "Center" },
            { Header: "차수", Type: "Text", Width: 60, SaveName: "phase", Align: "Center"},            
            { Header: "기산일", Type: "Text", Width: 100, SaveName: "initDate", Align: "Center"},
            { Header: "수행일", Type: "Text", Width: 80, SaveName: "runDays", Align: "Center" },
            { Header: "3년되는날", Type: "Text", Width: 100, SaveName: "threeYearDate", Align: "Center" },
            { Header: "이수/교육종류", Type: "Text", Width: 100, SaveName: "eduType", Align: "Center" },
            { Header: "교육시간", Type: "Text", Width: 80, SaveName: "eduTimes", Align: "Center" },
            { Header: "이수일", Type: "Text", Width: 100, SaveName: "passDate", Align: "Center" },
            { Header: "이수시간", Type: "Text", Width: 80, SaveName: "passTimes", Align: "Center" },
            { Header: "비고", Type: "Text", Width: 120, SaveName: "etc", Align: "Center" }
        ];
		
        IBS_InitSheet(mySheet2, initdata);
        mySheet2.SetEditable(0);
        mySheet2.SetEditableColorDiff(0);
        mySheet2.SetColFontUnderline("userId", true);
        mySheet2.SetDataLinkMouse("userId", true);
        mySheet2.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet2.LoadSearchData(pListDatas);
    }  
 
	// 목록 조회 함수
	function searchListEngineerEduManage() {
		var orgTypeCd = $("#org_type_cd").val() == "전체" ? "" : $("#org_type_cd").val(); 
		var dutyNm = $("#duty_nm").val();
		var userNm = $("#user_nm").val();
		
		listEngineerEduManageQualityEdu(orgTypeCd, dutyNm, userNm);
		listEngineerEduManageConstEdu(orgTypeCd, dutyNm, userNm);
	}
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {				
		searchListEngineerEduManage();
	});	
	
	// 품질관리교육 엑셀 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_engineer_quality_edu_list_download", function (e) {		
		var params = { Multipart: 0, FileName: "EngineerQualityEduList.xls",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		mySheet1.Down2Excel(params);
	});
	
	// 설계시공교육 엑셀 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_engineer_const_edu_list_download", function (e) {		
		var params = { Multipart: 0, FileName: "EngineerConstEduList.xls",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		mySheet2.Down2Excel(params);
	});
	
</script>