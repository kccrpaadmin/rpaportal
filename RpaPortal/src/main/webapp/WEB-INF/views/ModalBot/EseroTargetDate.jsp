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
 	
	// 봇 스케쥴 정보 생성
	function createSchedule(pMenuId, pMonthz, pDayz, pWeekz, pWeekDayz, pStartTime, pExpDate, pEmpNo) {
		$.ajax({
			url: "/AjaxBot/CreateSchedule.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId, "monthz": pMonthz, "dayz": pDayz, "weekz": pWeekz, "weekDayz": pWeekDayz, "startTime": pStartTime, "expDate": pExpDate, "empNo": pEmpNo }),
			dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "생성 되었습니다.", null, commonFunc.refreshPage);
				}
				else {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "오류가 발생 하였습니다.", null, commonFunc.refreshPage);
				}
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 봇 스케쥴 정보 삭제
	function deleteSchedule(pMenuId, pSeqStr) {
		$.ajax({
			url: "/AjaxBot/DeleteSchedule.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId, "seqStr": pSeqStr }),
			dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "삭제 되었습니다.", null, commonFunc.refreshPage);
				}
				else {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "오류가 발생 하였습니다.", null, commonFunc.refreshPage);
				}
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
            { Header: "순번", Type: "Date", Width: 300, SaveName: "yearMon", Align: "Center", Format: "Ym" }
        ];
		
        IBS_InitSheet(mySheet, initdata);
        //mySheet.SetEditable(1);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }	
	
 	// 저장 전, 확인 함수
	function createBotScheduleConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
	    	var monthz = getCheckBoxCheckValueStr("#chk_months");
	    	var dayz = getCheckBoxCheckValueStr("#chk_days");
	    	var weekz = getCheckBoxCheckValueStr("#chk_weeks");
	    	var weekDayz = getCheckBoxCheckValueStr("#chk_week_days");
	    	var startTime = $("#time_hour").val() + ":" + $("#time_minute").val();  
	    	var expDate = $("#exp_date").val();
	    	var empNo = commonFunc.certInfo.empNo;
	    	createSchedule(menuId, monthz, dayz, weekz, weekDayz, startTime, expDate, empNo);
        }
	}
 	
	// 삭제 전, 확인 함수
	function deleteBotScheduleConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
			var saveJson = mySheet.GetSaveJson().data;
			var saveJsonLen = saveJson.length;
			var seqStr = "";
			
			for (var i = 0; i < saveJsonLen; i++) {
				var seq = saveJson[i].seq;
				if (i == 0) {
					seqStr = seqStr + seq;	
				}
				else {
					seqStr = seqStr + "," +seq;
				}
			}
			
			deleteSchedule(menuId, seqStr);
        }
	}
 	
 	// 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add", function (e) {
    	mySheet.DataInsert(-1);
    });
 	
 	// 저장 버튼 클릭 이벤트
    $(document).on("click", "#btn_save", function (e) {
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장 하시겠습니까?", null, deleteBotScheduleConfirm);    	
    });
 	
</script>	