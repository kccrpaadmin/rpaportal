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
				<div class="location_title">K-RPA 소개</div>	
			</div>
			<div class="location_right">
				<span class="location_home">홈</span>
				<span class="location_arrow">K-RPA 소개</span>
			</div>
		</div>
		<!-- 소개영역 -->
		<div class="intro_box">
			<img src="/resources/imgs/background/bg_kpa.jpg" class="intro_img" />
		</div>
		<!-- 동영상영역 -->
		<div class="intro_video">
			<video src="/resources/intro/vdo_g2b.mp4"
		  		width="778px"
		  		height="393px"
			  	poster="/resources/intro/img_g2b.jpg"
			  	preload="auto"
			  	controls>
  			</video>
		</div>
		<div class="intro_textbox" >
			<div><span class="intro_title">그룹사 인사발령 수집 업무 자동화 (총무부)</span></div>
			<div><span class="intro_description">그룹사 인사발령 정보를 수집하여 당사 그룹웨어에 게시합니다.</span></div>
		</div>
		<div class="intro_video">
			<video src="/resources/intro/vdo_personappoint.mp4"
		  		width="778px"
		  		height="393px"
			  	poster="/resources/intro/img_personappoint.jpg"
			  	preload="auto"
			  	controls>
  			</video>
		</div>
	</div>
</div>
