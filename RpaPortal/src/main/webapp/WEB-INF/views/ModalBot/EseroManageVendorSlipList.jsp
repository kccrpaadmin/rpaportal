<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">	
		<!-- 상세영역 -->
		<div class="detail_box">
        	<table class="detail_tbl">
	            <caption>상세영역</caption>
	            <colgroup>
	                <col style="width:15%;" />
	                <col style="width:35%;" />
	                <col style="width:15%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                    <th class="detail_th_l">업체명</th>
	                    <td class="detail_td_l">${outEseroVO.vendorNm}</td>
	                    <th class="detail_th_l">전화번호</th>
	                    <td class="detail_td_l">${outEseroVO.telNo}</td>
	                </tr>	    <tr>
	                    <th class="detail_th_l">주소</th>
	                    <td class="detail_td_l" colspan="3">${outEseroVO.addr}</td>
	                </tr>	               
	            </tbody>
	        </table>
	    </div>	
		<!-- 그리드영역 -->	
		<div class="grid_box">
			<div class="grid_title">기준연월 (세금)계산서 상세 </div>	
			<div class="grid_btn">
		    	<a class="btn_common2" id="btn_invoice_download">엑셀</a>
		    </div>
		</div>	
		<div id="sheet1"></div>
		<div class="grid_box">
			<div class="grid_title">최근 1년간 전표 데이터</div>	
			<div class="grid_btn">
		    	<a class="btn_common2" id="btn_slip_download">엑셀</a>
		    </div>
		</div>
		<div id="sheet2"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var vendorCd = "${vendorCd}";
	var requestNo = "${requestNo}";
	var yearMon = "${yearMon}";
	var invoiceTypeCd = "${invoiceTypeCd}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listEseroManageVendorInvoiceList(vendorCd, requestNo, yearMon, invoiceTypeCd);
		listEseroManageVendorSlipList(vendorCd);
	});
	
	// 기준연월 세금계산서 상세 데이터
	function listEseroManageVendorInvoiceList(pVendorCd, pRequestNo, pYearMon, pInvoiceTypeCd) {
		$.ajax({
			url: "/AjaxBot/ListEseroManageVendorInvoiceList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "vendorCd": pVendorCd, "requestNo": pRequestNo, "yearMon": pYearMon, "invoiceTypeCd": pInvoiceTypeCd}),
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
	
	// 업체별 최근 1년간 전표 데이터 조회
	function listEseroManageVendorSlipList(pVendorCd) {
		$.ajax({
			url: "/AjaxBot/ListEseroManageVendorSlipList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "vendorCd": pVendorCd}),
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
	
	// 기준연월에 대한 세금계산서 정보 그리드 생성 함수
    function makeGrid1(pListDatas) {
    	commonFunc.initSheet("mySheet1");

        var initdata = {};

        createIBSheet2(document.getElementById("sheet1"), "mySheet1", "1060px", "185px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
          
            { Header: "기준연월", Type: "Text", Width: 0, SaveName: "yearMon", Align: "Center", Hidden: true },
            { Header: "계산서일자", Type: "Text", Width: 80, SaveName: "makeDate", Align: "Center" },
            { Header: "발급일자", Type: "Text", Width: 80, SaveName: "drawDate", Align: "Center" },
            { Header: "전송일자", Type: "Text", Width: 80, SaveName: "sendDate", Align: "Center" },
            { Header: "전표번호", Type: "Text", Width: 150, SaveName: "slipNo", Align: "Center" },
            { Header: "국세청인증번호", Type: "Text", Width: 180, SaveName: "issueNo", Align: "Center" },
            { Header: "적요", Type: "Text", Width: 250, SaveName: "itemNm" },
            { Header: "공급가액", Type: "Float", Width: 80, SaveName: "supplyAmt" },
            { Header: "부가세액", Type: "Float", Width: 80, SaveName: "vatAmt" },
            { Header: "합계", Type: "Float", Width: 80, SaveName: "totAmt" },
            { Header: "공급받은자이메일", Type: "Text", Width: 150, SaveName: "buyEmail1" },
            { Header: "이름", Type: "Text", Width: 70, SaveName: "userNm", Align: "Center" },
            { Header: "부서", Type: "Text", Width: 150, SaveName: "deptNm" },
            { Header: "휴대전화번호", Type: "Text", Width: 100, SaveName: "userMobileNo", Align: "Center" },
            { Header: "직급", Type: "Text", Width: 60, SaveName: "dutyNm", Align: "Center" }
            //{ Header: "사번", Type: "Text", Width: 60, SaveName: "userId", Align: "Center" }
        ];

        IBS_InitSheet(mySheet1, initdata);
        mySheet1.SetEditable(0);
        mySheet1.SetEditableColorDiff(0);
        mySheet1.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet1.LoadSearchData(pListDatas);
    }    
	
	// 최근 1년간 전표 데이터 그리드 생성 함수
    function makeGrid2(pListDatas) {
    	commonFunc.initSheet("mySheet2");

        var initdata = {};

        createIBSheet2(document.getElementById("sheet2"), "mySheet2", "1060px", "185px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
          
            { Header: "전표번호", Type: "Text", Width: 150, SaveName: "slipId", Align: "Center" },
            { Header: "회계처리일자", Type: "Text", Width: 80, SaveName: "glDate", Align: "Center" },
            { Header: "발의부서", Type: "Text", Width: 200, SaveName: "deptNm", Align: "Center" },
            { Header: "작성자", Type: "Text", Width: 120, SaveName: "userNm", Align: "Center" },
            { Header: "적요", Type: "Text", Width: 300, SaveName: "summary" },
            { Header: "품목", Type: "Text", Width: 190, SaveName: "description" }
        ];

        IBS_InitSheet(mySheet2, initdata);
        mySheet2.SetEditable(0);
        mySheet2.SetEditableColorDiff(0);
        mySheet2.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet2.LoadSearchData(pListDatas);
    }    
	
    // 기준연월 세금계산서 상세 엑셀 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_invoice_download", function (e) {	
		var fileName = yearMon + "_" + "${outEseroVO.vendorNm}" + "_(세금)계산서상세내역.xls"
		
		//var params = { Multipart: 0, FileName: "VendorInvoiceFile.xls",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		var params = { Multipart: 0, FileName: fileName,  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		
		mySheet1.Down2Excel(params);
	});
	
	// 최근 1년간 전표데이터 엑셀 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_slip_download", function (e) {		
		var fileName = yearMon + "_" + "${outEseroVO.vendorNm}" + "_최근1년간전표내역.xls"
		
		//var params = { Multipart: 0, FileName: "VendorSlipFile.xls",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		var params = { Multipart: 0, FileName: fileName,  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 }
		
		mySheet2.Down2Excel(params);
	});
</script>