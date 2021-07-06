<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<!-- 그리드영역 -->
		<div id="sheet"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${menuId}";
	var requestNo = "${requestNo}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listMoneySend(requestNo);
	});
	
	//  송금내역 수집 데이터 목록 조회
	function listMoneySend(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListMoneySend.do",
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
	
	// 그리드 생성 함수
    function makeGrid(pListDatas) {
    	commonFunc.initSheet("mySheet");

        var initdata = {};

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1060px", "510px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },
            { Header: "이체일자", Type: "Text", Width: 100, SaveName: "sendDate", Align: "Center" },
            { Header: "입금은행", Type: "Text", Width: 100, SaveName: "remitBankNm", Align: "Center" },
            { Header: "입금계좌번호", Type: "Text", Width: 200, SaveName: "remitAccountNo", Align: "Center" },
            { Header: "수취인", Type: "Text", Width: 250, SaveName: "remitteeNm", Align: "Center" },
            { Header: "이체금액", Type: "Float", Width: 150, SaveName: "sendAmt"},
            { Header: "수수료", Type: "Float", Width: 100, SaveName: "sendFee" },
            { Header: "출금은행", Type: "Text", Width: 100, SaveName: "sendBankNm", Align: "Center" },
            { Header: "출금계좌번호", Type: "Text", Width: 200, SaveName: "sendAccountNo", Align: "Center" },
            { Header: "작성일련번호", Type: "Text", Width: 200, SaveName: "serialNo", Align: "Center",Hidden:true }            
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
</script>