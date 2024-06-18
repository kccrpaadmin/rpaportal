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
	                </tr>	                
	            </tbody>
	        </table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_search">조회</a>
	    </div>
	    <!-- 그리드영역 -->
	    <div class="grid_box">
		    <div class="grid_title">전체 집계</div>
		    <div class="grid_btn">
		    	<a class="btn_common2" id="btn_download">엑셀</a>
		    </div>
	    </div>
   	    <div id="sheet1"></div>
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
		commonFunc.createDatepicker(".datepicker_ym", "YearMonth");
			
		searchListEseroInvoiceSlipListManage();
	});
		
	// 세금계산서 전표 대사 목록 조회
	function ListICManage(pYearMon) {
		$.ajax({
			url: "/AjaxBot/ListICManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "yearMon": pYearMon}),
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
	
	// 그리드 생성 함수
    function makeGrid1(pListDatas) {
    	commonFunc.initSheet("mySheet1");
		
        var initdata = {};

        createIBSheet2(document.getElementById("sheet1"), "mySheet1", "1120px", "395px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1,DataRowMerge:1 };
        initdata.HeaderMode = { Sort: 0, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "요청번호|요청번호|요청번호", Type: "Text", Width: 0, SaveName: "requestNo", Align: "Center", Hidden: true },
        	{ Header: "구분|구분|구분", Type: "Text", Width: 150, SaveName: "division1", Align: "Center" },
        	{ Header: "구분|구분|구분", Type: "Text", Width: 50, SaveName: "division2", Align: "Center" },
        	
        	{ Header: "1.세금계산서 발행 내역|세금계산서|건수", Type: "Int", Width: 55, SaveName: "a_TotCnt1", Align: "Center" },
        	{ Header: "1.세금계산서 발행 내역|세금계산서|공급가액", Type: "Int", Width: 100, SaveName: "a_SupplyAmt1", Align: "Center" },
        	{ Header: "1.세금계산서 발행 내역|세금계산서|부가가치세", Type: "Int", Width: 100, SaveName: "a_VatAmt1", Align: "Center" },
        	{ Header: "1.세금계산서 발행 내역|계산서|건수", Type: "Int", Width: 55, SaveName: "a_TotCnt2", Align: "Center" },
        	{ Header: "1.세금계산서 발행 내역|계산서|공급가액", Type: "Int", Width: 100, SaveName: "a_SupplyAmt2", Align: "Center" },
        	{ Header: "1.세금계산서 발행 내역|영세율|건수", Type: "Int", Width: 55, SaveName: "a_TotCnt3", Align: "Center" },
        	{ Header: "1.세금계산서 발행 내역|영세율|공급가액", Type: "Int", Width: 100, SaveName: "a_SupplyAmt3", Align: "Center" },
        	
        	{ Header: "2.홈택스 전송여부|세금계산서|건수", Type: "Int", Width: 55, SaveName: "h_TotCnt1", Align: "Center",ColMerge:1},
        	{ Header: "2.홈택스 전송여부|세금계산서|공급가액", Type: "Int", Width: 100, SaveName: "h_SupplyAmt1", Align: "Center" },
        	{ Header: "2.홈택스 전송여부|세금계산서|부가가치세", Type: "Int", Width: 100, SaveName: "h_VatAmt1", Align: "Center" },
        	{ Header: "2.홈택스 전송여부|계산서|건수", Type: "Int", Width: 55, SaveName: "h_TotCnt2", Align: "Center" },
        	{ Header: "2.홈택스 전송여부|계산서|공급가액", Type: "Int", Width: 100, SaveName: "h_SupplyAmt2", Align: "Center" },
        	{ Header: "2.홈택스 전송여부|영세율|건수", Type: "Int", Width: 55, SaveName: "h_TotCnt3", Align: "Center" },
        	{ Header: "2.홈택스 전송여부|영세율|공급가액", Type: "Int", Width: 100, SaveName: "h_SupplyAmt3", Align: "Center" },
        	
        	{ Header: "3.차이(1-2)|세금계산서|건수", Type: "Int", Width: 55, SaveName: "mTotCnt1", Align: "Center" },
        	{ Header: "3.차이(1-2)|세금계산서|공급가액", Type: "Int", Width: 100, SaveName: "mSupplyAmt1", Align: "Center" }, 
        	{ Header: "3.차이(1-2)|세금계산서|부가가치세", Type: "Int", Width: 100, SaveName: "mVatAmt1", Align: "Center" },  
        	{ Header: "3.차이(1-2)|계산서|건수", Type: "Int", Width: 55, SaveName: "mTotCnt2", Align: "Center" },
        	{ Header: "3.차이(1-2)|계산서|공급가액", Type: "Int", Width: 100, SaveName: "mSupplyAmt2", Align: "Center" },
        	{ Header: "3.차이(1-2)|영세율|건수", Type: "Int", Width: 55, SaveName: "mTotCnt3", Align: "Center"},  
        	{ Header: "3.차이(1-2)|영세율|공급가액", Type: "Int", Width: 100, SaveName: "mSupplyAmt3", Align: "Center" } 
        ];

        IBS_InitSheet(mySheet1, initdata);
        mySheet1.SetEditable(0);
        mySheet1.SetEditableColorDiff(0);
        mySheet1.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        
        var loadOption = {};
        loadOption = { Sync : 1 };
        mySheet1.LoadSearchData(pListDatas, loadOption);
        
        var lastRow = mySheet1.LastRow();
        var lastCol = mySheet1.LastCol();
        
        //link and underline
        var cols_data = mySheet1.Cols;
        for(let col in cols_data) {//열
            var save_nm = cols_data[col].SaveName;
            if(save_nm != null) {
	            var cntYn = save_nm.indexOf("Cnt");
	            if(cntYn > -1) {
	            	mySheet1.SetDataLinkMouse(save_nm, true);
	                mySheet1.SetColFontUnderline(save_nm, true);
	            }
            }
        }
        
        //구분 첫행 머지
        var merge_row = 1;
        for(r=3; r<=lastRow; r++) { //행
            var data = mySheet1.GetRowData(r);
            var data_next = mySheet1.GetRowData(r+1);
            if(data.division1 == data_next.division1) {
            	merge_row++;//머지 추가
            } else {
            	mySheet1.SetMergeCell(r-merge_row+1, 1, merge_row, 1); //r행 c열 merge_row칸 1컬럼
            	merge_row = 1;
            }
            //홈택스 매입 부분 머지
            if(r<10) {
	            for(c=10; c<=lastCol; c++) {//열
	            	var division2 = mySheet1.GetCellText(r,2);
	     		   if(division2 == "외주") {
	     			  var above = mySheet1.GetCellText(r,c);
	     			  var below = mySheet1.GetCellText(r+1,c);
	     			  if(above == below) mySheet1.SetMergeCell(r, c, 2, 1); //r행 c열 2칸 1컬럼
	     		   }
	 	       }
           }
            //'계' Bold
            if(data.division2 == "계") {
            	for(c=1; c<=lastCol; c++) {//열
            		mySheet1.SetCellFontBold(r,c,1)
            	}
            }
       }
        
        //차이 계산
        for(c=3; c<=lastCol; c++) { //열
	        for(r=3; r<=lastRow; r++) { //행
	        	var standard = mySheet1.ComputeSum("|"+c+"|",r,r);
	            var contrast = mySheet1.ComputeSum("|"+(c+7)+"|",r,r);
	            var division2_a = mySheet1.GetCellText(r,2);
	        	if(r<10 && division2_a == "외주") {
	        		var division2_b = mySheet1.GetCellText(r+1,2);
	        		if(division2_b == "자재") standard = mySheet1.ComputeSum("|"+c+"|",r,r+1);
	        	}
	        	mySheet1.SetCellValue(r,c+14,standard-contrast);
	        	if(r<10 && r%4 == 0) r++;
	        }
        }
        
      //mySheet1.SetMergeCell(lastRow, 1, 2, 4);
    }  
	
	
    // 그리드 클릭 함수
	function mySheet1_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
		
		var save_nm = mySheet1.ColSaveName(Col);
		if (save_nm.indexOf("Cnt") > -1) {
			console.log(mySheet1.GetCellValue(0, Col));
			var requestNo = mySheet1.GetCellValue(Row, "requestNo");
			var division = mySheet1.GetCellValue(0, Col).split(".")[0]; // 1/2/3
			var division1 = mySheet1.GetCellValue(Row, "division1").split(".")[0]; // A/B/C
			var division2 = mySheet1.GetCellValue(Row, "division2"); //매출/외주/자재/계
			
			var invoiceType = "세금계산서";//default 세금계산서
			var vatAmtYn = "Y";
			if (save_nm.indexOf("2") > -1) invoiceType = "계산서"; 
			if (save_nm.indexOf("3") > -1) vatAmtYn = "N"; //영세율
			
			libraryFunc.createModal(null, null, null, 1100, 560, "상세내역", "/ModalBot/SalesEseroDetail.do" 
							+ '?pRequestNo=' + requestNo 
							+ '&pDivision=' + division
							+ '&pDivision1=' + division1
							+ '&pDivision2=' + division2
							+ '&pInvoiceType=' + invoiceType
							+ '&pVatAmtYn=' + vatAmtYn
							);
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
		//var errorYn = getCheckYn("#error_yn");		
		
		ListICManage(yearMon);
		
		//listEseroInvoiceSlipListManageTaxOff(yearMon, errorYn);
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
	
	// 파일 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_download", function (e) {		
		let currentDate = new Date().toJSON().slice(0, 10);
		var date = currentDate.replaceAll("-",""); 
		var month = commonFunc.getReplaceAll($("#year_mon").val(),"-","").substring(4);
		if(month.substring(0,1) == "0") month = month.substring(1);
		var params = { Multipart: 0, FileName: "e세로전송("+month+"월증빙)_"+date+".xlsx",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20 ,HiddenColumn:1}
		mySheet1.Down2Excel(params);
	});
	
	
</script>