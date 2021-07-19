<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<div></div>
		<!-- 그리드영역 -->
		<div id="sheet"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var checkId = "${checkId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		ListMoneySendManageSendAmt(checkId);
	});
		
	// 업체 조회
	function ListMoneySendManageSendAmt(pCheckId) {
		$.ajax({
			url: "/AjaxBot/ListMoneySendManageSendAmt.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "checkId": pCheckId }),
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1060px", "450px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [                        
        	{ Header: "증빙일자", Type: "Text", Width: 80, SaveName: "invoiceDate", Align: "Center", Edit:0},
            { Header: "회계처리일자", Type: "Text", Width: 80, SaveName: "glDate", Align: "Center", Edit:0 },
            { Header: "지급일자", Type: "Text", Width: 80, SaveName: "transDate", Align: "Center", Edit:0 },            
            { Header: "전표금액", Type: "AutoSum", Width: 110, SaveName: "invoiceAmount", Align: "Right", Edit:0 },
            { Header: "상계후금액", Type: "AutoSum", Width: 110, SaveName: "amount", Align: "Right", Edit:0 },
            //{ Header: "실송금액", Type: "Float", Width: 100, SaveName: "sendAmt", Align: "Right", Edit:0 },            
            { Header: "입금은행", Type: "Text", Width: 70, SaveName: "remitBankNm", Align: "Center", Edit:0 },
            { Header: "입금계좌번호", Type: "Text", Width: 130, SaveName: "remitAccountNo", Align: "Center", Edit:0 },
            { Header: "수취인", Type: "Text", Width: 200, SaveName: "remitteeNm", Align: "Center", Edit:0 },
            { Header: "적요", Type: "Text", Width: 200, SaveName: "invoiceDescription", Align: "Center", Edit:0 },
            { Header: "CheckID", Type: "Text", Width: 0, SaveName: "checkId", Align: "Center", Hidden: true }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetColFontUnderline("vendorNm", true);
        mySheet.SetDataLinkMouse("vendorNm", true);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
        
    }
	
</script>