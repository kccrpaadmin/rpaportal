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
		listInsurance(requestNo);
	});
	
	// 세움터 사용승인(허가) 수집 목록 조회
	function listInsurance(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListInsurance.do",
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "951px", "417px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 0, SaveName: "requestNo", Hidden:true },
            { Header: "계약번호", Type: "Text", Width: 100, SaveName: "contractNo", Align: "Center" },
            { Header: "변경차수", Type: "Text", Width: 50, SaveName: "chgSeq", Align: "Center" },
            { Header: "구분", Type: "Text", Width: 70, SaveName: "insTypeNm", Align: "Center" },
            { Header: "발급번호", Type: "Text", Width: 120, SaveName: "issueNo", Align: "Center" },
            { Header: "사업장관리번호", Type: "Text", Width: 90, SaveName: "bizMngNo", Align: "Center" },
            { Header: "사업자번호", Type: "Text", Width: 90, SaveName: "bizNo", Align: "Center" },
            { Header: "사업명", Type: "Text", Width: 300, SaveName: "bizNm" },
            { Header: "업체납부금액", Type: "Float", Width: 80, SaveName: "vendorPayAmt" },
            { Header: "진위확인금액", Type: "Float", Width: 80, SaveName: "confirmPayAmt" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
</script>