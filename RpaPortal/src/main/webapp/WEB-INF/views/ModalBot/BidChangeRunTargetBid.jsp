<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<div class="search_dtl_box">
			<table class="search_dtl_tbl">
				<colgroup>
					<col style="width:10%;" />
	                <col style="width:20%;" />
					<col style="width:10%;" />
	                <col style="width:20%;" />
					<col style="width:10%;" />
	                <col style="width:20%;" />
	                <col />
				</colgroup>
				<tbody>
					<tr>
						<th class="search_dtl_th">부문</th>
	                    <td class="search_dtl_td">
	                         ${orgTypeComboBox}
	                    </td>
						<th class="search_dtl_th">이름</th>
						<td class="search_dtl_td">
							<input type="text" class="txt_box_l" style="width:160px;" id="const_nm"  />										
						</td>
						<th class="search_dtl_th">생년월일</th>
						<td class="search_dtl_td">
							<input type="text" class="txt_box_l" style="width:160px;" id="res_no"  />												
						</td>
								
					</tr>				
				</tbody>				
			</table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_search">조회</a>
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
		searchListBidChangeG2b();
	});
		
	// 대상공사 목록 조회
	function listBidChangeG2b(pConstNm) {
		$.ajax({
			url: "/AjaxBot/ListBidChangeG2b.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({"constNm":pConstNm }),
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
	
	// 대상 인원 목록 저장
	function saveEngineerTargetUser(pArrBotEngineerTargetUser) {
		$.ajax({
			url: "/AjaxBot/SaveEngineerTargetUser.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(pArrBotEngineerTargetUser),
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1060px", "200px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "공사관리번호", Type: "Text", Width: 0, SaveName: "manageNo", Align: "Center", Edit: false, Hidden: true },
        	{ Header: "접수일자", Type: "Text", Width: 100, SaveName: "receiveDate", Align: "Center", Edit: false },
        	{ Header: "공사명", Type: "Text", Width: 300, SaveName: "constNm", Align: "Center", Edit: false },
        	{ Header: "발주(공고)기관", Type: "Text", Width: 100, SaveName: "orderAgency", Align: "Center", Edit: false },
        	{ Header: "계약방법", Type: "Text", Width: 100, SaveName: "contractType", Align: "Center", Edit: false },
        	{ Header: "계약성질", Type: "Text", Width: 100, SaveName: "contractProperty", Edit: false },
        	{ Header: "총공사규모액", Type: "Text", Width: 100, SaveName: "totalContractAmt", Edit: false },
        	{ Header: "공종", Type: "Text", Width: 100, SaveName: "regJobType", Edit: false },
        	{ Header: "도급액", Type: "Text", Width: 100, SaveName: "contractAmt", Edit: false },
        	{ Header: "주공종과", Type: "Text", Width: 100, SaveName: "regJobTypeDept", Edit: false }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);        
    }
	
    // 불일치 행에 폰트 색상 변경 함수
	function mySheet_OnRowSearchEnd(row) {
		if(mySheet.GetCellValue(row, "deptNm") == "사직"){
			mySheet.SetRowFontColor(row , "#FF0000");			    
		}  		
	}
	
    // 저장 전, 확인 함수
	function saveEngineerTargetUserConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
			var saveJson = mySheet.GetSaveJson().data;
			var saveJsonLen = saveJson.length;
			var arrData = [];
			
			for (var i = 0; i < saveJsonLen; i++) {
				var jsonData = {};
				jsonData.userId = saveJson[i].userId;
				arrData.push(jsonData);
			}
			
			saveEngineerTargetUser(arrData);
        }
	}
    
	// 목록 조회 공통 함수
	function searchListBidChangeG2b() {
		var constNm = $("#const_nm").val();
		
		listBidChangeG2b(constNm);
	}	
		
    // 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {
		searchListBidChangeG2b();
	});
    
	// 추가 버튼 클릭 이벤트
    $(document).on("click", "#btn_add", function (e) {
    	libraryFunc.createModal(null, null, null, 600, 440, "직원 검색", "/ModalBot/EngineerRunTargetUserSearchUser.do" );
    });
    
	// 저장 버튼 클릭 이벤트
    $(document).on("click", "#btn_save", function (e) {
    	var saveStr = mySheet.GetSaveString();
    	
    	if (saveStr == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "변경 사항이 없습니다.", null, null, null);
    		return false;
    	}
    	
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장하시겠습니까?", null, saveEngineerTargetUserConfirm, null);    
    });
	
    // 엔터 이벤트
	$(document).on("keydown", "#user_nm", function (e) {
		if (e.keyCode == 13) {
			$("#btn_search").trigger("click");
		}
	});
    
	$(document).on("keydown", "#res_no", function (e) {
		if (e.keyCode == 13) {
			$("#btn_search").trigger("click");
		}
	});
</script>