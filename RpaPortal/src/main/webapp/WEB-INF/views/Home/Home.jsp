<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<div class="home_box">
			<div class="home_header">
				<div class="home_header_left">
					<div class="home_title">공지사항</div>
					<div class="home_notice_content">RPA 오픈 안내</div>
				</div>
				<div class="home_header_right">
					<div class="home_title">OCR</div>
					<div class="home_ocr_content">텍스트 추출 바로가기</div>
				</div>
			</div>
			
			<div class="home_body">
				<div class="home_body_left">
					<div class="home_title">웹크롤링 운영현황</div>
					<div class="home_task"></div>
				</div>
				<div class="home_body_center">
					<div class="home_title">웹크롤링 타임라인</div>
					<div class="home_task"></div>
				</div>
				<div class="home_body_right">
					<div class="home_title">웹크롤링 사용현황</div>
					<div class="home_task"></div>
				</div>
			</div>
			
			<div class="home_body">
				<div class="home_body_left">
					<div class="home_title">BOT 운영현황</div>
					<div class="home_task"></div>
				</div>
				<div class="home_body_center">
					<div class="home_title">BOT 타임라인</div>
					<div class="home_task"></div>
				</div>
				<div class="home_body_right">
					<div class="home_title">BOT 사용현황</div>
					<div class="home_task"></div>
				</div>
			</div>
			
		</div>
		
		
	</div>
</div>

<script type="text/javascript">
	
	// 페이지 로드 
	$(document).ready(function (e) {
		$("body").css("background-color", "#f5f5fb");
	});

</script>