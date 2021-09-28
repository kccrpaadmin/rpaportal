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
				<div class="location_title">${outMenuVO.menuNm}</div>	
			</div>
			<div class="location_right">
				<span class="location_home">홈</span>
				<span class="location_arrow">웹크롤링</span>
				<span class="location_arrow">업무관리</span>
			</div>
		</div>
		<!-- 제목 -->
		<div class="title">상세정보</div>
		<!-- 검색영역 -->
	    <div class="search_dtl_box">
	        <table class="search_dtl_tbl">
	            <caption>검색영역</caption>
	            <colgroup>
	                <col style="width:8%;" />
	                <col style="width:15%;" />
	                <col style="width:8%;" />
	                <col style="width:25%;" />
	                <col style="width:8%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                	<th class="search_dtl_th">숨김여부</th>
	                    <td class="search_dtl_td">
	                        <label class="lbl_every"><input type="checkbox" class="chk_every" id="show_yn" /><span class="spn_every">숨김항목포함</span></label>
	                    </td>
	                	<th class="search_dtl_th">기준일자</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="datepicker_ymd" readonly="readonly"  id="start_date" value="${startDate}" />
	                        <span>~</span>
	                        <input type="text" class="datepicker_ymd" readonly="readonly" id="end_date" value="${endDate}" />
	                    </td>
	                    <th class="search_dtl_th">공사명</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="txt_box_l" style="width:200px;" id="const_nm" />
	                    </td>
	                </tr>
	            </tbody>
	        </table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_save">저장</a>
	    	<a class="btn_common1" id="btn_search">조회</a>
	    </div>
	    <!-- 그리드영역 -->
	    <div class="grid_box">
		    <div class="grid_title">수주정보</div>
		    <div class="grid_btn">
		    	<a class="btn_common2" id="btn_g2b_download">엑셀</a>
		    </div>
	    </div>
   	    <div id="sheet"></div>
   	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a href="/Crawl/ListMenu.do"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${outMenuVO.menuId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		commonFunc.createDatepicker(".datepicker_ymd", "YearMonthDay");
		searchListCrawlG2bManage();
	});
	
	// 나라장터 수집 데이터 관리 목록 조회
	function listG2bManage(pShowYn, pStartDate, pEndDate, pConstNm) {
		$.ajax({
			url: "/AjaxCrawl/ListG2bManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "showYn": pShowYn, "startDate": pStartDate, "endDate": pEndDate, "constNm": pConstNm }),
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
	
	// 나라장터 수집 데이터 관리 목록 저장
	function saveG2bManage(pArrCrawlG2b) {
		$.ajax({
			url: "/AjaxCrawl/SaveG2bManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(pArrCrawlG2b),
			dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "저장 되었습니다.", null, searchListCrawlG2bManage, null);
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1120px", "510px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "상태", Type: "Status", Width: 50, SaveName: "status", Align: "Center" },
       	 	{ Header: "숨김", Type: "CheckBox", Width: 50, SaveName: "showYn", Align: "Center" },
            { Header: "공사관리번호", Type: "Text", Width: 100, SaveName: "manageNo", Align: "Center", Edit: false },
            { Header: "접수일자", Type: "Text", Width: 80, SaveName: "receiveDate", Align: "Center", Edit: false },
            { Header: "공사명", Type: "Text", Width: 250, SaveName: "constNm", Edit: false },
            { Header: "발주(공고)기관", Type: "Text", Width: 200, SaveName: "orderAgency", Align: "Center", Edit: false },
            { Header: "계약방법", Type: "Text", Width: 110, SaveName: "contractType", Align: "Center", Edit: false },
            { Header: "계약성질", Type: "Text", Width: 60, SaveName: "contractProperty", Align: "Center", Edit: false },
            { Header: "총공사규모액", Type: "Float", Width: 120, SaveName: "totalContractAmt", Edit: false },
            { Header: "공종", Type: "Text", Width: 150, SaveName: "regJobType", Align: "Center", Edit: false },
            { Header: "도급액", Type: "Float", Width: 120, SaveName: "contractAmt", Edit: false },
            { Header: "주공종과", Type: "Text", Width: 150, SaveName: "regJobTypeDept", Align: "Center", Edit: false },
            { Header: "관급액", Type: "Float", Width: 120, SaveName: "governmentAmt", Edit: false },
            { Header: "공사기간", Type: "Text", Width: 150, SaveName: "constDateTerm", Align: "Center", Edit: false },
            { Header: "기타액", Type: "Float", Width: 120, SaveName: "etcAmt", Edit: false },
            { Header: "공사현장", Type: "Text", Width: 150, SaveName: "constSite", Align: "Center", Edit: false },
            { Header: "공사예정금액", Type: "Float", Width: 120, SaveName: "targetContractAmt", Edit: false },
            { Header: "기술검토 의뢰일", Type: "Text", Width: 120, SaveName: "skillOrderDate", Align: "Center", Edit: false },
            { Header: "작성일시", Type: "Text", Width: 120, SaveName: "regDate", Align: "Center", Edit: false }
        ];
		
        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }  
	
	// 체크 박스 체크 여부 조회
	function getCheckYn(pChkBoxId) {
		var returnVal = "Y";
		if ($(pChkBoxId).is(":checked")) {
			returnVal = "";
		}
		return returnVal;
	}
	
	// 목록 조회 공통 함수
	function searchListCrawlG2bManage() {
		listG2bManage(getCheckYn("#show_yn"), commonFunc.getReplaceAll($("#start_date").val(), "-", ""), commonFunc.getReplaceAll($("#end_date").val(), "-", ""), $("#const_nm").val());
	}	
	
	// 저장 전, 확인 함수
	function saveCrawlG2bManageConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
			var empNo = commonFunc.certInfo.empNo;
			var saveJson = mySheet.GetSaveJson().data;
			var saveJsonLen = saveJson.length;
			var arrData = [];
			
			for (var i = 0; i < saveJsonLen; i++) {
				var jsonData = {};
				jsonData.manageNo = saveJson[i].manageNo;
				jsonData.showYn = saveJson[i].showYn;
				jsonData.empNo = empNo;
				arrData.push(jsonData);
			}
			
			saveG2bManage(arrData);
        }
	}
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {
		searchListCrawlG2bManage();
	});
 	
	// 검색 박스 엔터 이벤트
	$(document).on("keydown", "#const_nm", function (e) {
		if (e.keyCode == 13) {
			$("#btn_search").trigger("click");
		}
	});
	
	// 저장 버튼 클릭 이벤트
    $(document).on("click", "#btn_save", function (e) {
		var saveStr = mySheet.GetSaveString();
    	
    	if (saveStr == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "숨김 대상을 선택하지 않았습니다.", null, null, null);
    		return false;
    	}
    	
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장 하시겠습니까?", null, saveCrawlG2bManageConfirm, null);    
    });
	
 	// 엑셀 버튼 클릭 이벤트
	$(document).on("click", "#btn_g2b_download", function (e) {		
		var params = { Multipart: 0, FileName: "G2b.xls",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		mySheet.Down2Excel(params);
	});
 	
</script>