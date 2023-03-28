<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<!-- 그리드영역 -->
		<div class="grid_box">
			<div class="grid_title">홈택스 (세금)계산서 데이터</div>
		</div>
		<div id="sheet1"></div>
		<div class="grid_box">
			<div class="grid_title">오라클 데이터</div>
		</div>
		<div id="sheet2"></div>
		<div class="grid_box">
			<div class="grid_title">전표ERP 데이터 (매출)</div>
		</div>
		<div id="sheet3"></div>
		<div class="grid_box">
			<div class="grid_title">전표ERP 데이터 (외주/자재)</div>
		</div>
		<div id="sheet4"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${menuId}";
	var requestNo = "${requestNo}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listICHomeTaxList(requestNo);
		listICOracleList(requestNo);
		listICSalesSlipList(requestNo);
		listICBuySlipList(requestNo);
	});
	
	//  (세금)계산서 데이터 수집 목록 조회
	function listICHomeTaxList(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListICHomeTaxList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo }),
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
	
	// (세금)계산서 그리드 생성 함수
    function makeGrid(pListDatas) {
    	commonFunc.initSheet("mySheet");

        var initdata = {};

        createIBSheet2(document.getElementById("sheet1"), "mySheet", "1060px", "240px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },
            { Header: "구분", Type: "Text", Width: 100, SaveName: "invoiceTaxType", Align: "Center" },
            { Header: "기준년월", Type: "Text", Width: 60, SaveName: "yearMon", Align: "Center" },
            { Header: "승인번호", Type: "Text", Width: 180, SaveName: "issueNo", Align: "Center" },
            { Header: "작성일자", Type: "Text", Width: 80, SaveName: "makeDate", Align: "Center" },
            { Header: "발급일자", Type: "Float", Width: 80, SaveName: "drawDate", Align: "Center" },
            { Header: "전송일자", Type: "Text", Width: 80, SaveName: "sendDate", Align: "Center"  },
            { Header: "사업자번호\n(공급하는자)", Type: "Text", Width: 90, SaveName: "salesBizNo", Align: "Center" },
            { Header: "종사업자번호", Type: "Text", Width: 100, SaveName: "salesPlaceNo", Align: "Center" },
            { Header: "상호", Type: "Text", Width: 200, SaveName: "salesVendorNm", Align: "Center" },
            { Header: "대표자명", Type: "Text", Width: 100, SaveName: "salesRepNm", Align: "Center" },
            { Header: "주소", Type: "Text", Width: 200, SaveName: "salesAddr" },
            { Header: "사업자번호\n(공급받는자)", Type: "Text", Width: 90, SaveName: "buyBizNo", Align: "Center" },
            { Header: "종사업자번호", Type: "Text", Width: 90, SaveName: "buyPlaceNo", Align: "Center" },
            { Header: "상호", Type: "Text", Width: 120, SaveName: "buyVendorNm", Align: "Center" },
            { Header: "대표자명", Type: "Text", Width: 100, SaveName: "buyRepNm", Align: "Center" },
            { Header: "주소", Type: "Text", Width: 200, SaveName: "buyAddr", Align: "Center" },            
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

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
//  오라클 데이터 수집 목록 조회
	function listICOracleList(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListICOracleList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo }),
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
	
	// 오라클 그리드 생성 함수
    function makeGrid2(pListDatas) {
    	commonFunc.initSheet("mySheet2");

        var initdata = {};

        createIBSheet2(document.getElementById("sheet2"), "mySheet2", "1060px", "200px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },            
            { Header: "기준년월", Type: "Text", Width: 60, SaveName: "yearMon", Align: "Center" },
            { Header: "전표번호", Type: "Text", Width: 180, SaveName: "slipNo", Align: "Center" },
            { Header: "발의부서", Type: "Text", Width: 180, SaveName: "deptNm", Align: "Center" },
            { Header: "회계일자", Type: "Text", Width: 80, SaveName: "paymentDate", Align: "Center" },
            { Header: "사업자번호\n(공급하는자)", Type: "Text", Width: 90, SaveName: "bizNo", Align: "Center" },
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
    }

//  전표ERP (매출) 데이터 수집 목록 조회
	function listICSalesSlipList(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListICSalesSlipList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeGrid3(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 매출전표 그리드 생성 함수
    function makeGrid3(pListDatas) {
    	commonFunc.initSheet("mySheet3");

        var initdata = {};

        createIBSheet2(document.getElementById("sheet3"), "mySheet3", "1060px", "200px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },
        	{ Header: "기준년월", Type: "Text", Width: 60, SaveName: "yearMon", Align: "Center" },
        	{ Header: "승인번호", Type: "Text", Width: 180, SaveName: "issueNo", Align: "Center" },
        	{ Header: "전표번호", Type: "Text", Width: 180, SaveName: "slipNo", Align: "Center" },
        	{ Header: "진행상태", Type: "Text", Width: 100, SaveName: "slipStatus", Align: "Center" },
        	{ Header: "국세청신고상태", Type: "Text", Width: 100, SaveName: "taxInvoiceStatus", Align: "Center" },
        	{ Header: "모듈유형", Type: "Text", Width: 100, SaveName: "moduleCd", Align: "Center" },
        	{ Header: "세금계산서종류", Type: "Text", Width: 100, SaveName: "invoiceType", Align: "Center" },
        	{ Header: "증빙일자", Type: "Text", Width: 100, SaveName: "drawDate", Align: "Center" },
        	{ Header: "발의부서코드", Type: "Text", Width: 100, SaveName: "deptCd", Align: "Center" },
        	{ Header: "발의부서", Type: "Text", Width: 100, SaveName: "deptNm", Align: "Center" },
        	{ Header: "공급받는자사업자등록번호", Type: "Text", Width: 100, SaveName: "buyBizNo", Align: "Center" },
        	{ Header: "공급받는자상호", Type: "Text", Width: 100, SaveName: "buyVendorNm", Align: "Center" },
        	{ Header: "품목", Type: "Text", Width: 100, SaveName: "itemNameCNT", Align: "Center" },
        	{ Header: "공급가액", Type: "Float", Width: 100, SaveName: "supplyAmt" },
        	{ Header: "세액", Type: "Float", Width: 100, SaveName: "vatAmt" }
        ];

        IBS_InitSheet(mySheet3, initdata);
        mySheet3.SetEditable(0);
        mySheet3.SetEditableColorDiff(0);
        mySheet3.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet3.LoadSearchData(pListDatas);
    }
		
//  전표ERP (외주/자재) 데이터 수집 목록 조회
	function listICBuySlipList(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListICBuySlipList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeGrid4(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 외주.자재 전표 그리드 생성 함수
    function makeGrid4(pListDatas) {
    	commonFunc.initSheet("mySheet4");

        var initdata = {};

        createIBSheet2(document.getElementById("sheet4"), "mySheet4", "1060px", "200px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },
        	{ Header: "기준년월", Type: "Text", Width: 60, SaveName: "yearMon",  Align: "Center" },
        	{ Header: "승인번호", Type: "Text", Width: 180, SaveName: "issueNo",  Align: "Center" },
        	{ Header: "전표번호", Type: "Text", Width: 180, SaveName: "slipNo",  Align: "Center" },
        	{ Header: "발행일자", Type: "Text", Width: 100, SaveName: "drawDate",  Align: "Center" },
        	{ Header: "작성일자", Type: "Text", Width: 100, SaveName: "makeDate",  Align: "Center" },
        	{ Header: "모듈유형", Type: "Text", Width: 100, SaveName: "moduleCd",  Align: "Center" },
        	{ Header: "전송상태", Type: "Text", Width: 100, SaveName: "statusCd",  Align: "Center" },
        	{ Header: "공급자사업자등록번호", Type: "Text", Width: 100, SaveName: "salesBizNo",  Align: "Center" },
        	{ Header: "공급자종사업번호", Type: "Text", Width: 100, SaveName: "salesPlaceNo",  Align: "Center" },
        	{ Header: "공급자상호", Type: "Text", Width: 100, SaveName: "salesVendorNm",  Align: "Center" },
        	{ Header: "공급자대표", Type: "Text", Width: 100, SaveName: "salesRepNm",  Align: "Center" },
        	{ Header: "합계", Type: "Float", Width: 100, SaveName: "totAmt" },
        	{ Header: "공급가액", Type: "Float", Width: 100, SaveName: "supplyAmt" },
        	{ Header: "세액", Type: "Float", Width: 100, SaveName: "vatAmt" }
        ];

        IBS_InitSheet(mySheet4, initdata);
        mySheet4.SetEditable(0);
        mySheet4.SetEditableColorDiff(0);
        mySheet4.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet4.LoadSearchData(pListDatas);
    }
</script>