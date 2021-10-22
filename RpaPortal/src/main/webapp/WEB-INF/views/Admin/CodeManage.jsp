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
				<div class="location_title">공통코드관리</div>	
			</div>
			<div class="location_right">
				<span class="location_home">관리자</span>
				<span class="location_arrow">공통코드관리</span>
			</div>
		</div>
		<!-- 제목 -->
		<div class="title">공통코드관리</div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_search">조회</a>
	    	<a class="btn_common1" id="btn_search">추가</a>
	    	<a class="btn_common1" id="btn_search">저장</a>
	    </div>
	    <div class="divide_box">
	    	<div class="float_left_box" style="width:320px;">
	    		<div id="tree"></div>
	    	</div>
	    	<div class="float_left_box" style="width:790px;margin-left:10px;"></div>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	var ttt = [
		{cd: 'CO01', nm: '대한민국', parentCd: 'ROOT', childYn: 'Y', openYn: 'Y'},
		{cd: 'CO0001', nm: '강원', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0002', nm: '경기', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0003', nm: '경남', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0004', nm: '경북', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0005', nm: '광주', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0006', nm: '대구', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0007', nm: '대전', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0008', nm: '부산', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0009', nm: '서울', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0010', nm: '울산', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0011', nm: '인천', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0012', nm: '전남', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0013', nm: '전북', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0014', nm: '제주', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0015', nm: '충남', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO0016', nm: '충북', parentCd: 'CO01', childYn: 'Y', openYn: 'N'},
		{cd: 'CO00000001', nm: '강릉시', parentCd: 'CO0001', childYn: 'Y', openYn: 'N'},
		{cd: 'CO00000002', nm: '고성군', parentCd: 'CO0001', childYn: 'Y', openYn: 'N'},
		{cd: 'CO00000003', nm: '동해시', parentCd: 'CO0001', childYn: 'Y', openYn: 'N'},
		{cd: 'CO00000004', nm: '삼척시', parentCd: 'CO0001', childYn: 'Y', openYn: 'N'},
		{cd: 'CO00000005', nm: '속초시', parentCd: 'CO0001', childYn: 'Y', openYn: 'N'},
		{cd: 'CO00000006', nm: '양구군', parentCd: 'CO0001', childYn: 'Y', openYn: 'N'},
		{cd: 'CO00000007', nm: '양양군', parentCd: 'CO0001', childYn: 'Y', openYn: 'N'},
		{cd: 'CO00000008', nm: '영월군', parentCd: 'CO0001', childYn: 'Y', openYn: 'N'},
		{cd: 'CO00000009', nm: '원주시', parentCd: 'CO0001', childYn: 'Y', openYn: 'N'},
		{cd: 'CO00000010', nm: '인제군', parentCd: 'CO0001', childYn: 'Y', openYn: 'N'}
	]
	
	// 페이지 로드 
	$(document).ready(function (e) {
		libraryFunc.createTree("tree", "st_type1", "공통코드", "290", "500", ttt, "", tree_click, [1,2]);
	});
	
	// 트리 버튼 클릭 이벤트
    function tree_click(pNodeCd, pNodeNm, pParentNodeCd, pNodeChild, pNodeData, pParam) {
        // 선택 노드의 코드, 명칭 확인
        alert(pNodeCd);
        alert(pNodeNm);
        alert(pParentNodeCd);
        alert(pNodeChild);
        alert(pNodeData);
        alert(pParam);
        alert(pParam[0]);
        alert(pParam[1]);
    }
 	
</script>