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
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                	<th class="search_dtl_th">기준연월</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="datepicker_ym" readonly="readonly"  id="year_mon" value="${basicDate}" />
	                    </td>
	                	<th class="search_dtl_th">조회구분</th>
	                    <td class="search_dtl_td">
	                         <label class="lbl_every"><input type="checkbox" class="chk_every" id="error_yn" /><span class="spn_every">불일치건만 조회</span></label>
	                    </td>
	                </tr>	                
	            </tbody>
	        </table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_search">조회</a>
	    	<a class="btn_common1" id="btn_orachecklist">오라클 체크리스트</a>
	    	<!-- <a class="btn_common1" id="btn_tax_on_download">전자세금계산서 다운로드</a>
	    	<a class="btn_common1" id="btn_tax_off_download">전자계산서 다운로드</a>  -->
	    </div>
	    <!-- 그리드영역 -->
	    <div class="grid_box">
		    <div class="grid_title">세금계산서</div>
		    <div class="grid_btn">
		    	<a class="btn_common2" id="btn_tax_on_download">엑셀</a>
		    </div>
	    </div>
   	    <div id="sheet1"></div>
   	    <div class="grid_box">
   	    	<div class="grid_title">계산서</div>
   	    	<div class="grid_btn">
		    	<a class="btn_common2" id="btn_tax_off_download">엑셀</a>
		    </div>
   	    </div>
   	    <div id="sheet2"></div>
   	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a href="/Bot/ListMenu.do"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${outMenuVO.menuId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		commonFunc.createDatepicker(".datepicker_ym", "YearMonth");
			
		searchListEseroInvoiceSlipListManage();
		
	});
		
	// 세금계산서 전표 대사 목록 조회
	function listEseroInvoiceSlipListManageTaxOn(pYearMon, pErrorYn) {
		$.ajax({
			url: "/AjaxBot/ListEseroInvoiceSlipListManageTaxOn.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "yearMon": pYearMon, "errorYn": pErrorYn }),
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
	
	// 계산서 전표 대사 목록 조회
	function listEseroInvoiceSlipListManageTaxOff(pYearMon, pErrorYn) {
		$.ajax({
			url: "/AjaxBot/ListEseroInvoiceSlipListManageTaxOff.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "yearMon": pYearMon, "errorYn": pErrorYn }),
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
	
	// 그리드 생성 함수
    function makeGrid1(pListDatas) {
    	commonFunc.initSheet("mySheet1");
		
        var initdata = {};

        createIBSheet2(document.getElementById("sheet1"), "mySheet1", "1120px", "348px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "요청번호|요청번호", Type: "Text", Width: 0, SaveName: "requestNo", Align: "Center", Hidden: true },
        	{ Header: "기준연월|기준연월", Type: "Text", Width: 60, SaveName: "invoiceYearMon", Align: "Center" },
            { Header: "사업자번호|사업자번호", Type: "Text", Width: 90, SaveName: "invoiceBizNo", Align: "Center" },
            { Header: "업체코드|업체코드", Type: "Text", Width: 70, SaveName: "vendorCd", Align: "Center" },            
            { Header: "업체명|업체명", Type: "Text", Width: 170, SaveName: "vendorNm", Align: "Center" },
            { Header: "세금계산서|개수", Type: "Float", Width: 56, SaveName: "invoiceCnt", Align: "Right" },
            { Header: "세금계산서|공급가액", Type: "Float", Width: 100, SaveName: "invoiceSupplyAmt", Align: "Right" },            
            { Header: "세금계산서|부가세액", Type: "Float", Width: 100, SaveName: "invoiceVatAmt", Align: "Right" },
            { Header: "세금계산서|합계", Type: "Float", Width: 100, SaveName: "invoiceTotAmt", Align: "Right" },
            { Header: "전표|개수", Type: "Float", Width: 56, SaveName: "slipCnt", Align: "Right" },
            { Header: "전표|공급가액", Type: "Float", Width: 100, SaveName: "slipSupplyAmt", Align: "Right"},        
            { Header: "전표|부가세액", Type: "Float", Width: 100, SaveName: "slipVatAmt", Align: "Right" },
            { Header: "전표|합계", Type: "Float", Width: 100, SaveName: "slipTotAmt", Align: "Right" },
            { Header: "차이|개수", Type: "Float", Width: 56, SaveName: "gapCnt", Align: "Right" },
            { Header: "차이|공급가액", Type: "Float", Width: 100, SaveName: "gapSupplyAmt", Align: "Right"},        
            { Header: "차이|부가세액", Type: "Float", Width: 100, SaveName: "gapVatAmt", Align: "Right" },
            { Header: "차이|합계", Type: "Float", Width: 100, SaveName: "gapTotAmt", Align: "Right" }
        ];

        IBS_InitSheet(mySheet1, initdata);
        mySheet1.SetEditable(0);
        mySheet1.SetEditableColorDiff(0);
        mySheet1.SetDataLinkMouse("vendorNm", true);
        mySheet1.SetColFontUnderline("vendorNm", true);
        mySheet1.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet1.LoadSearchData(pListDatas);
    }  
	
	// 그리드 생성 함수
    function makeGrid2(pListDatas) {
    	commonFunc.initSheet("mySheet2");
		
        var initdata = {};

        createIBSheet2(document.getElementById("sheet2"), "mySheet2", "1120px", "348px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "요청번호|요청번호", Type: "Text", Width: 0, SaveName: "requestNo", Align: "Center", Hidden: true },
        	{ Header: "기준연월|기준연월", Type: "Text", Width: 60, SaveName: "invoiceYearMon", Align: "Center" },
            { Header: "사업자번호|사업자번호", Type: "Text", Width: 90, SaveName: "invoiceBizNo", Align: "Center" },
            { Header: "업체코드|업체코드", Type: "Text", Width: 70, SaveName: "vendorCd", Align: "Center" },            
            { Header: "업체명|업체명", Type: "Text", Width: 170, SaveName: "vendorNm", Align: "Center" },
            { Header: "계산서|개수", Type: "Float", Width: 56, SaveName: "invoiceCnt", Align: "Right" },
            { Header: "계산서|공급가액", Type: "Float", Width: 100, SaveName: "invoiceSupplyAmt", Align: "Right" },            
            { Header: "계산서|부가세액", Type: "Float", Width: 100, SaveName: "invoiceVatAmt", Align: "Right" },
            { Header: "계산서|합계", Type: "Float", Width: 100, SaveName: "invoiceTotAmt", Align: "Right" },
            { Header: "전표|개수", Type: "Float", Width: 56, SaveName: "slipCnt", Align: "Right" },
            { Header: "전표|공급가액", Type: "Float", Width: 100, SaveName: "slipSupplyAmt", Align: "Right"},        
            { Header: "전표|부가세액", Type: "Float", Width: 100, SaveName: "slipVatAmt", Align: "Right" },
            { Header: "전표|합계", Type: "Float", Width: 100, SaveName: "slipTotAmt", Align: "Right" },
            { Header: "차이|개수", Type: "Float", Width: 56, SaveName: "gapCnt", Align: "Right" },
            { Header: "차이|공급가액", Type: "Float", Width: 100, SaveName: "gapSupplyAmt", Align: "Right"},        
            { Header: "차이|부가세액", Type: "Float", Width: 100, SaveName: "gapVatAmt", Align: "Right" },
            { Header: "차이|합계", Type: "Float", Width: 100, SaveName: "gapTotAmt", Align: "Right" }
        ];
		
        IBS_InitSheet(mySheet2, initdata);
        mySheet2.SetEditable(0);
        mySheet2.SetEditableColorDiff(0);
        mySheet2.SetDataLinkMouse("vendorNm", true);
        mySheet2.SetColFontUnderline("vendorNm", true);
        mySheet2.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet2.LoadSearchData(pListDatas);
    } 
	
    // 세금계산서 그리드 클릭 함수
	function mySheet1_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
		
		if (mySheet1.ColSaveName(Col) == "vendorNm") {
			var vendorCd = mySheet1.GetCellValue(Row, "vendorCd");
			var requestNo = mySheet1.GetCellValue(Row, "requestNo");
			var yearMon = mySheet1.GetCellValue(Row, "invoiceYearMon");
			var invoiceTypeCd = "RA010001";
			
			libraryFunc.createModal(null, null, null, 1100, 560, "상세내역", "/ModalBot/EseroManageVendorSlipList.do" + "?pVendorCd=" + vendorCd + '&pRequestNo=' + requestNo + '&pYearMon=' + yearMon + '&pInvoiceTypeCd=' + invoiceTypeCd);
   		}
	}
    
	// 계산서 그리드 클릭 함수
	function mySheet2_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
		
		if (mySheet2.ColSaveName(Col) == "vendorNm") {
			var vendorCd = mySheet2.GetCellValue(Row, "vendorCd");
			var requestNo = mySheet2.GetCellValue(Row, "requestNo");
			var yearMon = mySheet2.GetCellValue(Row, "invoiceYearMon");
			var invoiceTypeCd = "RA010002";
			
			libraryFunc.createModal(null, null, null, 1100, 560, "상세내역", "/ModalBot/EseroManageVendorSlipList.do" + "?pVendorCd=" + vendorCd + '&pRequestNo=' + requestNo + '&pYearMon=' + yearMon + '&pInvoiceTypeCd=' + invoiceTypeCd);
   		}
	}
	
 	// 체크 박스 체크 여부 조회
	function getCheckYn(pChkBoxId) {
		var returnVal = "";
		if ($(pChkBoxId).is(":checked")) {
			returnVal = "Y";
		}
		return returnVal;
	}
	
	// 목록 조회 공통 함수
	function searchListEseroInvoiceSlipListManage() {
		var yearMon = commonFunc.getReplaceAll($("#year_mon").val(),"-","");
		var errorYn = getCheckYn("#error_yn");		
		
		listEseroInvoiceSlipListManageTaxOn(yearMon, errorYn);
		listEseroInvoiceSlipListManageTaxOff(yearMon, errorYn);
	}	
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {		
		var yearMon = commonFunc.getReplaceAll($("#year_mon").val(),"-","");		
		
		if (yearMon == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "기준연월이 선택되지 않았습니다.", null, null, null);
    		return false;
    	}		
		
		searchListEseroInvoiceSlipListManage();
	});
	
	// 오라클 체크리스트 버튼 클릭 이벤트
	$(document).on("click", "#btn_orachecklist", function (e) {		
		var yearMon = commonFunc.getReplaceAll($("#year_mon").val(),"-","");		
		
		if (yearMon == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "기준연월이 선택되지 않았습니다.", null, null, null);
    		return false;
    	}		
				
		libraryFunc.createModal(null, null, null, 1100, 560, "오라클 체크리스트", "/ModalBot/EseroManageOraCheckList.do?pMenuId=" + menuId + "&pYearMon=" + yearMon);
	});
	
	// 전자세금계산서 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_tax_on_download", function (e) {		
		var params = { Multipart: 0, FileName: "TaxInvoiceFile.xls",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		mySheet1.Down2Excel(params);
	});
	
	// 전자계산서 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_tax_off_download", function (e) {		
		var params = { Multipart: 0, FileName: "InvoiceFile.xls",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		mySheet2.Down2Excel(params);
	});
	
</script>