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
				<div class="location_title">권한관리</div>	
			</div>
			<div class="location_right">
				<span class="location_home">관리자</span>
				<span class="location_arrow">권한관리</span>
			</div>
		</div>
		<!-- 제목 -->
		<div class="title">권한관리</div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_save">저장</a>
	    </div>
	    <div class="divide_box">
	    	<div class="float_left_box" style="width:320px;">
	    		<!-- 트리영역 -->
	    		<div id="tree"></div>
	    	</div>
	    	<div class="float_left_box" style="width:790px;margin-left:10px;">	    		
	    		<!-- 그리드영역 -->
	    		<div class="grid_box">
		    		<div class="grid_title" id="menuNm">업무수행</div>		    		
				    <div class="grid_btn">
				    	<a class="btn_common5" id="btn_add_run_user">개인</a>
				    	<a class="btn_common5" id="btn_add_run_degree">직급</a>
				    	<a class="btn_common5" id="btn_add_run_dept">부서</a>
				    </div>
		    	</div>
				<div id="sheet1"></div><br>
				<div class="grid_box">
		    		<div class="grid_title" id="menuNm">업무관리</div>
		    		<div class="grid_btn">
				    	<a class="btn_common5" id="btn_add_manage_user">개인</a>
				    	<a class="btn_common5" id="btn_add_manage_degree">직급</a>
				    	<a class="btn_common5" id="btn_add_manage_dept">부서</a>
				    </div>
		    	</div>
				<div id="sheet2"></div>
	    	</div>
	    	<input type="hidden" id="hdn_upcd">
	    	<input type="hidden" id="hdn_lvl">
	    </div>
	</div>
</div>

