<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
	     <input type="hidden" class="param" readonly="readonly"  id="requestNo" value="${requestNo}" />
	     <input type="hidden" class="param" readonly="readonly"  id="division" value="${division}" />
	     <input type="hidden" class="param" readonly="readonly"  id="division1" value="${division1}" />
	     <input type="hidden" class="param" readonly="readonly"  id="division2" value="${division2}" />
	     <input type="hidden" class="param" readonly="readonly"  id="invoiceType" value="${invoiceType}" />
	     <input type="hidden" class="param" readonly="readonly"  id="vatAmtYn" value="${vatAmtYn}" />
	    <!-- 그리드영역 -->
	    <div id="homtax" style="display:none;">
		    <div class="grid_box" >
			    <div class="grid_title">홈택스 (세금)계산서 데이터</div>
			    <div class="grid_btn">
			    	<a class="btn_common2" id="btn_homtax_download">엑셀</a>
			    </div>
		    </div>
		    <div id="sheet1"></div>
	    </div>
	    <div id="oracle" style="display:none;">
	   	    <div class="grid_box" >
	   	    	<div class="grid_title">오라클 데이터</div>
	   	    	<div class="grid_btn">
			    	<a class="btn_common2" id="btn_oracle_download">엑셀</a>
			    </div>
	   	    </div>
	   	    <div id="sheet2"></div>
   	    </div>
   	    <div id="erp" style="display:none;">
	   	    <div class="grid_box">
			    <div class="grid_title">전표 데이터</div>
			    <div class="grid_btn">
			    	<a class="btn_common2" id="btn_erp_download">엑셀</a>
			    </div>
		    </div>
	   	    <div id="sheet3"></div>
	    </div>
	    <div id="compare" style="display:none;">
	   	    <div class="grid_box">
			    <div class="grid_title">차이 데이터</div>
			    <div class="grid_btn">
			    	<a class="btn_common2" id="btn_compare_download">엑셀</a>
			    </div>
		    </div>
	   	    <div id="sheet4"></div>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${outMenuVO.menuId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		//commonFunc.createDatepicker(".datepicker_ym", "YearMonth");
		initList();
	});
		
	// 세금계산서 전표 대사 목록 조회
	function SalesEseroDetail(pRequestNo, pDivision, pDivision1, pDivision2, pInvoiceType, pVatAmtYn) {
		$.ajax({
			url: "/AjaxBot/SalesEseroDetail.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : 
				JSON.stringify({ 
					"requestNo": pRequestNo, 
					"division": pDivision, 
					"division1": pDivision1, 
					"division2": pDivision2, 
					"invoiceType": pInvoiceType, 
					"vatAmtYn": pVatAmtYn 
					}),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				console.log(listDatas)
				if(pDivision == "1") { //내부
					if(pDivision1 == "A") { //오라클
						$("#oracle").css({"display":"block"});
						makeGrid2(listDatas);
					}else if(pDivision1 == "B") {//전표
						$("#erp").css({"display":"block"});
						makeGrid3(listDatas);
					}else if(pDivision1 == "C") {//오라클 매출(타사)
						$("#oracle").css({"display":"block"});
						makeGrid2(listDatas); //영세율
					}else if(pDivision1 == "A-B-C") {//차이
						$("#compare").css({"display":"block"});
						makeGrid4(listDatas);
					}
				} else if(pDivision == "2") { //홈택스
					if(pDivision1 == "C") {//오라클 매출(타사)
						$("#oracle").css({"display":"block"});
						makeGrid2(listDatas); //영세율
					}else if(pDivision1 == "A-B-C") {//차이
						$("#compare").css({"display":"block"});
						makeGrid4(listDatas);
					} else {
						$("#homtax").css({"display":"block"});
						makeGrid1(listDatas);
					}
				} else if(pDivision == "3") { //차이
					$("#compare").css({"display":"block"});
					makeGrid4(listDatas);
				}
				
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
        createIBSheet2(document.getElementById("sheet1"), "mySheet1", "1040px", "485px");
        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },
        	{ Header: "업체코드", Type: "Text", Width: 0, SaveName: "vendorCd", Hidden: true },
            { Header: "구분", Type: "Text", Width: 100, SaveName: "invoiceType", Align: "Center" },
            { Header: "기준년월", Type: "Text", Width: 60, SaveName: "yearMon", Align: "Center" },
            { Header: "승인번호", Type: "Text", Width: 180, SaveName: "issueNo", Align: "Center" },
            { Header: "작성일자", Type: "Text", Width: 80, SaveName: "makeDate", Align: "Center" },
            /* { Header: "발급일자", Type: "Float", Width: 80, SaveName: "drawDate", Align: "Center" }, */
            { Header: "전송일자", Type: "Text", Width: 80, SaveName: "sendDate", Align: "Center"  },
            { Header: "사업자번호", Type: "Text", Width: 90, SaveName: "bizNo", Align: "Center" },
            { Header: "상호", Type: "Text", Width: 200, SaveName: "vendorNm", Align: "Center" },
            { Header: "대표자명", Type: "Text", Width: 100, SaveName: "repNm", Align: "Center" },
            { Header: "주소", Type: "Text", Width: 200, SaveName: "addr", Align: "Center" }, 
            { Header: "합계금액", Type: "Float", Width: 100, SaveName: "totAmt" },
            { Header: "공급가액", Type: "Float", Width: 100, SaveName: "supplyAmt" },
            { Header: "세액", Type: "Float", Width: 80, SaveName: "vatAmt" },
            { Header: "분류", Type: "Text", Width: 100, SaveName: "invoiceType", Align: "Center" },
            { Header: "종류", Type: "Text", Width: 80, SaveName: "invoiceKind", Align: "Center" },
            { Header: "발급유형", Type: "Text", Width: 80, SaveName: "invoiceSource", Align: "Center" },
            { Header: "비고", Type: "Text", Width: 60, SaveName: "etc", Align: "Center" },
            { Header: "영수/청구", Type: "Text", Width: 60, SaveName: "reqResType", Align: "Center" },
            { Header: "공급하는자이메일", Type: "Text", Width: 200, SaveName: "salesEmail", Align: "Center" },
            { Header: "공급받는자이메일1", Type: "Text", Width: 200, SaveName: "buyEmail1", Align: "Center" },
            { Header: "공급받는자이메일2", Type: "Text", Width: 200, SaveName: "buyEmail2", Align: "Center" },
            { Header: "품목일자", Type: "Text", Width: 80, SaveName: "itemDate", Align: "Center" },
            { Header: "품목명", Type: "Text", Width: 200, SaveName: "itemNm", Align: "Center" },
            { Header: "품목규격", Type: "Text", Width: 60, SaveName: "itemUnit", Align: "Center" },
            { Header: "품목수량", Type: "Float", Width: 60, SaveName: "itemQty" },
            { Header: "품목단가", Type: "Float", Width: 100, SaveName: "itemCost" },
            { Header: "품목공급가액", Type: "Float", Width: 100, SaveName: "itemSupplyAmt" },
            { Header: "품목세액", Type: "Float", Width: 100, SaveName: "itemVatAmt"},
            { Header: "품목비고", Type: "Text", Width: 60, SaveName: "itemEtc"} 
        ];

        IBS_InitSheet(mySheet1, initdata);
        mySheet1.SetEditable(0);
        mySheet1.SetEditableColorDiff(0);
        mySheet1.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet1.LoadSearchData(pListDatas);
        
        mySheet1.SetDataLinkMouse("vendorNm", true);
	    mySheet1.SetColFontUnderline("vendorNm", true);
    }  
	
	// 그리드 생성 함수
    function makeGrid2(pListDatas) {
    	commonFunc.initSheet("mySheet2");
        var initdata = {};
        createIBSheet2(document.getElementById("sheet2"), "mySheet2", "1040px", "485px");
        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	 { Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },
        	 { Header: "업체코드", Type: "Text", Width: 0, SaveName: "vendorCd", Hidden: true },
             { Header: "기준년월", Type: "Text", Width: 60, SaveName: "yearMon", Align: "Center" },
             { Header: "전표번호", Type: "Text", Width: 180, SaveName: "slipNo", Align: "Center" },
             { Header: "발의부서", Type: "Text", Width: 180, SaveName: "deptNm", Align: "Center" },
             { Header: "회계일자", Type: "Text", Width: 80, SaveName: "paymentDate", Align: "Center" },
             { Header: "사업자번호", Type: "Text", Width: 90, SaveName: "bizNo", Align: "Center" },
             { Header: "업체명", Type: "Text", Width: 200, SaveName: "vendorNm", Align: "Center" },
             { Header: "증빙일자", Type: "Text", Width: 80, SaveName: "drawDate", Align: "Center" },
             { Header: "공급가액", Type: "Float", Width: 100, SaveName: "supplyAmt" },
             { Header: "부가세액", Type: "Float", Width: 80, SaveName: "vatAmt" },
             { Header: "적요", Type: "Text", Width: 60, SaveName: "summary", Align: "Center" },
             { Header: "사업장구분", Type: "Text", Width: 100, SaveName: "corpType", Align: "Center" },
             { Header: "병합전표번호", Type: "Text", Width: 180, SaveName: "mergeSlipNo", Align: "Center" },
             { Header: "세금계산서구분", Type: "Text", Width: 100, SaveName: "invoiceType", Align: "Center" },
             { Header: "매입매출구분", Type: "Text", Width: 80, SaveName: "buySalesType", Align: "Center" },
             { Header: "확정일자", Type: "Text", Width: 80, SaveName: "confirmDate", Align: "Center" },
             { Header: "수정대상원전표", Type: "Text", Width: 180, SaveName: "oriSlipNo", Align: "Center" },
             { Header: "전자/일반구분", Type: "Text", Width: 80, SaveName: "onOffType", Align: "Center" },            
             { Header: "국세청승인번호", Type: "Text", Width: 180, SaveName: "issueNo", Align: "Center" },            
             { Header: "전송일자", Type: "Text", Width: 80, SaveName: "sendDate", Align: "Center"  }
        ];
		
        IBS_InitSheet(mySheet2, initdata);
        mySheet2.SetEditable(0);
        mySheet2.SetEditableColorDiff(0);
        mySheet2.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet2.LoadSearchData(pListDatas);
        mySheet2.SetDataLinkMouse("vendorNm", true);
        mySheet2.SetColFontUnderline("vendorNm", true);
    } 
	
 // 그리드 생성 함수
    function makeGrid3(pListDatas) {
    	commonFunc.initSheet("mySheet3");
        var initdata = {};
        createIBSheet2(document.getElementById("sheet3"), "mySheet3", "1040px", "485px");
        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },
        	{ Header: "업체코드", Type: "Text", Width: 0, SaveName: "vendorCd", Hidden: true },
        	{ Header: "기준년월", Type: "Text", Width: 60, SaveName: "yearMon", Align: "Center" },
        	{ Header: "승인번호", Type: "Text", Width: 180, SaveName: "issueNo", Align: "Center" },
        	{ Header: "전표번호", Type: "Text", Width: 180, SaveName: "slipNo", Align: "Center" },
        	//{ Header: "진행상태", Type: "Text", Width: 100, SaveName: "slipStatus", Align: "Center" },
        	//{ Header: "국세청신고상태", Type: "Text", Width: 100, SaveName: "taxInvoiceStatus", Align: "Center" },
        	{ Header: "모듈유형", Type: "Text", Width: 100, SaveName: "moduleCd", Align: "Center" },
        	{ Header: "세금계산서종류", Type: "Text", Width: 100, SaveName: "invoiceType", Align: "Center" },
        	{ Header: "증빙일자", Type: "Text", Width: 100, SaveName: "drawDate", Align: "Center" },
        	{ Header: "사업자등록번호", Type: "Text", Width: 100, SaveName: "bizNo", Align: "Center" },
        	{ Header: "상호", Type: "Text", Width: 160, SaveName: "vendorNm", Align: "Center" },
        	{ Header: "공급가액", Type: "Float", Width: 100, SaveName: "supplyAmt" },
        	{ Header: "세액", Type: "Float", Width: 100, SaveName: "vatAmt" }
        ];
		
        IBS_InitSheet(mySheet3, initdata);
        mySheet3.SetEditable(0);
        mySheet3.SetEditableColorDiff(0);
        mySheet3.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet3.LoadSearchData(pListDatas);
        mySheet3.SetDataLinkMouse("vendorNm", true);
        mySheet3.SetColFontUnderline("vendorNm", true);
    } 
 
 // 그리드 생성 함수
    function makeGrid4(pListDatas) {
    	commonFunc.initSheet("mySheet4");
        var initdata = {};
        createIBSheet2(document.getElementById("sheet4"), "mySheet4", "1040px", "485px");
        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },
        	{ Header: "업체코드", Type: "Text", Width: 0, SaveName: "vendorCd", Hidden: true },
        	{ Header: "오차구분", Type: "Text", Width: 100, SaveName: "division", Align: "Center" },
        	{ Header: "발행구분", Type: "Text", Width: 100, SaveName: "invoiceType", Align: "Center" },
            { Header: "기준년월", Type: "Text", Width: 60, SaveName: "yearMon", Align: "Center" },
            { Header: "승인번호", Type: "Text", Width: 180, SaveName: "issueNo", Align: "Center" },
            //{ Header: "작성일자", Type: "Text", Width: 80, SaveName: "makeDate", Align: "Center" },
            { Header: "증빙일자", Type: "Text", Width: 80, SaveName: "drawDate", Align: "Center" },
            //{ Header: "전송일자", Type: "Text", Width: 80, SaveName: "sendDate", Align: "Center"  },
            { Header: "사업자번호", Type: "Text", Width: 90, SaveName: "bizNo", Align: "Center" },
            { Header: "상호", Type: "Text", Width: 200, SaveName: "vendorNm", Align: "Center" },
            { Header: "대표자명", Type: "Text", Width: 100, SaveName: "repNm", Align: "Center" },
            { Header: "주소", Type: "Text", Width: 200, SaveName: "addr", Align: "Center" }, 
            { Header: "합계금액", Type: "Float", Width: 100, SaveName: "totAmt" },
            { Header: "공급가액", Type: "Float", Width: 100, SaveName: "supplyAmt" },
            { Header: "세액", Type: "Float", Width: 80, SaveName: "vatAmt" },
            //{ Header: "분류", Type: "Text", Width: 100, SaveName: "invoiceType", Align: "Center" },
            //{ Header: "종류", Type: "Text", Width: 80, SaveName: "invoiceKind", Align: "Center" },
            //{ Header: "발급유형", Type: "Text", Width: 80, SaveName: "invoiceSource", Align: "Center" },
            //{ Header: "비고", Type: "Text", Width: 60, SaveName: "etc", Align: "Center" },
            //{ Header: "영수/청구", Type: "Text", Width: 60, SaveName: "reqResType", Align: "Center" },
            //{ Header: "공급하는자이메일", Type: "Text", Width: 200, SaveName: "salesEmail", Align: "Center" },
            //{ Header: "공급받는자이메일1", Type: "Text", Width: 200, SaveName: "buyEmail1", Align: "Center" },
            //{ Header: "공급받는자이메일2", Type: "Text", Width: 200, SaveName: "buyEmail2", Align: "Center" },
            //{ Header: "품목일자", Type: "Text", Width: 80, SaveName: "itemDate", Align: "Center" },
            //{ Header: "품목명", Type: "Text", Width: 200, SaveName: "itemNm", Align: "Center" },
            //{ Header: "품목규격", Type: "Text", Width: 60, SaveName: "itemUnit", Align: "Center" },
            //{ Header: "품목수량", Type: "Float", Width: 60, SaveName: "itemQty" },
            //{ Header: "품목단가", Type: "Float", Width: 100, SaveName: "itemCost" },
            //{ Header: "품목공급가액", Type: "Float", Width: 100, SaveName: "itemSupplyAmt" },
            //{ Header: "품목세액", Type: "Float", Width: 100, SaveName: "itemVatAmt"},
            //{ Header: "품목비고", Type: "Text", Width: 60, SaveName: "itemEtc"} 
        ];

        IBS_InitSheet(mySheet4, initdata);
        mySheet4.SetEditable(0);
        mySheet4.SetEditableColorDiff(0);
        mySheet4.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet4.LoadSearchData(pListDatas);
        
        mySheet4.SetDataLinkMouse("vendorNm", true);
	    mySheet4.SetColFontUnderline("vendorNm", true);
    }  
	
    // 홈택스 그리드 클릭 함수
	function mySheet1_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
		
		if (mySheet1.ColSaveName(Col) == "vendorNm") {
			var vendorCd = mySheet1.GetCellValue(Row, "vendorCd");
			var requestNo = mySheet1.GetCellValue(Row, "requestNo");
			var yearMon = mySheet1.GetCellValue(Row, "yearMon");
			var invoiceTypeCd = mySheet1.GetCellValue(Row, "invoiceType");
			
			libraryFunc.createModal(null, null, null, 1100, 560, "상세내역", "/ModalBot/SalesEseroManageVendorSlipList.do" + "?pVendorCd=" + vendorCd + '&pRequestNo=' + requestNo + '&pYearMon=' + yearMon + '&pInvoiceTypeCd=' + invoiceTypeCd);
   		}
	}
    
	// 오라클 그리드 클릭 함수
	function mySheet2_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
		
		if (mySheet2.ColSaveName(Col) == "vendorNm") {
			var vendorCd = mySheet2.GetCellValue(Row, "vendorCd");
			var requestNo = mySheet2.GetCellValue(Row, "requestNo");
			var yearMon = mySheet2.GetCellValue(Row, "yearMon");
			var invoiceTypeCd = mySheet2.GetCellValue(Row, "invoiceType");
			
			libraryFunc.createModal(null, null, null, 1100, 560, "상세내역", "/ModalBot/SalesEseroManageVendorSlipList.do" + "?pVendorCd=" + vendorCd + '&pRequestNo=' + requestNo + '&pYearMon=' + yearMon + '&pInvoiceTypeCd=' + invoiceTypeCd);
   		}
	}
	
	// 전표 그리드 클릭 함수
	function mySheet3_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
		
		if (mySheet3.ColSaveName(Col) == "vendorNm") {
			var vendorCd = mySheet3.GetCellValue(Row, "vendorCd");
			var requestNo = mySheet3.GetCellValue(Row, "requestNo");
			var yearMon = mySheet3.GetCellValue(Row, "yearMon");
			var invoiceTypeCd = mySheet3.GetCellValue(Row, "invoiceType");
			
			libraryFunc.createModal(null, null, null, 1100, 560, "상세내역", "/ModalBot/SalesEseroManageVendorSlipList.do" + "?pVendorCd=" + vendorCd + '&pRequestNo=' + requestNo + '&pYearMon=' + yearMon + '&pInvoiceTypeCd=' + invoiceTypeCd);
   		}
	}
	
	// 차이 그리드 클릭 함수
	function mySheet4_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
		
		if (mySheet4.ColSaveName(Col) == "vendorNm") {
			var vendorCd = mySheet4.GetCellValue(Row, "vendorCd");
			var requestNo = mySheet4.GetCellValue(Row, "requestNo");
			var yearMon = mySheet4.GetCellValue(Row, "yearMon");
			var invoiceTypeCd = mySheet4.GetCellValue(Row, "invoiceType");
			
			libraryFunc.createModal(null, null, null, 1100, 560, "상세내역", "/ModalBot/SalesEseroManageVendorSlipList.do" + "?pVendorCd=" + vendorCd + '&pRequestNo=' + requestNo + '&pYearMon=' + yearMon + '&pInvoiceTypeCd=' + invoiceTypeCd);
   		}
	}
	
	// 목록 조회 공통 함수
	function initList() {
		var requestNo = $("#requestNo").val();
		var division = $("#division").val();
		var division1 = $("#division1").val();
		var division2 = $("#division2").val();
		var invoiceType = $("#invoiceType").val();
		var vatAmtYn = $("#vatAmtYn").val();
		
		SalesEseroDetail(requestNo, division, division1, division2, invoiceType, vatAmtYn);
	}	
	
	// 홈택스 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_homtax_download", function (e) {		
		var params = { Multipart: 0, FileName: "list.xlsx",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		mySheet1.Down2Excel(params);
	});
	
	// 오라클 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_oracle_download", function (e) {		
		var params = { Multipart: 0, FileName: "list.xlsx",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		mySheet2.Down2Excel(params);
	});
	
	// 전표ERP 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_erp_download", function (e) {		
		var params = { Multipart: 0, FileName: "list.xlsx",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		mySheet3.Down2Excel(params);
	});
	
	// 차이 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_compare_download", function (e) {		
		var params = { Multipart: 0, FileName: "compareList.xlsx",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		mySheet4.Down2Excel(params);
	});
	
</script>