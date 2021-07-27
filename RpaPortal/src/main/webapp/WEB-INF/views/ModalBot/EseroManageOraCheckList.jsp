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
	                <col />
				</colgroup>
				<tbody>
					<tr>
						<th class="search_dtl_th">조회구분</th>
						<td class="search_dtl_td">
							<label class="lbl_every"><input type="checkbox" class="chk_every" id="error_yn" /><span class="spn_every">불일치건만 조회</span></label>
							<input type="button"  id="btn_search" value="조회" />						
						</td>				
					</tr>				
				</tbody>				
			</table>
	    </div>	
		<!-- 그리드영역 -->		
		<div id="sheet"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${menuId}";
	var yearMon = "${yearMon}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		searchListEseroOraCheckList();
	});
	
	//  오라클 체크리스트 조회
	function listEseroOraCheckList(pYearMon, pErrorYn) {
		$.ajax({
			url: "/AjaxBot/ListEseroInvoiceSlipListManageOraCheckList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "yearMon": pYearMon, "errorYn": pErrorYn}),
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1060px", "450px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
          
            { Header: "발의부서|발의부서", Type: "Text", Width: 150, SaveName: "deptNm", Align: "Center" },
            { Header: "회계일자|회계일자", Type: "Text", Width: 80, SaveName: "slipPaymentDate", Align: "Center" },
            { Header: "전표번호|전표번호", Type: "Text", Width: 150, SaveName: "slipNo", Align: "Center" },
            { Header: "사업자번호|사업자번호", Type: "Text", Width: 100, SaveName: "slipBizNo", Align: "Center" },
            { Header: "상호명|상호명", Type: "Text", Width: 150, SaveName: "vendorNm" },
            { Header: "오라클|세금계산서구분", Type: "Text", Width: 100, SaveName: "invoiceType", Align: "Center" },
            { Header: "오라클|매입매출구분", Type: "Text", Width: 90, SaveName: "buySalesType", Align: "Center" },  
            { Header: "오라클|전자/일반", Type: "Text", Width: 60, SaveName: "onOffType", Align: "Center" },
            { Header: "오라클|적요", Type: "Text", Width: 180, SaveName: "summary" },
            //{ Header: "오라클|전송일자", Type: "Text", Width: 80, SaveName: "sendDate", Align: "Center" },
            { Header: "오라클|국세청인증번호", Type: "Text", Width: 180, SaveName: "slipIssueNo", Align: "Center" },
            { Header: "오라클|계산서일자", Type: "Text", Width: 80, SaveName: "slipDrawDate", Align: "Center" },
            { Header: "오라클|공급가액", Type: "Float", Width: 100, SaveName: "slipSupplyAmt" },
            { Header: "오라클|부가가치세", Type: "Float", Width: 100, SaveName: "slipVatAmt" },           
            //{ Header: "홈택스|품목", Type: "Text", Width: 150, SaveName: "itemNm" },
            { Header: "홈택스|국세청인증번호", Type: "Text", Width: 180, SaveName: "invoiceIssueNo", Align: "Center" },
            { Header: "홈택스|계산서일자", Type: "Text", Width: 80, SaveName: "invoiceMakeDate", Align: "Center" },            
            { Header: "홈택스|공급가액", Type: "Float", Width: 100, SaveName: "invoiceSupplyAmt"  },
            { Header: "홈택스|부가가치세", Type: "Float", Width: 100, SaveName: "invoiceVatAmt"  },
            { Header: "홈택스|합계", Type: "Float", Width: 100, SaveName: "totAmt" },
            { Header: "일치여부|국세청인증번호", Type: "Text", Width: 90, SaveName: "issueNoYn", Align: "Center" },
            { Header: "일치여부|사업자번호", Type: "Text", Width: 80, SaveName: "bizNoYn", Align: "Center" },
            { Header: "일치여부|계산서일자", Type: "Text", Width: 80, SaveName: "drawDateYn", Align: "Center" },
            { Header: "일치여부|공급가액", Type: "Text", Width: 80, SaveName: "supplyAmtYn", Align: "Center" },
            { Header: "일치여부|부가가치세", Type: "Text", Width: 80, SaveName: "vatAmtYn", Align: "Center" },
            { Header: "일치여부|합계", Type: "Text", Width: 80, SaveName: "totAmtYn", Align: "Center" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
    // 불일치 행에 폰트 색상 변경 함수
	function mySheet_OnRowSearchEnd(row) {
		if((mySheet.GetCellValue(row, "issueNoYn") != "Y") || (mySheet.GetCellValue(row, "bizNoYn") != "Y") || (mySheet.GetCellValue(row, "drawDateYn") != "Y")
			  || (mySheet.GetCellValue(row, "supplyAmtYn") != "Y") || (mySheet.GetCellValue(row, "vatAmtYn") != "Y") || (mySheet.GetCellValue(row, "totAmtYn") != "Y")){
			mySheet.SetRowFontColor(row , "#FF0000");			    
		}  		
	}
    
	// 목록 조회 공통 함수
	function searchListEseroOraCheckList() {
		var errorYn = getCheckYn("#error_yn");		
		
		listEseroOraCheckList(yearMon, errorYn);
	}	
	
	// 체크 박스 체크 여부 조회
	function getCheckYn(pChkBoxId) {
		var returnVal = "";
		if ($(pChkBoxId).is(":checked")) {
			returnVal = "Y";
		}
		return returnVal;
	}
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {								
		searchListEseroOraCheckList();
	});
		
</script>