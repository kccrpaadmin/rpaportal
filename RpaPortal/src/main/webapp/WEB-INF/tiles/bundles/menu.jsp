<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="lnb_bar">
	<a class="btn_lnb_bar_o" id="btn_lnb_menu_open"></a>
	<a class="btn_lnb_bar btn_lnb_bar_i">RPA 소개</a>
	<a class="btn_lnb_bar btn_lnb_bar_n">RPA 소식</a>
	<a class="btn_lnb_bar btn_lnb_bar_w">업무수행</a>
	<a class="btn_lnb_bar btn_lnb_bar_m">업무관리</a>
</div>
<div id="lnb_menu">
	<div class="lnb_menu_header">
		<div class="lnb_menu_header_left">MENU</div>
		<div class="lnb_menu_header_right">
			<a class="btn_lnb_menu_close" id="btn_lnb_menu_close"></a>
		</div>
	</div>
	<div class="lnb_menu_body">
		<a class="btn_lnb_menu btn_lnb_menu_i_b">RPA 소개</a>
		<div class="lnb_sub_menu_box">
			<a href="/Intro/Rpa.do" class="btn_lnb_sub_menu">RPA 소개</a>
			<a href="/Intro/Kpa.do" class="btn_lnb_sub_menu">K-RPA 소개</a>
		</div>
		<a class="btn_lnb_menu btn_lnb_menu_n_b">RPA 소식</a>
		<div class="lnb_sub_menu_box">
			<a href="/Board/ListBoard.do" class="btn_lnb_sub_menu">RPA 소식</a>
			<a class="btn_lnb_sub_menu">RPA 과제 건의함</a>
		</div>
		<a class="btn_lnb_menu btn_lnb_menu_w_b">업무수행</a>
		<div class="lnb_sub_menu_box">
			<a href="/Crawl/ListMenu.do" class="btn_lnb_sub_menu">웹크롤링</a>
			<a href="/Ocr/ListMenu.do" class="btn_lnb_sub_menu">OCR</a>
			<a href="/Bot/ListMenu.do" class="btn_lnb_sub_menu">BOT</a>
		</div>
		<a class="btn_lnb_menu btn_lnb_menu_m_b">업무관리</a>
		<div class="lnb_sub_menu_box">
			<a href="/Main/Home1.do" class="btn_lnb_sub_menu">업무관리</a>
			<a class="btn_lnb_sub_menu">업무관리</a>
		</div>
	</div>
</div>