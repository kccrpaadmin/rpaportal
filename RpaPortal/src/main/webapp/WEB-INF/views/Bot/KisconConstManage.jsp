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
				<span class="location_arrow">BOT</span>
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
	                <col style="width:20%;" />
	                <col style="width:8%;" />
	                <col style="width:20%;" />
	                <col style="width:8%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                	<th class="search_dtl_th">현장명</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="txt_box_l" style="width:160px;" id="site_nm" />
	                    </td>
	                	<th class="search_dtl_th">계약명</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="txt_box_l" style="width:160px;" id="contract_nm" />
	                    </td>
	                    <td class="search_dtl_td"></td>
	                    <td class="search_dtl_td"></td>
	                </tr>	                
	            </tbody>
	        </table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_search">조회</a>
	    </div>
	    <!-- 그리드영역 -->
   	    <div id="sheet"></div>
   	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a href="/Bot/ListMenu.do?pCategoryCd=${outMenuVO.categoryCd}"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${outMenuVO.menuId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		searchListKisconConstManage("","");
	});
	
	// 30일 이내 하도급 변경계약 목록 조회
	function ListKisconConstManage(pSiteNm, pContractNm) {
		$.ajax({
			url: "/AjaxBot/ListKisconConstManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({"siteNm" : pSiteNm, "contractNm" : pContractNm}),
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1120px", "510px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	 { Header: "통보마감일자", Type: "Text", Width: 80, SaveName: "notificationCloseDate", Align: "Center" },    
             { Header: "현장명", Type: "Text", Width: 160, SaveName: "siteNm", Align: "Center" },        
             { Header: "계약번호", Type: "Text", Width: 105, SaveName: "contractNo", Align: "Center" },
             { Header: "계약명", Type: "Text", Width: 157, SaveName: "contractNm", Align: "Center"},            
             { Header: "업체명", Type: "Text", Width: 135, SaveName: "vendorNm", Align: "Center"},
             { Header: "계약일자", Type: "Text", Width: 80, SaveName: "contractDate", Align: "Center" },
             { Header: "계약시작일자", Type: "Text", Width: 80, SaveName: "contractStartDate", Align: "Center" },
             { Header: "계약종료일자", Type: "Text", Width: 80, SaveName: "contractFinishDate", Align: "Center" },
             { Header: "계약금액", Type: "Float", Width: 95, SaveName: "contractAmt", Align: "Right"},
             { Header: "대금지급보증\nERP입력여부", Type: "Text", Width: 80, SaveName: "pricePaymentYn", Align: "Center" },
             { Header: "BOT\n수행여부", Type: "Text", Width: 50, SaveName: "successType", Align: "Center" }
        ];
		
        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }  
	
    // 목록 조회 공통 함수
	function searchListKisconConstManage(pSiteNm, pContractNm) {
		ListKisconConstManage(pSiteNm, pContractNm);
	}
	
    // 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {		
		var siteNm = $("#site_nm").val();
		var contractNm = $("#contract_nm").val();
		
		searchListKisconConstManage(siteNm, contractNm);
	});
		
</script>