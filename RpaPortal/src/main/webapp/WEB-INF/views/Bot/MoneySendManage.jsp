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
	                <col style="width:10%;" />
	                <col style="width:20%;" />
	                <col style="width:10%;" />
	                <col style="width:20%;" />
	                <col style="width:10%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                	<th class="search_dtl_th">업체명</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="txt_box_l" style="width:160px;" id="vendor_nm" readonly="readonly" />
	                        <input type="hidden" class="txt_box_l" style="width:160px;" id="vendor_cd" />
	                        <input type="button"  id="btn_searchVendor" value="검색">
	                    </td>
	                	<th class="search_dtl_th">회계처리일자</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="datepicker_ymd" readonly="readonly"  id="gl_date" value="${glDate}" />
	                    </td>
	                    <th class="search_dtl_th">전표금액</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="txt_box_r number_15_0" style="width:160px;" id="send_amt" />
	                    </td>
	                </tr>	                
	            </tbody>
	        </table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common" id="btn_search">조회</a>
	    </div>
	    <p>※ 다른 전표 건과 통합 지급된 경우, 전표 금액보다 실송금액이 클 수 있습니다.</p>
	    <p>※ 실송금액이 10억 원 이상일 경우, 10억 원 단위로 분리 지급되므로 여러 행이 표시될 수 있습니다.</p>
	    <p>※ 실송금액을 클릭하면, 통합 지급 내역을 확인하실 수 있습니다.</p>
	    <br>
	    <!-- 그리드영역 -->
   	    <div id="sheet"></div>
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
		commonFunc.createDatepicker(".datepicker_ymd", "YearMonthDay");
		libraryFunc.applyTypingNumber("number_15_0", 15, 0);
		searchListMoneySendManage("","","")
	});
	
	// 송금확인증 목록 조회
	function ListMoneySendManage(pVendorCd, pGlDate, pSendAmt) {
		$.ajax({
			url: "/AjaxBot/ListMoneySendManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "vendorCd": pVendorCd, "glDate": pGlDate, "sendAmt": pSendAmt }),
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

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "증빙일자", Type: "Text", Width: 80, SaveName: "invoiceDate", Align: "Center", Edit:0},
            { Header: "회계처리일자", Type: "Text", Width: 80, SaveName: "glDate", Align: "Center", Edit:0 },
            { Header: "지급일자", Type: "Text", Width: 80, SaveName: "transDate", Align: "Center", Edit:0 },            
            { Header: "전표금액", Type: "Float", Width: 100, SaveName: "invoiceAmount", Align: "Right", Edit:0 },
            { Header: "상계후금액", Type: "Float", Width: 100, SaveName: "amount", Align: "Right", Edit:0 },
            { Header: "실송금액", Type: "Float", Width: 100, SaveName: "sendAmt", Align: "Right", Edit:0 },            
            { Header: "입금은행", Type: "Text", Width: 70, SaveName: "remitBankNm", Align: "Center", Edit:0 },
            { Header: "입금계좌번호", Type: "Text", Width: 120, SaveName: "remitAccountNo", Align: "Center", Edit:0 },
            { Header: "수취인", Type: "Text", Width: 200, SaveName: "remitteeNm", Align: "Center", Edit:0 },
            { Header: "송금확인증", Type: "Button", Width: 80, SaveName: "btnPrint", Align: "Center"},        
            { Header: "송금확인증파일ID", Type: "Text", Width: 0, SaveName: "attId", Align: "Center", Hidden: true },
            { Header: "송금확인증파일경로", Type: "Text", Width: 0, SaveName: "attFilePath", Align: "Center", Hidden: true },
            { Header: "적요", Type: "Text", Width: 200, SaveName: "invoiceDescription", Align: "Center", Edit:0 },
            { Header: "CheckID", Type: "Text", Width: 0, SaveName: "checkId", Align: "Center", Hidden: true }
        ];
		
        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetDataLinkMouse("sendAmt", true);
        mySheet.SetColFontUnderline("sendAmt", true);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }  
	
	// 업체 검색 팝업에서 전달받은 업체명, 업체코드 입력
	function setVendorNm(vendorCd, vendorNm) {		
		$("#vendor_nm").val(vendorNm);
		$("#vendor_cd").val(vendorCd);
	}
	
	// 목록 조회 공통 함수
	function searchListMoneySendManage(vendorCd, glDate, sendAmt) {
		ListMoneySendManage(vendorCd, glDate, sendAmt);
	}
	
    // 그리드 클릭 함수
	function mySheet_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}		
		
		if (mySheet.ColSaveName(Col) == "sendAmt") {			
			var checkId = mySheet.GetCellValue(Row, "checkId");
			libraryFunc.createModal(null, null, null, 1100, 560, "통합 지급 내역", "/ModalBot/MoneySendManageSendAmt.do" + "?pCheckId=" + checkId );
   		}
		
		if (mySheet.ColSaveName(Col) == "btnPrint") {
			var attId = mySheet.GetCellValue(Row, "attId");
			window.location.href = "/FileDownload/Download.do?attId=" + attId + "&seq=1";
   		}
	}
	
	// 업체 검색 버튼 클릭 이벤트
	$(document).on("click", "#btn_searchVendor", function (e) {
		libraryFunc.createModal(null, null, null, 1100, 560, "업체 검색", "/ModalBot/MoneySendManageSearchVendor.do");
	});
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {
		var vendorCd = $("#vendor_cd").val();
		var glDate = $("#gl_date").val();
		var sendAmt = $("#send_amt").val();
		
		if (vendorCd == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "업체가 선택되지 않았습니다.", null, null);
    		return false;
    	}
		
		if (glDate == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "회계처리일자가 입력되지 않았습니다.", null, null);
    		return false;
    	}
		
		if (sendAmt == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "송금액이 입력되지 않았습니다.", null, null);
    		return false;
    	}
		
		searchListMoneySendManage(vendorCd, glDate, sendAmt);
	});

</script>