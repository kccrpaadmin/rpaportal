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
	    	<a class="btn_common1" id="btn_add">추가</a>
	    	<a class="btn_common1" id="btn_save">저장</a>
	    </div>
	    <div class="divide_box">
	    	<div class="float_left_box" style="width:320px;">
	    		<!-- 트리영역 -->
	    		<div id="tree"></div>
	    	</div>
	    	<div class="float_left_box" style="width:790px;margin-left:10px;">
	    		<!-- 그리드영역 -->
				<div id="sheet"></div>
	    	</div>
	    	<input type="text" id="hdn_upcd">
	    	<input type="text" id="hdn_lvl">
	    </div>
	</div>
</div>

<script type="text/javascript">
		
	// 페이지 로드 
	$(document).ready(function (e) {
		listCodeTree("RA");
		listCodeChild("");
	});
	
	// 공통코드 트리 목록 조회
	function listCodeTree(pViewNode) {
		$.ajax({
			url: "/AjaxAdmin/ListCodeTree.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "viewNode": pViewNode }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				libraryFunc.createTree("tree", "st_type1", "공통코드", "298", "500", listDatas, null, tree_click, null);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 공통코드 자식 목록 조회
	function listCodeChild(pCd) {
		$.ajax({
			url: "/AjaxAdmin/ListCodeChild.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "upCd": pCd }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeGrid(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 그리드 생성 함수
    function makeGrid(pListDatas) {
    	commonFunc.initSheet("mySheet");
		
        var initdata = {};

        createIBSheet2(document.getElementById("sheet"), "mySheet", "790px", "563px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "코드", Type: "Text", Width: 100, SaveName: "cd", Align: "Center" },
            { Header: "코드명", Type: "Text", Width: 290, SaveName: "cdNm" },
            { Header: "상위코드", Type: "Text", Width: 100, SaveName: "upCd", Align: "Center", Edit: false },
            { Header: "레벨", Type: "Text", Width: 100, SaveName: "lvl", Align: "Center", Edit: false },
            { Header: "순번", Type: "Text", Width: 100, SaveName: "ord", Align: "Center" },
            { Header: "사용여부", Type: "Combo", Width: 100, SaveName: "useYn", ComboText: "Y|N", Align: "Center" }
        ];
		
        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(1);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
	// 트리 클릭 이벤트
    function tree_click(pNodeCd, pNodeNm, pParentNodeCd, pNodeChild, pNodeData, pParam) {
        listCodeChild(pNodeCd);
        $("#hdn_upcd").val(pNodeData.cd);
        $("#hdn_lvl").val(Number(pNodeData.lvl) + 1);
    }
 	
 	// 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add", function (e) {
    	mySheet.DataInsert(-1);
    });
 	
</script>