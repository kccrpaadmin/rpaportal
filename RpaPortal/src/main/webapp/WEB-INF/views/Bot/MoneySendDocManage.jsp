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
				<span class="location_arrow">BOT</span>
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
	    	<a class="btn_common" id="btn_search">조회</a>
	    </div>
	    <!-- 그리드영역 -->
   	    <div id="sheet"></div>
   	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a href="/Bot/ListMenu.do"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${outMenuVO.menuId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		
	});
		
</script>