<script type="text/javascript">
		
	//전역 변수
	menuId = "";

	// 페이지 로드 
	$(document).ready(function (e) {
		listMenuTree("RA");
		listMenuAuthRunChild("");
		listMenuAuthManageChild("");
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
	
	// 메뉴별 업무수행 권한 자식 목록 조회
	function listMenuAuthRunChild(pMenuId) {
		$.ajax({
			url: "/AjaxAdmin/ListMenuAuthRunChild.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeGrid1(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 메뉴별 업무관리 권한 자식 목록 조회
	function listMenuAuthManageChild(pMenuId) {
		$.ajax({
			url: "/AjaxAdmin/ListMenuAuthManageChild.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeGrid2(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 권한관리 저장
	function saveAuthManage(pArrAuthManage) {
		$.ajax({
			url: "/AjaxAdmin/SaveAuthManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(pArrAuthManage),
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
	
	// 업무수행 그리드 생성 함수
    function makeGrid1(pListDatas) {
    	commonFunc.initSheet("mySheet1");
		
        var initdata = {};

        createIBSheet2(document.getElementById("sheet1"), "mySheet1", "790px", "232px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "상태", Type: "Status", Width: 50, SaveName: "status", Align: "Center" },
        	{ Header: "삭제", Type: "DelCheck", Width: 50, SaveName: "deleteYn", Align: "Center" },
            { Header: "메뉴ID", Type: "Text", Width: 0, SaveName: "menuId", Align: "Center", Hidden: true, Edit: false },
            { Header: "메뉴타입", Type: "Text", Width: 0, SaveName: "menuType", Align: "Center", Hidden: true, Edit: false },
            { Header: "권한타입코드", Type: "Text", Width: 0, SaveName: "authTypeCd", Align: "Center", Hidden: true, Edit: false },
            { Header: "권한타입", Type: "Text", Width: 180, SaveName: "authTypeCdNm", Align: "Center", Edit: false },
            { Header: "권한대상코드", Type: "Text", Width: 210, SaveName: "authType", Align: "Center", Edit: false },
            { Header: "권한대상", Type: "Text", Width: 300, SaveName: "authTypeNm", Align: "Center", Edit: false }
        ];
		
        IBS_InitSheet(mySheet1, initdata);
        mySheet1.SetEditableColorDiff(0);
        mySheet1.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet1.LoadSearchData(pListDatas);
    }
	
     // 업무관리 그리드 생성 함수
    function makeGrid2(pListDatas) {
    	commonFunc.initSheet("mySheet2");
		
        var initdata = {};

        createIBSheet2(document.getElementById("sheet2"), "mySheet2", "790px", "233px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "상태", Type: "Status", Width: 50, SaveName: "status", Align: "Center" },
        	{ Header: "삭제", Type: "DelCheck", Width: 50, SaveName: "deleteYn", Align: "Center" },
            { Header: "메뉴ID", Type: "Text", Width: 0, SaveName: "menuId", Align: "Center", Hidden: true, Edit: false },
            { Header: "메뉴타입", Type: "Text", Width: 0, SaveName: "menuType", Align: "Center", Hidden: true, Edit: false },
            { Header: "권한타입코드", Type: "Text", Width: 0, SaveName: "authTypeCd", Align: "Center", Hidden: true, Edit: false },
            { Header: "권한타입", Type: "Text", Width: 180, SaveName: "authTypeCdNm", Align: "Center", Edit: false },
            { Header: "권한대상코드", Type: "Text", Width: 210, SaveName: "authType", Align: "Center", Edit: false },
            { Header: "권한대상", Type: "Text", Width: 300, SaveName: "authTypeNm", Align: "Center", Edit: false }
        ];
		
        IBS_InitSheet(mySheet2, initdata);
        mySheet2.SetEditableColorDiff(0);
        mySheet2.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet2.LoadSearchData(pListDatas);
    }
     
    // 저장 전, 확인 함수
	function saveAuthManageConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
			var saveJson1 = mySheet1.GetSaveJson().data;
			var saveJson2 = mySheet2.GetSaveJson().data;
			var saveJsonLen1 = saveJson1.length;
			var saveJsonLen2 = saveJson2.length;
			var arrData = [];
			
			for (var i = 0; i < saveJsonLen1; i++) {
				var jsonData = {};
				jsonData.menuId = saveJson1[i].menuId;
				jsonData.menuType = saveJson1[i].menuType;
				jsonData.authType = saveJson1[i].authType;
				arrData.push(jsonData);
			}
			
			for (var i = 0; i < saveJsonLen2; i++) {
				var jsonData = {};
				jsonData.menuId = saveJson2[i].menuId;
				jsonData.menuType = saveJson2[i].menuType;
				jsonData.authType = saveJson2[i].authType;
				arrData.push(jsonData);
			}
			
			saveAuthManage(arrData);
        }
	}
	
	// 트리 클릭 이벤트
    function tree_click(pNodeCd, pNodeNm, pParentNodeCd, pNodeChild, pNodeData, pParam) {
        listMenuAuthRunChild(pNodeCd);
        listMenuAuthManageChild(pNodeCd);
        $("#hdn_upcd").val(pNodeData.cd);
        $("#hdn_lvl").val(Number(pNodeData.lvl) + 1);
        //document.getElementById("menuNm").innerText = pNodeNm;
        menuId = pNodeCd;
    }
 	
    // 저장 버튼 클릭 이벤트
    $(document).on("click", "#btn_save", function (e) {
		var saveStr1 = mySheet1.GetSaveString();
		var saveStr2 = mySheet2.GetSaveString();
    	
    	if (saveStr1 == "" && saveStr2 == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "변경 사항이 없습니다.", null, null, null);
    		return false;
    	}
    	
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장하시겠습니까?", null, saveAuthManageConfirm, null);    
    }); 	
	
	// 업무수행 개인 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add_run_user", function (e) {
    	libraryFunc.createModal(null, null, null, 600, 440, "개인 권한 추가", "/ModalAdmin/AuthManageAddUser.do?pMenuId=" + menuId + "&pMenuType=R");
    }); 	
	
    // 업무수행 직급 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add_run_degree", function (e) {
    	libraryFunc.createModal(null, null, null, 600, 440, "직급 권한 추가", "/ModalAdmin/AuthManageAddDegree.do?pMenuId=" + menuId + "&pMenuType=R");
    }); 	
   
    // 업무수행 부서 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add_run_dept", function (e) {
    	libraryFunc.createModal(null, null, null, 600, 440, "부서 권한 추가", "/ModalAdmin/AuthManageAddDept.do?pMenuId=" + menuId + "&pMenuType=R");
    }); 	
 	
    // 업무관리 개인 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add_manage_user", function (e) {
    	libraryFunc.createModal(null, null, null, 600, 440, "개인 권한 추가", "/ModalAdmin/AuthManageAddUser.do?pMenuId=" + menuId + "&pMenuType=M");
    }); 	
    
    // 업무관리 직급 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add_manage_degree", function (e) {
    	libraryFunc.createModal(null, null, null, 600, 440, "직급 권한 추가", "/ModalAdmin/AuthManageAddDegree.do?pMenuId=" + menuId + "&pMenuType=M");
    }); 	
   
    // 업무관리 부서 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add_manage_dept", function (e) {
    	libraryFunc.createModal(null, null, null, 600, 440, "부서 권한 추가", "/ModalAdmin/AuthManageAddDept.do?pMenuId=" + menuId + "&pMenuType=M");
    }); 	       
 	
</script>