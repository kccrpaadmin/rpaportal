<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<div class="search_dtl_box">
			<table class="search_dtl_tbl">
				<colgroup>
					<col style="width:10%;" />
	                <col style="width:20%;" />
					<col style="width:10%;" />
	                <col style="width:20%;" />
	                <col />
				</colgroup>
				<tbody>
					<tr>
						<th class="search_dtl_th">이름</th>
						<td class="search_dtl_td">
							<input type="text" class="txt_box_l" style="width:160px;" id="user_nm"  />										
						</td>
						<th class="search_dtl_th">생년월일</th>
						<td class="search_dtl_td">
							<input type="text" class="txt_box_l" style="width:160px;" id="res_no"  />												
						</td>	
						<td class="search_dtl_td">
							<input type="button"  id="btn_searchuser" value="조회" />		
						</td>			
					</tr>				
				</tbody>				
			</table>
	    </div>
		<!-- 그리드영역 -->
		<div class="grid_box">
		    <div class="grid_btn">
		    	<a class="btn_common2" id="btn_career_list_download">엑셀</a>
		    </div>	  
	    </div>  
		<div id="sheet"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var userId = "${userId}";
	var userNm = "${userNm}";
	var resNo = "${resNo}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		$("#user_nm").val(userNm);
		$("#res_no").val(resNo);		
		
		listEngineerManageCareerList(userId, "", "");
	});
		
	// 업체 조회
	function listEngineerManageCareerList(pUserId, pUserNm, pResNo) {
		$.ajax({
			url: "/AjaxBot/ListEngineerManageCareerList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "userId": pUserId, "userNm": pUserNm, "resNo":pResNo }),
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1060px", "400px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [                        
        	{ Header: "사번", Type: "Text", Width: 65, SaveName: "userId", Align: "Center"},
        	{ Header: "성명", Type: "Text", Width: 70, SaveName: "userNm", Align: "Center"},
        	{ Header: "생년월일", Type: "Text", Width: 80, SaveName: "resNo", Align: "Center"},
        	{ Header: "업체명", Type: "Text", Width: 130, SaveName: "vendorNm", Align: "Center"},
        	{ Header: "참여기간", Type: "Text", Width: 150, SaveName: "playDateTerm", Align: "Center"},
            { Header: "인정일", Type: "Text", Width: 70, SaveName: "approveDays", Align: "Center" },
            { Header: "사업명", Type: "Text", Width: 300, SaveName: "constNm", Align: "Center" },            
            { Header: "발주자", Type: "Text", Width: 180, SaveName: "orderVendorNm", Align: "Center" },
            { Header: "공사종류", Type: "Text", Width: 150, SaveName: "constType", Align: "Center" },      
            { Header: "공법", Type: "Text", Width: 100, SaveName: "constMethod", Align: "Center" },
            { Header: "직무분야", Type: "Text", Width: 80, SaveName: "jobKind", Align: "Center" },
            { Header: "전문분야", Type: "Text", Width: 90, SaveName: "specialKind", Align: "Center" },
            { Header: "담당업무", Type: "Text", Width: 100, SaveName: "assignWork", Align: "Center" },
            { Header: "직위", Type: "Text", Width: 60, SaveName: "titleNm", Align: "Center" },
            { Header: "신고구분", Type: "Text", Width: 60, SaveName: "reportType", Align: "Center" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);        
    }
		
    // 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_searchuser", function (e) {				
		var searchUserId = "";
		var searchUserNm = $("#user_nm").val();
		var searchResNo = $("#res_no").val();
		
		listEngineerManageCareerList(searchUserId, searchUserNm, searchResNo);
	});
    
	// 엑셀 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_career_list_download", function (e) {		
		var params = { Multipart: 0, FileName: "EngineerCareerList.xls",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		mySheet.Down2Excel(params);
	});
	
</script>