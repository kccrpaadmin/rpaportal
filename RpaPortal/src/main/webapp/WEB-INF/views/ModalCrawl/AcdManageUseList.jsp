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
	                <col style="width:30%;" />
	                <col />
				</colgroup>
				<tbody>
					<tr>
						<th class="search_dtl_th">조회년월</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="datepicker_ym" readonly="readonly"  id="start_date" value="${startDate}" />
	                        <span>~</span>
	                        <input type="text" class="datepicker_ym" readonly="readonly" id="end_date" value="${endDate}" />
	                    </td>						
						<td class="search_dtl_td">							
							<input type="button"  id="btn_search" value="조회" />						
						</td>				
					</tr>				
				</tbody>				
			</table>
	    </div>	
		<!-- 그리드영역 -->		
		<div id="sheet"></div>
	</div>
</div>

<script type="text/javascript">

	var menuId = "${menuId}";

	// 페이지 로드 
	$(document).ready(function (e) {
		commonFunc.createDatepicker(".datepicker_ym", "YearMonth");
		searchListAcdManageUseList();
	});
	
	//  사용자 현황 조회
	function listAcdManageUseList(pStartDate, pEndDate) {
		$.ajax({			
			url: "/AjaxCrawl/ListAcdManageUseList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "startDate": pStartDate, "endDate": pEndDate}),
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "600px", "250px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	 { Header: "성명", Type: "Text", Width: 90, SaveName: "usernm",  Align: "Left" },
             { Header: "부서명", Type: "Text", Width: 150, SaveName: "deptnm", Align: "Left" },
             { Header: "직급", Type: "Text", Width: 90, SaveName: "titlenm", Align: "Left" },
             { Header: "접속기기", Type: "Text", Width: 90, SaveName: "accessdevice" , Align: "Left" },
             { Header: "조회년월", Type: "Text", Width: 100, SaveName: "userdate" , Align: "Left"},
             { Header: "조회수", Type: "Int", Width: 80, SaveName: "cnt", Align: "Center" },                         
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	

	// 목록 조회 공통 함수
	function searchListAcdManageUseList() {			
		
		listAcdManageUseList(commonFunc.getReplaceAll($("#start_date").val(), "-", ""), commonFunc.getReplaceAll($("#end_date").val(), "-", ""));
	}	

	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {								
		searchListAcdManageUseList();
	});
		
</script>