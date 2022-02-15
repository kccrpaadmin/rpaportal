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
				<div class="location_title">메뉴관리</div>	
			</div>
			<div class="location_right">
				<span class="location_home">관리자</span>
				<span class="location_arrow">메뉴관리</span>
			</div>
		</div>
		<!-- 제목 -->
		<div class="title">메뉴관리</div>
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
		
	//전역 변수
	var taskTypeComboCd = "${taskTypeComboCd}";
	var taskTypeComboCdNm = "${taskTypeComboCdNm}";
	var execTypeComboCd = "${execTypeComboCd}";
	var execTypeComboCdNm = "${execTypeComboCdNm}";

	// 페이지 로드 
	$(document).ready(function (e) {
		listMenuTree("RA");
		listMenuChild("");
	});
	
	// 메뉴 트리 목록 조회
	function listMenuTree(pViewNode) {
		$.ajax({
			url: "/AjaxAdmin/ListMenuTree.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "viewNode": pViewNode }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				libraryFunc.createTree("tree", "st_type1", "메뉴", "298", "500", listDatas, null, tree_click, null);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 공통코드 자식 목록 조회
	function listMenuChild(pMenuId) {
		$.ajax({
			url: "/AjaxAdmin/ListMenuChild.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "upMenuId": pMenuId }),
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
	
	// 메뉴 저장
	function saveMenuManage(pArrMenuManage) {
		$.ajax({
			url: "/AjaxAdmin/SaveMenuManage.do",
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "790px", "563px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "상태", Type: "Status", Width: 30, SaveName: "status", Align: "Center" },
            { Header: "메뉴", Type: "Text", Width: 80, SaveName: "menuId", Align: "Center" },
            { Header: "메뉴명", Type: "Text", Width: 280, SaveName: "menuNm" },
            { Header: "상위메뉴", Type: "Text", Width: 70, SaveName: "upMenuId", Align: "Center", Edit: false },
            { Header: "수행타입", Type: "Combo", Width: 80, SaveName: "taskTypeCd", Align: "Center", ComboText: taskTypeComboCdNm, ComboCode: taskTypeComboCd },
            { Header: "실행타입", Type: "Combo", Width: 80, SaveName: "execTypeCd", Align: "Center", ComboText: execTypeComboCdNm, ComboCode: execTypeComboCd },
            { Header: "레벨", Type: "Text", Width: 50, SaveName: "lvl", Align: "Center", Edit: false },
            { Header: "순번", Type: "Text", Width: 50, SaveName: "ord", Align: "Center" },
            { Header: "업무수행 URL", Type: "Text", Width: 160, SaveName: "runUrl" },
            { Header: "업무관리 URL", Type: "Text", Width: 160, SaveName: "manageUrl" },
            { Header: "내용", Type: "Text", Width: 200, SaveName: "content" },
            { Header: "사용여부", Type: "Combo", Width: 60, SaveName: "useYn", ComboText: "Y|N", Align: "Center" }
        ];
		
        IBS_InitSheet(mySheet, initdata);     
        mySheet.SetEditable(1);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
    // 저장 전, 확인 함수
	function saveMenuManageConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
			var saveJson = mySheet.GetSaveJson().data;
			var saveJsonLen = saveJson.length;
			var arrData = [];
			
			for (var i = 0; i < saveJsonLen; i++) {
				var jsonData = {};

				jsonData.status = saveJson[i].status;
				jsonData.cd = saveJson[i].menuId;
				jsonData.cdNm = saveJson[i].menuNm;
				jsonData.upCd = saveJson[i].upMenuId;
				jsonData.taskTypeCd = saveJson[i].taskTypeCd;
				jsonData.execTypeCd = saveJson[i].execTypeCd;
				jsonData.lvl = saveJson[i].lvl;
				jsonData.ord = saveJson[i].ord;
				jsonData.runUrl = saveJson[i].runUrl;
				jsonData.manageUrl = saveJson[i].manageUrl;
				jsonData.content = saveJson[i].content;
				jsonData.useYn = saveJson[i].useYn;
				jsonData.empNo = commonFunc.certInfo.empNo;				
				
				arrData.push(jsonData);										
			}
			
			saveMenuManage(arrData);			
        }
	}
	
	// 트리 클릭 이벤트
    function tree_click(pNodeCd, pNodeNm, pParentNodeCd, pNodeChild, pNodeData, pParam) {
        listMenuChild(pNodeCd);
        $("#hdn_upcd").val(pNodeData.cd);
        $("#hdn_lvl").val(Number(pNodeData.lvl) + 1);
    }
 	
 	// 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add", function (e) {
    	var row = mySheet.DataInsert(-1);
    	mySheet.SetCellValue(row, "upCd", $("#hdn_upcd").val());
    	mySheet.SetCellValue(row, "lvl", $("#hdn_lvl").val());
    });
 	
    // 저장 버튼 클릭 이벤트
    $(document).on("click", "#btn_save", function (e) {
		var saveStr = mySheet.GetSaveString();
    	
    	if (saveStr == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "변경 사항이 없습니다.", null, null, null);
    		return false;
    	}
    	
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장하시겠습니까?", null, saveMenuManageConfirm, null);    
    });
 	
</script>