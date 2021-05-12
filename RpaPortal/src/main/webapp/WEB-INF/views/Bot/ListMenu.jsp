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
				<div class="location_title">BOT</div>	
			</div>
			<div class="location_right">
				<span class="location_home">홈</span>
				<span class="location_arrow">BOT</span>
			</div>
		</div>
		<!-- 검색박스 -->
		<div class="search_box">
			<input type="text" class="search_txt" id="search_txt" placeholder="검색어를 입력하세요" />
			<a class="btn_search" id="btn_search" ></a>
		</div>
		<!-- BOT 메뉴 목록 -->
		<div id="bot_menu_list_box"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 페이지 로드 
    $(document).ready(function (e) {
    	createBotMenulList($("#search_txt").val());
    });
	
	// BOT 메뉴 목록 생성 함수
	function createBotMenulList(pSearchTxt) {
		$.ajax({
			url: "/AjaxMenu/ListBotMenu.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "searchTxt": pSearchTxt }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				commonFunc.createBotMenulList("bot_menu_list_box", listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 메뉴 권한 조회 함수
	function getMenuAuth(pUrlType, pMenuId, pUserId, pTitleCd, pDeptCd, pAccessUrl) {
		$.ajax({
			url: "/AjaxMenu/GetMenuAuth.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "urlType": pUrlType, "menuId": pMenuId, "userId": pUserId, "titleCd": pTitleCd, "deptCd": pDeptCd }),
		    dataType : "json",
	        async: true,
			success: function(data) {
				checkAuthAndMoveDetail(data, pMenuId, pAccessUrl);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 권한 확인 및 상세 페이지 이동 함수
	function checkAuthAndMoveDetail(pData, pMenuId, pAccessUrl) {
		if (pData.authYn == "N") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "접근권한이 없습니다.", null, null);
			return false;
		}
		
		window.location.href = pAccessUrl + "?pMenuId=" + pMenuId + "&pEmpNo=" + commonFunc.certInfo.empNo;
	}
	
	// 검색 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {
		createBotMenulList($("#search_txt").val());
	});
 	
	// 검색 박스 엔터 이벤트
	$(document).on("keydown", "#search_txt", function (e) {
		if (e.keyCode == 13) {
			$("#btn_search").trigger("click");
		}
	});
	
	// 업무설명 버튼 클릭 이벤트
	$(document).on("click", ".btn_bot_work_info", function (e) {
  		var menuId = $(this).parent().parent().attr("id");
  		libraryFunc.createModal(null, null, null, 500, 250, "업무설명", "/ModalBot/WorkInfo.do?pMenuId=" + menuId);
	});
	
	// 업무수행 버튼 클릭 이벤트
	$(document).on("click", ".btn_bot_run_info", function (e) {
		var menuId = $(this).parent().parent().attr("id");
		var userId = commonFunc.certInfo.userId;
		var titleCd = commonFunc.certInfo.titleCd;
		var deptCd = commonFunc.certInfo.deptCd;
		var accessUrl = $(this).attr("runUrl");
		var checkNullYn = commonFunc.getCheckNullYn(accessUrl);
		
		if (checkNullYn == "Y") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "업무수행 대상 메뉴가 아닙니다.", null, null);
			return false;
		}
		else {
			getMenuAuth("Run", menuId, userId, titleCd, deptCd, accessUrl);	
		}
	});
	
	// 업무관리 버튼 클릭 이벤트
	$(document).on("click", ".btn_bot_manage_info", function (e) {
  		var menuId = $(this).parent().parent().attr("id");
  		var userId = commonFunc.certInfo.userId;
		var titleCd = commonFunc.certInfo.titleCd;
		var deptCd = commonFunc.certInfo.deptCd;
		var accessUrl = $(this).attr("manageUrl");
		var checkNullYn = commonFunc.getCheckNullYn(accessUrl);
		
		if (checkNullYn == "Y") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "업무관리 대상 메뉴가 아닙니다.", null, null);
			return false;
		}
		else {
			getMenuAuth("Manage", menuId, userId, titleCd, deptCd, accessUrl);	
		}
	});
	
</script>