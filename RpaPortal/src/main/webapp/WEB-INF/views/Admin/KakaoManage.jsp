<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
  .no-break {
    display: inline; /* 요소를 인라인으로 변경 */
    margin: 0;       /* 위아래 마진 제거 */
  }
</style>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<!-- 로케이션 -->
		<div class="location_box">
			<div class="location_left">
				<div class="location_title">카카오톡관리</div>	
			</div>
			<div class="location_right">
				<span class="location_home">관리자</span>
				<span class="location_arrow">카카오톡관리</span>
			</div>
		</div>
		<!-- 제목 -->
		<div class="title">카카오톡관리</div>
		<p class="no-break">※ <p class="no-break" style="color:red;"> bizmsg.lgcns.com 접속 후 알림톡 템플릿 검수요청하여 검수완료된 템플릿코드를 아래 그리드에 추가 </p></p>
		<br/>
		<p class="no-break">※ <p class="no-break" style="color:red;"> 사이트 계정정보는 홈페이지 담당자에게 요청 </p></p>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_add">추가</a>
	    	<a class="btn_common1" id="btn_save">저장</a>
	    </div>
	    <div class="divide_box">
	    	<!-- <div class="float_left_box" style="width:320px;">
	    		트리영역
	    		<div id="tree"></div>
	    	</div> -->
	    	<div class="float_left_box" style="margin-left:10px;">
	    		<!-- 그리드영역 -->
				<div id="sheet"></div>
	    	</div>
	    	<input type="hidden" id="hdn_upcd">
	    	<input type="hidden" id="hdn_lvl">
	    </div>
	</div>
</div>

<script type="text/javascript">
		
	//전역 변수
	var taskTypeComboCd = "${taskTypeComboCd}";
	var taskTypeComboCdNm = "${taskTypeComboCdNm}";
	var execTypeComboCd = "${execTypeComboCd}";
	var execTypeComboCdNm = "${execTypeComboCdNm}";
	var timeTypeComboCd = "${timeTypeComboCd}";
	var timeTypeComboCdNm = "${timeTypeComboCdNm}";
	var botMenuTypeComboCd = "${botMenuTypeComboCd}";
	var botMenuTypeComboCdNm = "${botMenuTypeComboCdNm}";
	var checkMenuId = "";

	// 페이지 로드 
	$(document).ready(function (e) {
		listKakaoTemplate();
	});
	
	// 공통코드 자식 목록 조회
	function listKakaoTemplate() {
		$.ajax({
			url: "/AjaxAdmin/ListKakaoTemplate.do",
			type: "POST",
			data : JSON.stringify({ "viewNode": "" }),
			contentType : "application/json; charset=utf-8",
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
	
	// 코드 저장
	function saveKakaoManage(pArrMenuManage) {
		$.ajax({
			url: "/AjaxAdmin/SaveKakaoManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(pArrMenuManage),
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1120px", "563px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "seq", Type: "Text", Width: 0, SaveName: "kakao_Seq", Align: "Center", Hidden: true },
        	{ Header: "상태ID", Type: "Status", Width: 40, SaveName: "status", Align: "Center" },
            { Header: "플러스ID", Type: "Text", Width: 100, SaveName: "kakao_Plusid", Align: "Center", Edit: false },
            { Header: "과제번호", Type: "Text", Width: 70, SaveName: "kakao_Task_Number", Align: "Center" },
            { Header: "템플릿코드", Type: "Text", Width: 70, SaveName: "kakao_Tempid", Align: "Center" },
            { Header: "템플릿명", Type: "Text", Width: 160, SaveName: "kakao_Tempnm", Align: "Center"},
            { Header: "템플릿내용", Type: "Text", Width: 669, SaveName: "kakao_Tempcn", Align: "Center" },
        ];
		
        IBS_InitSheet(mySheet, initdata);     
        mySheet.SetEditable(1);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
    // 저장 전, 확인 함수
	function saveKakaoManageConfirm(pOption) {
		
		if (pOption.sdBtnKey == "o") {

			var saveJson = mySheet.GetSaveJson().data;
			var saveJsonLen = saveJson.length;
			var arrData = [];

			for (var i = 0; i < saveJsonLen; i++) {
				var jsonData = {};

				jsonData.status = saveJson[i].status;
				jsonData.kakao_Seq = saveJson[i].kakao_Seq;
				jsonData.kakao_Plusid = saveJson[i].kakao_Plusid;
				jsonData.kakao_Task_Number = saveJson[i].kakao_Task_Number;
				jsonData.kakao_Tempid = saveJson[i].kakao_Tempid;
				jsonData.kakao_Tempnm = saveJson[i].kakao_Tempnm;
				jsonData.kakao_Tempcn = saveJson[i].kakao_Tempcn;
				
				arrData.push(jsonData);
			}

			saveKakaoManage(arrData);			
        }
	}
    
	// 저장 데이터 Null값 확인 함수
	function saveKakaoManageNullCheck() {
		var returnVal = true;
 		var saveJson = mySheet.GetSaveJson({AllSave:1});
		
		for (var i = 0; i < saveJson.data.length; i++) {
			if (commonFunc.getCheckNullYn(saveJson.data[i].kakao_Tempid) == "Y") {
				returnVal = false;
    		}
		}
		
		return returnVal;
	}
	
 	// 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add", function (e) {
    	var row = mySheet.DataInsert(-1);
    	mySheet.SetCellValue(row, "kakao_Plusid", "kcc_krpa");
    });
 	
    // 저장 버튼 클릭 이벤트
    $(document).on("click", "#btn_save", function (e) {
    	// MenuId가 입력되지 않은 경우
    	if (!saveKakaoManageNullCheck()) {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "메뉴ID 항목은 필수 입력 항목입니다. ", null, null, null);
    		return false;
    	}
    	
    	var saveStr = mySheet.GetSaveString();
    	
    	if (saveStr == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "변경 사항이 없습니다.", null, null, null);
    		return false;
    	}
    	
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장하시겠습니까?", null, saveKakaoManageConfirm, null);    
    });
 	
</script>