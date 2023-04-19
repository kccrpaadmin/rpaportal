<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="lnb_bar">
	<a class="btn_lnb_bar_o" id="btn_lnb_menu_open"></a>
	<a class="btn_lnb_bar btn_lnb_bar_a">관리자</a>
</div>
<div id="lnb_menu">
	<div class="lnb_menu_header">
		<div class="lnb_menu_header_left">MENU</div>
		<div class="lnb_menu_header_right">
			<a class="btn_lnb_menu_close" id="btn_lnb_menu_close"></a>
		</div>
	</div>
	<div class="lnb_menu_body">
		<a class="btn_lnb_menu btn_lnb_menu_a_b">관리자</a>
		<div class="lnb_sub_menu_box">
			<a href="/Admin/CodeManage.do" class="btn_lnb_sub_menu">공통코드관리</a>
			<a href="/Admin/MenuManage.do" class="btn_lnb_sub_menu">메뉴관리</a>
			<a href="/Admin/AuthManage.do" class="btn_lnb_sub_menu">권한관리</a>
			<a href="/Admin/AnalysisManage.do" class="btn_lnb_sub_menu">방문자통계</a>
			<a href="/Admin/AnalysisResult.do" class="btn_lnb_sub_menu">누적 절감시간</a>
		</div>		
	</div>
</div>