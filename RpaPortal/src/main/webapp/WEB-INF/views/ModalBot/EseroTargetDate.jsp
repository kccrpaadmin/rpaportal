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
	
    // (세금)계산서, 전표 데이터 대사업무 대상기간 생성
    function saveEseroTargetDate(pMenuId, pEmpNo, pEseroTargetDate) {
    	$.ajax({
			url: "/AjaxBot/SaveEseroTargetDate.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId, "empNo": pEmpNo, "eseroTargetDate": pEseroTargetDate }),
		    dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "요청이 성공 하였습니다.", null, commonFunc.refreshPage);
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
    
 	// (세금)계산서, 전표 데이터 대사업무 대상기간 목록 조회
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
	
 	// 저장 데이터 Null값 확인 함수
	function saveEseroTargetDateNullCheck() {
		var returnVal = true;
 		var saveJson = mySheet.GetSaveJson({AllSave:1});
		
		for (var i = 0; i < saveJson.data.length; i++) {
			if (commonFunc.getCheckNullYn(saveJson.data[i].yearMon) == "Y") {
				returnVal = false;
    		}
		}
		
		return returnVal;
	}
	
	// 저장 데이터 Row값 확인 함수
	function saveEseroTargetDateRowCheck() {
		var returnVal = true;
		var saveJson = mySheet.GetSaveJson({AllSave:1});
    	var saveDataArr = [];
    	
    	for (var i = 0; i < saveJson.data.length; i++) {
    		// 삭제 데이터 제거 
    		if (saveJson.data[i].delete == 0) {
    			// 중복 데이터 제거
    			if (!saveDataArr.includes(saveJson.data[i].yearMon)) {
    				saveDataArr.push(saveJson.data[i].yearMon);
        	    }
    		}
		}
		
    	// 값이 없는 경우
    	if (saveDataArr.length == 0) {
    		returnVal = false;
    	}
    	
		return returnVal;
	}
 	
 	// 저장 전, 확인 함수
	function saveEseroTargetDateConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
			var saveJson = mySheet.GetSaveJson({AllSave:1});
	    	var saveDataArr = [];
	    	var saveDataStr = "";
	    	
	    	for (var i = 0; i < saveJson.data.length; i++) {
	    		// 삭제 데이터 제거 
	    		if (saveJson.data[i].delete == 0) {
	    			// 중복 데이터 제거
	    			if (!saveDataArr.includes(saveJson.data[i].yearMon)) {
	    				saveDataArr.push(saveJson.data[i].yearMon);
	        	    }
	    		}
			}
	    	
	    	for (var i = 0; i < saveDataArr.length; i++) {
				if (commonFunc.getCheckNullYn(saveDataStr) == "Y") {
					saveDataStr = saveDataArr[i];
				}
				else {
					saveDataStr = saveDataStr + "≡" + saveDataArr[i];
				}
			}
	    	
	    	saveEseroTargetDate(menuId, commonFunc.certInfo.empNo, saveDataStr);
        }
	}
 	
 	// 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add", function (e) {
    	mySheet.DataInsert(-1);
    });
 	
 	// 저장 버튼 클릭 이벤트
    $(document).on("click", "#btn_save", function (e) {
   		
    	// 대상기간이 입력되지 않은 경우
    	if (!saveEseroTargetDateNullCheck()) {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "대상기간을 선택하지 않았습니다.", null, null);
    		return false;
    	}
    	
    	// 저장되야하는 데이터가 존재하지 않는 경우
    	if (!saveEseroTargetDateRowCheck()) {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "저장대상 데이터가 존재하지 않습니다.", null, null);
    		return false;
    	}
   	
		libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장 하시겠습니까?", null, saveEseroTargetDateConfirm);    	
    });
 	
</script>	