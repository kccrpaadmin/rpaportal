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
		listPersonalBcc(requestNo);
	});
	
	//  법인카드 신청 수행 목록 조회
	function listPersonalBcc(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListPersonalBcc.do",
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

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", 		Type: "Text", Width: 0,    SaveName: "requestNo", 				Hidden:true },            
            { Header: "사번", 			Type: "Text", Width: 70,  SaveName: "applicantId", 				Align: "Center" },
            { Header: "이름", 			Type: "Text", Width: 70, SaveName: "userNm", 						Align: "Center" },
            { Header: "부서/현장",		Type: "Text", Width: 180, SaveName: "deptNm", 					Align: "Center" },
            { Header: "직급", 			Type: "Text", Width: 50,  SaveName: "dutyNm", 					Align: "Center" },
            { Header: "한도", 			Type: "Text", Width: 50,  SaveName: "amtLmt", 						Align: "Center" },
            { Header: "신청구분", 		Type: "Text", Width: 60,  SaveName: "applyCdNm", 				Align: "Center" },
            { Header: "영문성", 		Type: "Text", Width: 60, SaveName: "engLastNm", 				Align: "Center" },
            { Header: "영문이름", 		Type: "Text", Width: 100, SaveName: "engFirstNm", 				Align: "Center" },
            { Header: "우편번호", 		Type: "Text", Width: 60, SaveName: "zipCd", 						Align: "Center" },
            { Header: "수령지주소", 	Type: "Text", Width: 200, SaveName: "compAddress", 			Align: "Center" },
            { Header: "성공여부", 		Type: "Text", Width: 60, SaveName: "successTypeCdNm", 		Align: "Center" },
            { Header: "오류내용", 		Type: "Text", Width: 100, SaveName: "errorMsg", 					Align: "Center" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
</script>