<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<!-- 그리드영역 -->
		<div class="grid_box">
			<div class="grid_title">설계시공 계속교육 이수자</div>
		</div>
		<div id="sheet1"></div>
		<div class="grid_box">
			<div class="grid_title">설계시공 계속교육 미이수자</div>
		</div>
		<div id="sheet2"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${menuId}";
	var requestNo = "${requestNo}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listEngineerConstEduPass(requestNo);
		listEngineerConstEduFail(requestNo);
	});
	
	// 설계시공 계속교육 이수자 조회
	function listEngineerConstEduPass(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListBotEngineerConstEduPass.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo }),
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
	
	// 설계시공 계속교육 미이수자 조회
	function listEngineerConstEduFail(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListBotEngineerConstEduFail.do",
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
	    
    // 설계시공 계속교육 이수자 그리드 생성 함수
    function makeGrid1(pListDatas) {
    	commonFunc.initSheet("mySheet1");

        var initdata = {};
        
        createIBSheet2(document.getElementById("sheet1"), "mySheet1", "1060px", "300px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 0, SaveName: "requestNo", Hidden:true },            
            { Header: "성명", Type: "Text", Width: 80, SaveName: "userNm", Align: "Center" },
            { Header: "생년월일", Type: "Text", Width: 80, SaveName: "resNo", Align: "Center"},  
            { Header: "차수", Type: "Float", Width: 50, SaveName: "phase", Align: "Center" },          
            { Header: "기산일", Type: "Text", Width: 80, SaveName: "initDate", Align: "Center"},
            { Header: "수행일", Type: "Text", Width: 60, SaveName: "runDays", Align: "Center" },
            { Header: "3년되는날", Type: "Text", Width: 80, SaveName: "threeYearDate", Align: "Center" },
            { Header: "이수/교육종류", Type: "Text", Width: 90, SaveName: "eduType", Align: "Center" },
            { Header: "교육시간", Type: "Text", Width: 80, SaveName: "eduTimes", Align: "Center" },
            { Header: "인정학점", Type: "Text", Width: 80, SaveName: "recogScore", Align: "Center" },
            { Header: "연기일", Type: "Text", Width: 80, SaveName: "delayDate", Align: "Center" },
            { Header: "이수일", Type: "Text", Width: 80, SaveName: "passDate", Align: "Center" },
            { Header: "이수시간", Type: "Text", Width: 60, SaveName: "passTimes", Align: "Center" },
            { Header: "이수학점", Type: "Text", Width: 60, SaveName: "passScore", Align: "Center" },
            { Header: "접수일", Type: "Text", Width: 80, SaveName: "receiptDate", Align: "Center" }
        ];

        IBS_InitSheet(mySheet1, initdata);
        mySheet1.SetEditable(0);
        mySheet1.SetEditableColorDiff(0);
        mySheet1.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet1.LoadSearchData(pListDatas);
    }
    
    // 설계시공 계속교육 미이수자 그리드 생성 함수
    function makeGrid2(pListDatas) {
    	commonFunc.initSheet("mySheet2");

        var initdata = {};
        
        createIBSheet2(document.getElementById("sheet2"), "mySheet2", "1060px", "300px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 0, SaveName: "requestNo", Hidden:true },            
            { Header: "성명", Type: "Text", Width: 80, SaveName: "userNm", Align: "Center" },
            { Header: "생년월일", Type: "Text", Width: 80, SaveName: "resNo", Align: "Center"},  
            { Header: "차수", Type: "Float", Width: 50, SaveName: "phase", Align: "Center" },          
            { Header: "기산일", Type: "Text", Width: 80, SaveName: "initDate", Align: "Center"},
            { Header: "수행일", Type: "Text", Width: 60, SaveName: "runDays", Align: "Center" },
            { Header: "3년되는날", Type: "Text", Width: 80, SaveName: "threeYearDate", Align: "Center" },
            { Header: "이수/교육종류", Type: "Text", Width: 90, SaveName: "eduType", Align: "Center" },
            { Header: "교육시간", Type: "Text", Width: 80, SaveName: "eduTimes", Align: "Center" },
            { Header: "인정학점", Type: "Text", Width: 80, SaveName: "recogScore", Align: "Center" },
            { Header: "연기일", Type: "Text", Width: 80, SaveName: "delayDate", Align: "Center" },
            { Header: "이수일", Type: "Text", Width: 80, SaveName: "passDate", Align: "Center" },
            { Header: "이수시간", Type: "Text", Width: 60, SaveName: "passTimes", Align: "Center" },
            { Header: "이수학점", Type: "Text", Width: 60, SaveName: "passScore", Align: "Center" },
            { Header: "접수일", Type: "Text", Width: 80, SaveName: "receiptDate", Align: "Center" }
        ];

        IBS_InitSheet(mySheet2, initdata);
        mySheet2.SetEditable(0);
        mySheet2.SetEditableColorDiff(0);
        mySheet2.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet2.LoadSearchData(pListDatas);
    }
    	
</script>