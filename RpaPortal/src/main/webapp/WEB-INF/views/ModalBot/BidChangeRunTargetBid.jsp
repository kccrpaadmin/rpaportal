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
	                <col style="width:60%;" />
	                <col />
				</colgroup>
				<tbody>
					<tr>						
						<th class="search_dtl_th">공사명</th>
						<td class="search_dtl_td">
							<input type="text" class="txt_box_l" style="width:160px;" id="const_nm"  />										
						</td>
						<th class="search_dtl_th">접수일자</th>
						<td class="search_dtl_td">
							<input type="text" class="datepicker_ymd" readonly="readonly"  id="start_receive_date" value="${startReceiveDate}" /> ~
							<input type="text" class="datepicker_ymd" readonly="readonly"  id="end_receive_date" value="${endReceiveDate}" />
						</td>
								
					</tr>				
				</tbody>				
			</table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_search">조회</a>
	    	<a class="btn_common1" id="btn_save">저장</a>
	    </div>
		<!-- 그리드영역 -->
		<div class="grid_box">
			<div class="grid_title">나라장터 수주정보 목록</div>
		</div>
		<div id="sheet1"></div>
		<div class="grid_box">
			<div class="grid_title">변경사항 수집대상 공사 목록 </div>
		</div>
		<div id="sheet2"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${menuId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {	
		commonFunc.createDatepicker(".datepicker_ymd", "YearMonthDay");	
		$("#start_receive_date").val();
		searchListBidChangeG2b();
		searchListBidChangeTargetBid();
	});
		
	// 수주정보 목록 조회
	function listBidChangeG2b(pConstNm, pStartReceiveDate, pEndReceiveDate) {
		$.ajax({
			url: "/AjaxBot/ListBidChangeG2b.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({"constNm":pConstNm, "startReceiveDate":pStartReceiveDate, "endReceiveDate":pEndReceiveDate }),
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
	
	// 입찰 예정 변경사항 수집대상 공사 목록 조회
	function listBidChangeTargetBid(pMenuId) {
		$.ajax({
			url: "/AjaxBot/ListBidChangeTargetBid.do",
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
	
	// 대상공사 저장
	function createBidChangeTargetBid(pArrBotBidChangeTargetBid) {
		$.ajax({
			url: "/AjaxBot/CreateBotBidChangeTargetBid.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(pArrBotBidChangeTargetBid),
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
	
	// 대상공사 삭제
	function deleteBidChangeTargetBid(pArrBotBidChangeTargetBid) {
		$.ajax({
			url: "/AjaxBot/DeleteBotBidChangeTargetBid.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(pArrBotBidChangeTargetBid),
			dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "삭제 되었습니다.", null, commonFunc.refreshPage, null);
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
	
	// 수주정보 그리드 생성 함수
    function makeGrid1(pListDatas) {
    	commonFunc.initSheet("mySheet1");

        var initdata = {};

        createIBSheet2(document.getElementById("sheet1"), "mySheet1", "1060px", "220px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "상태", Type: "Status", Width: 40, SaveName: "status", Align: "Center" },
        	{ Header: "추가", Type: "CheckBox", Width: 40, SaveName: "chk", Align: "Center" },
        	{ Header: "공사관리번호", Type: "Text", Width: 80, SaveName: "manageNo", Align: "Center", Edit: false },
        	{ Header: "접수일자", Type: "Text", Width: 80, SaveName: "receiveDate", Align: "Center", Edit: false },
        	{ Header: "공사명", Type: "Text", Width: 300, SaveName: "constNm", Edit: false },
        	{ Header: "발주(공고)기관", Type: "Text", Width: 150, SaveName: "orderAgency", Edit: false },
        	{ Header: "계약방법", Type: "Text", Width: 70, SaveName: "contractType", Align: "Center", Edit: false },
        	{ Header: "계약성질", Type: "Text", Width: 70, SaveName: "contractProperty", Align: "Center", Edit: false },
        	{ Header: "총공사규모액", Type: "Text", Width: 110, SaveName: "totalContractAmt", Align: "Right", Edit: false },
        	{ Header: "공종", Type: "Text", Width: 90, SaveName: "regJobType", Align: "Center", Edit: false },
        	{ Header: "도급액", Type: "Text", Width: 110, SaveName: "contractAmt", Align: "Right", Edit: false },
        	{ Header: "주공종과", Type: "Text", Width: 90, SaveName: "regJobTypeDept", Align: "Center", Edit: false }
        ];

        IBS_InitSheet(mySheet1, initdata);
        mySheet1.SetEditableColorDiff(0);
        mySheet1.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet1.LoadSearchData(pListDatas);        
    }
	
    // 수집대상공사 그리드 생성 함수
    function makeGrid2(pListDatas) {
    	commonFunc.initSheet("mySheet2");

        var initdata = {};

        createIBSheet2(document.getElementById("sheet2"), "mySheet2", "1060px", "200px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "상태", Type: "Status", Width: 40, SaveName: "status", Align: "Center" },
        	{ Header: "삭제", Type: "DelCheck", Width: 40, SaveName: "chk", Align: "Center" },
        	{ Header: "공사관리번호", Type: "Text", Width: 80, SaveName: "manageNo", Align: "Center", Edit: false },
        	{ Header: "접수일자", Type: "Text", Width: 80, SaveName: "receiveDate", Align: "Center", Edit: false },
        	{ Header: "입찰공고번호", Type: "Text", Width: 130, SaveName: "bidNo", Align: "Center", Edit: false },
        	{ Header: "공사명", Type: "Text", Width: 360, SaveName: "constNm", Edit: false },
        	{ Header: "발주(공고)기관", Type: "Text", Width: 200, SaveName: "orderAgency", Edit: false },
        	{ Header: "총공사규모액", Type: "Text", Width: 110, SaveName: "totalContractAmt", Align: "Right", Edit: false }
        ];

        IBS_InitSheet(mySheet2, initdata);
        mySheet2.SetEditableColorDiff(0);
        mySheet2.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet2.LoadSearchData(pListDatas);        
    }
		
    // 저장 전, 확인 함수
	function saveBidChangeTargetBidConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
			var saveJson1 = mySheet1.GetSaveJson().data;
			var saveJson2 = mySheet2.GetSaveJson().data;
			var saveJsonLen1 = saveJson1.length;
			var saveJsonLen2 = saveJson2.length;
			var arrData1 = [];
			var arrData2 = [];
			
			if (saveJsonLen1 > 0){
				for (var i = 0; i < saveJsonLen1; i++) {
					var jsonData = {};
					jsonData.manageNo = saveJson1[i].manageNo;
					jsonData.empNo = commonFunc.certInfo.empNo;
					arrData1.push(jsonData);
				}
				
				createBidChangeTargetBid(arrData1);
			}
			
			if (saveJsonLen2 > 0){
				for (var i = 0; i < saveJsonLen2; i++) {
					var jsonData = {};
					jsonData.manageNo = saveJson2[i].manageNo;
					arrData2.push(jsonData);
				}

				deleteBidChangeTargetBid(arrData2);
			}
        }
	}
    
	// 수주정보 목록 조회 공통 함수
	function searchListBidChangeG2b() {
		var constNm = $("#const_nm").val();
		var startReceiveDate = $("#start_receive_date").val();
		var endReceiveDate = $("#end_receive_date").val();
		
		listBidChangeG2b(constNm, startReceiveDate, endReceiveDate);
	}	
	
	// 변경사항 수집대상 공사 목록 조회 공통 함수
	function searchListBidChangeTargetBid() {
		listBidChangeTargetBid(menuId);
	}	
		
    // 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {
		searchListBidChangeG2b();
	});
    
	// 저장 버튼 클릭 이벤트
    $(document).on("click", "#btn_save", function (e) {
    	var saveStr1 = mySheet1.GetSaveString();
    	var saveStr2 = mySheet2.GetSaveString();
    	
    	if (saveStr1 == "" && saveStr2 == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "변경 사항이 없습니다.", null, null, null);
    		return false;
    	}
    	
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장하시겠습니까?", null, saveBidChangeTargetBidConfirm, null);    
    });
	
    // 엔터 이벤트
	$(document).on("keydown", "#const_nm", function (e) {
		if (e.keyCode == 13) {
			$("#btn_search").trigger("click");
		}
	});
</script>