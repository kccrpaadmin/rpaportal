<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<!-- 웹크롤링 업무설명 -->
		<div class="work_info">
			<div class="info_line">
				<span class="info_line_title">${outMenuVO.menuNm}</span>
			</div>
			<div class="info_line">
				<span class="info_line_content">담당부서 : ${outMenuVO.deptNm}</span>
			</div>
			<div class="info_line">
				<span class="info_line_content">기능설명 : 편의기능</span>
			</div>
			<div class="info_line">
				<span class="info_line_content">${outMenuVO.content}</span>	
			</div>
		</div>
	</div>
</div>