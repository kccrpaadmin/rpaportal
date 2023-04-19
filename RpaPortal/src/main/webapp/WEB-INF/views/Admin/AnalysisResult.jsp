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
				<div class="location_title">누적 절감시간</div>	
			</div>
			<div class="location_right">
				<span class="location_home">관리자</span>
				<span class="location_arrow">누적 절감시간</span>
			</div>
		</div>
		<!-- 검색영역 -->
	    <div class="search_dtl_box">
	        <table class="search_dtl_tbl">
	            <caption>검색영역</caption>
	            <colgroup>
	                <col style="width:8%;" />
	                <col style="width:40%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                	<th class="search_dtl_th">조회기간</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="datepicker_ym" readonly="readonly"  id="year_mon_day1" value="${beginDate}" />
	                        <span>~</span>
	                        <input type="text" class="datepicker_ym" readonly="readonly"  id="year_mon_day2" value="${endDate}" />
	                    </td>
	                </tr>	                
	            </tbody>
	        </table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_search" >조회</a>
	    </div>
		<!-- 메뉴 목록 -->
		<div id="menu_list_box"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 페이지 로드 
    $(document).ready(function (e) {
    	commonFunc.createDatepicker(".datepicker_ym", "YearMonthDay");
    	createBotMenulList();
    });
	
	// BOT 메뉴 목록 생성 함수
	function createBotMenulList() {
		var beginDate = $("#year_mon_day1").val();
		var endDate = $("#year_mon_day2").val();
		$.ajax({
			url: "/AjaxAdmin/ListProcessResult.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "beginDate": beginDate,"endDate": endDate }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				commonFunc.createResultMenulList("menu_list_box", listDatas);
				getTotTime();
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	function getTotTime() {
		var beginDate = $("#year_mon_day1").val();
		var endDate = $("#year_mon_day2").val();
		$.ajax({
			url: "/AjaxAdmin/ListProcessResultTot.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "beginDate": beginDate,"endDate": endDate }),
		    dataType : "json",
	        async: true,
			success: function(data) {
				$(".menu_list_cnt").text("총 "+data.totTime);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
		
	// 검색 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {
		createBotMenulList();
	});
 	
	// 업무설명 버튼 클릭 이벤트
	$(document).on("click", ".btn_work_info", function (e) {
  		var menuId = $(this).parent().parent().attr("id");
  		if(menuId.indexOf("RA004")) {
	  		libraryFunc.createModal(null, null, null, 500, 250, "업무설명", "/ModalBot/WorkInfo.do?pMenuId=" + menuId);
  		} else if(menuId.indexOf("RA002")) {
  			libraryFunc.createModal(null, null, null, 500, 250, "업무설명", "/ModalCrawl/WorkInfo.do?pMenuId=" + menuId + "&pEmpNo=" + commonFunc.certInfo.empNo);
  		}
	});
	
</script>