<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common" id="btn_add">추가</a>
	    	<a class="btn_common" id="btn_save">저장</a>
	    </div>
	    <!-- 그리드영역 -->
		<div id="sheet"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${menuId}";
	
    // 페이지 로드
    $(document).ready(function (e) {
    	listEseroTargetDate(menuId);
    });
	
 	// (세금)계산서, 전표 데이터 대사업무 수집 대상년월 목록 조회
	function listEseroTargetDate(pMenuId) {
		$.ajax({
			url: "/AjaxBot/ListEseroTargetDate.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId }),
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "460px", "197px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "상태", Type: "Status", Width: 80, SaveName: "status", Align: "Center" },
        	{ Header: "삭제", Type: "DelCheck", Width: 80, SaveName: "delete", Align: "Center" },
            { Header: "대상기간", Type: "Date", Width: 300, SaveName: "yearMon", Align: "Center", Format: "Ym" }
        ];
		
        IBS_InitSheet(mySheet, initdata);
        //mySheet.SetEditable(1);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }	
	
 	// 저장 전, 확인 함수
	function saveEseroTargetDateConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
	    	
			saveEseroTargetDate();
        }
	}
 	 	
 	// 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add", function (e) {
    	mySheet.DataInsert(-1);
    });
 	
 	// 저장 버튼 클릭 이벤트
    $(document).on("click", "#btn_save", function (e) {
    	
    	
   		libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장 하시겠습니까?", null, saveEseroTargetDateConfirm);    	
    });
 	
</script>	