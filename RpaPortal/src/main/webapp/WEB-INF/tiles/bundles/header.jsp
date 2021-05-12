<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- CustumViewPreparer 결과값 받기 위해 필요 -->
<tiles:importAttribute name="curTime" />
<tiles:importAttribute name="userInfo" />

<div id="header_back">
	<div id="header">
		<div class="header_left">
			<a href="/Main/Home.do"><img src="/resources/imgs/logo/logo_home.png" /></a>
		</div>
		<div class="header_right">
			<span class="header_time_info">[${curTime}]</span>
			<span class="header_user_info">${userInfo}</span>
			<a href="/Account/Logout.do" class="header_logout">로그아웃</a>
		</div>
	</div>
</div>
