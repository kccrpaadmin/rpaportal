<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<div class="btn_box">
	    	<a class="btn_common1" id="btn_add">추가</a>
	    	<a class="btn_common1" id="btn_save">저장</a>
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
		listAdDailyReportTargetAd(menuId);
	});
	
	// 유튜브 광고 현황 수집 목록 조회
	function listAdDailyReportTargetAd(pMenuId) {
		$.ajax({
			url: "/AjaxBot/ListAdDailyReportTargetAd.do",
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1043px", "449px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "상태", Type: "Status", Width: 50, SaveName: "status", Align: "Center" },
            { Header: "번호", Type: "Text", Width: 50, SaveName: "adNo", Align: "Center", Edit: false },
            { Header: "광고명", Type: "Text", Width: 251, SaveName: "adNm", Edit: false, KeyField: true },
            { Header: "광고시작일자", Type: "Date", Width: 100, SaveName: "startDate", Align: "Center", Edit: false, KeyField: true },
            { Header: "광고 URL(Youtube URL)", Type: "Text", Width: 500, SaveName: "adUrl", Edit: false, KeyField: true },
            { Header: "등록일자", Type: "Date", Width: 100, SaveName: "regDate", Align: "Center", Edit: false }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
	// 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add", function (e) {
    	mySheet.DataInsert(-1);
    	mySheet.SetEditable(1);
    	mySheet.SetCellEditable(mySheet.GetDataLastRow(), "adNm", true);
    	mySheet.SetCellEditable(mySheet.GetDataLastRow(), "startDate", true);
    	mySheet.SetCellEditable(mySheet.GetDataLastRow(), "adUrl", true);
    	mySheet.SelectCell(mySheet.GetDataLastRow(), "adNm");
    });

	// 저장 버튼 클릭 이벤트
    $(document).on("click", "#btn_save", function (e) {		
    	var saveStr = mySheet.GetSaveString({"ValidKeyField": 1});
    	
    	if (saveStr == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "변경 사항이 없습니다.", null, null, null);
    		return false;
    	}
    	
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장하시겠습니까?", null, saveAdDailyReportTargetAd, null); 
    });
	
</script>