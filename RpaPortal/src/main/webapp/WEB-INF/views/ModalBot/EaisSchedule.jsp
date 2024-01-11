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
		<!-- <div><p style="margin-top:5px;">※ '광고명'은 현황 엑셀 파일에 들어갈 광고명으로 작성하시기 바랍니다.</p></div> -->
	</div>
</div>

<script type="text/javascript">
		
	// 전역 변수
	var menuId = "${menuId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listEaisSchedule(menuId);
	});
	
	// 세움터 스케줄 조회
	function listEaisSchedule(pMenuId) {
		$.ajax({
			url: "/AjaxBot/ListEaisSchedule.do",
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
	
	// 스케줄 저장
	function saveEaisSchedule(pArrEaisSchedule) {
		$.ajax({
			url: "/AjaxBot/SaveEaisSchedule.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(pArrEaisSchedule),
			dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "저장 되었습니다.", null, commonFunc.refreshPage, null);
				}
				else {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "오류가 발생 하였습니다.", null, commonFunc.refreshPage, null);
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "760px", "249px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "편집가능", Type: "Text", Width: 0, SaveName: "editYn", Align: "Center", Edit: false, Hidden: true },
            { Header: "상태", Type: "Status", Width: 50, SaveName: "status", Align: "Center" },
            { Header: "번호", Type: "Text", Width: 50, SaveName: "seq", Align: "Center", Edit: false },
            { Header: "현장명", Type: "Text", Width: 300, SaveName: "siteNm", Edit: true, KeyField: true },
            { Header: "시작일자", Type: "Date", Width: 100, SaveName: "startDate", Align: "Center", Edit: false, KeyField: true },
            { Header: "종료일자", Type: "Date", Width: 100, SaveName: "finishDate", Align: "Center", Edit: true, KeyField: true },
            { Header: "종료처리여부", Type: "CheckBox", Width: 100, SaveName: "finishYn", Align: "Center", Edit: true, KeyField: true },
            { Header: "등록자", Type: "Text", Width: 100, SaveName: "regUserNm", Align: "Center", Edit: false },
            { Header: "편집가능", Type: "Text", Width: 100, SaveName: "editYn", Align: "Center", Edit: false, Hidden: true }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);        
    }
	
	function mySheet_OnSearchEnd(code, message){
		if(code == 0){
	        for(i = 0; i <= mySheet.RowCount(); i++){
	        	if(mySheet.GetCellValue(i,0) == "N"){
	        		mySheet.SetRowEditable(i,0);
	        	}
	        }
		} else {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "조회 중 오류가 발생하였습니다.", null, null, null);
    		return false;
		}
	}
	
	function saveEaisScheduleConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
			var saveJson = mySheet.GetSaveJson().data;
			var saveJsonLen = saveJson.length;
			var arrData = [];
			
			if (saveJsonLen > 0){
				for (var i = 0; i < saveJsonLen; i++) {
					var jsonData = {};
					jsonData.status = saveJson[i].status;
					jsonData.seq = saveJson[i].seq;
					jsonData.siteNm = saveJson[i].siteNm;
					jsonData.startDate = saveJson[i].startDate;
					jsonData.finishDate = saveJson[i].finishDate;
					jsonData.finishYn = saveJson[i].finishYn;
					jsonData.empNo = commonFunc.certInfo.empNo;
					arrData.push(jsonData);
				}
				
				saveEaisSchedule(arrData);
			}	
		}	
	}
	
	// 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add", function (e) {
    	mySheet.DataInsert(0);
    	mySheet.SetEditable(1);
    	
    	var today = new Date();
    	today = today.format("yyyy-MM-dd");
    	
    	mySheet.SetCellValue(1, 4, today);  // 시작일자는 오늘날짜로 지정 
    });

	// 저장 버튼 클릭 이벤트
    $(document).on("click", "#btn_save", function (e) {		
    	var saveStr = mySheet.GetSaveString({"ValidKeyField": 1});
    	
    	if (saveStr == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "변경 사항이 없습니다.", null, null, null);
    		return false;
    	}
    	
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장하시겠습니까?", null, saveEaisScheduleConfirm, null); 
    });
	
</script>