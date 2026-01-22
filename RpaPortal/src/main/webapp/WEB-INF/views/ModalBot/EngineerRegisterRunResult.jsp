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
		ListEngineerRegisterResult(requestNo);
	});
	
	//  인지세 납부확인증 수집대상 결과 조회
	function ListEngineerRegisterResult(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListEngineerRegisterResult.do",
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
            //{ Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", hidden:true},
            { Header: "연번", Type: "Text", Width: 50, SaveName: "Seq", Align: "Center" },
            { Header: "신고일자", Type: "Text", Width: 100, SaveName: "RegisterDate", Align: "Center" },
            { Header: "성명", Type: "Text", Width: 80, SaveName: "UserNm", Align: "Center" },
            { Header: "신고유형", Type: "Text", Width: 100, SaveName: "RegisterType", Align: "Center" },
            { Header: "현장명", Type: "Text", Width: 400, SaveName: "SiteNm", Align: "Center" },
            { Header: "비고", Type: "Text", Width: 100, SaveName: "Etc", Align: "Left" },
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
</script>