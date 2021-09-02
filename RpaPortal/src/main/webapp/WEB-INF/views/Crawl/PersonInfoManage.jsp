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
	                <col style="width:8%;" />
	                <col style="width:20%;" />
	                <col style="width:8%;" />
	                <col style="width:20%;" />
	                <col style="width:8%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                	<th class="search_dtl_th">회사명</th>
	                    <td class="search_dtl_td">
	                        ${orgComboBox}
	                    </td>
	                	<th class="search_dtl_th">부서명</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="txt_box_l" style="width:160px;" id="dept_nm" />
	                    </td>
	                    <td class="search_dtl_td"></td>
	                    <td class="search_dtl_td"></td>
	                </tr>
	                <tr>
	                	<th class="search_dtl_th">이름</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="txt_box_l" style="width:160px;" id="user_nm" />
	                    </td>
	                	<th class="search_dtl_th">직위</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="txt_box_l" style="width:160px;" id="duty_nm" />
	                    </td>
	                	<th class="search_dtl_th">담당업무</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="txt_box_l" style="width:160px;" id="task" />
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
		listPersonInfoManage(menuId, $("#combo_org").val(), $("#dept_nm").val(), $("#user_nm").val(), $("#duty_nm").val(), $("#task").val());
	});
	
	// 발주처 직원 정보 목록 조회 (업무관리)
	function listPersonInfoManage(pMenuId, pOrgNm, pDeptNm, pUserNm, pDutyNm, pTask) {
		$.ajax({
			url: "/AjaxCrawl/ListPersonInfoManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId, "orgNm": pOrgNm, "deptNm": pDeptNm, "userNm": pUserNm, "dutyNm": pDutyNm, "task": pTask }),
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

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },
            { Header: "발주처", Type: "Text", Width: 200, SaveName: "orgNm", Align: "Center" },
            { Header: "부서명", Type: "Text", Width: 220, SaveName: "deptNm" },
            { Header: "이름", Type: "Text", Width: 110, SaveName: "userNm", Align: "Center" },
            { Header: "직위", Type: "Text", Width: 110, SaveName: "dutyNm", Align: "Center" },
            { Header: "전화번호", Type: "Text", Width: 110, SaveName: "tel", Align: "Center" },
            { Header: "담당업무", Type: "Text", Width: 370, SaveName: "task" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
   	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {
		listPersonInfoManage(menuId, $("#combo_org").val(), $("#dept_nm").val(), $("#user_nm").val(), $("#duty_nm").val(), $("#task").val());
	});
	
</script>