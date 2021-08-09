<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<!-- 그리드영역 -->
		<p>기본교육 및 최초교육 이수 현황</p>
		<div id="sheet1"></div>
		<p>품질관리 현재등급 및 승급교육후 등급</p>
		<div id="sheet2"></div>
		<p>품질관리 계속교육 이수자</p>
		<div id="sheet3"></div>
		<p>품질관리 계속교육 미이수자</p>
		<div id="sheet4"></div>
		<p>기술자 경력 내역</p>
		<div id="sheet5"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${menuId}";
	var requestNo = "${requestNo}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listEngineerBasic(requestNo);
		listEngineerQualityRank(requestNo);
		listEngineerQualityEduPass(requestNo);
		listEngineerQualityEduFail(requestNo);
		listEngineerCareerList(requestNo);
	});
	
	// 기본교육 및 최초교육 이수 현황 조회
	function listEngineerBasic(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListEngineerBasic.do",
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
	
	// 품질관리 현재등급 및 승급교육후 등급 조회
	function listEngineerQualityRank(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListEngineerQualityRank.do",
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
	
	// 품질관리 계속교육 이수자 조회
	function listEngineerQualityEduPass(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListEngineerQualityEduPass.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeGrid3(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 품질관리 계속교육 미이수자 조회
	function listEngineerQualityEduFail(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListEngineerQualityEduFail.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeGrid4(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 기술자 경력내역 정보 조회
	function listEngineerCareerList(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListEngineerCareerList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeGrid5(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 기본교육 및 최초교육 이수 현황 그리드 생성 함수
    function makeGrid1(pListDatas) {
    	commonFunc.initSheet("mySheet1");

        var initdata = {};
        
        createIBSheet2(document.getElementById("sheet1"), "mySheet1", "1060px", "300px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호|요청번호", Type: "Text", Width: 0, SaveName: "requestNo", Hidden:true },            
            { Header: "성명|성명", Type: "Text", Width: 80, SaveName: "userNm", Align: "Center" },
            { Header: "생년월일|생년월일", Type: "Text", Width: 80, SaveName: "resNo", Align: "Center"},  
            { Header: "기본교육|기본교육", Type: "Text", Width: 200, SaveName: "basicEdu", Align: "Center" },          
            { Header: "최초교육|설계시공", Type: "Text", Width: 200, SaveName: "planEdu", Align: "Center"},
            { Header: "최초교육|건설사업관리", Type: "Text", Width: 200, SaveName: "businessEdu", Align: "Center" },
            { Header: "최초교육|품질관리", Type: "Text", Width: 200, SaveName: "qualityEdu", Align: "Center" },
            { Header: "비고|비고", Type: "Text", Width: 80, SaveName: "etc", Align: "Center"}           
        ];

        IBS_InitSheet(mySheet1, initdata);
        mySheet1.SetEditable(0);
        mySheet1.SetEditableColorDiff(0);
        mySheet1.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet1.LoadSearchData(pListDatas);
    }
	
    // 품질관리 현재등급 및 승급교육후 등급 그리드 생성 함수
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
            { Header: "현재등급", Type: "Text", Width: 180, SaveName: "ranking", Align: "Center" },          
            { Header: "현재역량지수", Type: "Text", Width: 180, SaveName: "rankingRate", Align: "Center"},
            { Header: "승급교육 이수 후 등급", Type: "Text", Width: 180, SaveName: "afterRanking", Align: "Center" },
            { Header: "비고", Type: "Text", Width: 340, SaveName: "etc", Align: "Center" }
        ];

        IBS_InitSheet(mySheet2, initdata);
        mySheet2.SetEditable(0);
        mySheet2.SetEditableColorDiff(0);
        mySheet2.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet2.LoadSearchData(pListDatas);
    }
    
    // 품질관리 계속교육 이수자 그리드 생성 함수
    function makeGrid3(pListDatas) {
    	commonFunc.initSheet("mySheet3");

        var initdata = {};
        
        createIBSheet2(document.getElementById("sheet3"), "mySheet3", "1060px", "300px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 0, SaveName: "requestNo", Hidden:true },            
            { Header: "성명", Type: "Text", Width: 80, SaveName: "userNm", Align: "Center" },
            { Header: "생년월일", Type: "Text", Width: 80, SaveName: "resNo", Align: "Center"},  
            { Header: "차수", Type: "Float", Width: 60, SaveName: "phase", Align: "Center" },          
            { Header: "기산일", Type: "Text", Width: 80, SaveName: "initDate", Align: "Center"},
            { Header: "수행일", Type: "Text", Width: 60, SaveName: "runDays", Align: "Center" },
            { Header: "3년되는날", Type: "Text", Width: 80, SaveName: "threeYearDate", Align: "Center" },
            { Header: "3년되는날\n전 6개월", Type: "Text", Width: 80, SaveName: "beforeThreeYearDate", Align: "Center" },
            { Header: "3년되는날\n후 6개월", Type: "Text", Width: 80, SaveName: "afterThreeYearDate", Align: "Center" },
            { Header: "이수/교육종류", Type: "Text", Width: 80, SaveName: "eduType", Align: "Center" },
            { Header: "교육시간", Type: "Text", Width: 60, SaveName: "eduTimes", Align: "Center" },
            { Header: "연기일", Type: "Text", Width: 80, SaveName: "delayDate", Align: "Center" },
            { Header: "이수일", Type: "Text", Width: 80, SaveName: "passDate", Align: "Center" },
            { Header: "이수시간", Type: "Text", Width: 60, SaveName: "passTimes", Align: "Center" },
            { Header: "접수일", Type: "Text", Width: 80, SaveName: "receiptDate", Align: "Center" }
        ];

        IBS_InitSheet(mySheet3, initdata);
        mySheet3.SetEditable(0);
        mySheet3.SetEditableColorDiff(0);
        mySheet3.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet3.LoadSearchData(pListDatas);
    }
    
    // 품질관리 계속교육 미이수자 그리드 생성 함수
    function makeGrid4(pListDatas) {
    	commonFunc.initSheet("mySheet4");

        var initdata = {};
        
        createIBSheet2(document.getElementById("sheet4"), "mySheet4", "1060px", "300px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 0, SaveName: "requestNo", Hidden:true },            
            { Header: "성명", Type: "Text", Width: 80, SaveName: "userNm", Align: "Center" },
            { Header: "생년월일", Type: "Text", Width: 80, SaveName: "resNo", Align: "Center"},  
            { Header: "차수", Type: "Float", Width: 60, SaveName: "phase", Align: "Center" },          
            { Header: "기산일", Type: "Text", Width: 80, SaveName: "initDate", Align: "Center"},
            { Header: "수행일", Type: "Text", Width: 60, SaveName: "runDays", Align: "Center" },
            { Header: "3년되는날", Type: "Text", Width: 80, SaveName: "threeYearDate", Align: "Center" },
            { Header: "3년되는날\n전 6개월", Type: "Text", Width: 80, SaveName: "beforeThreeYearDate", Align: "Center" },
            { Header: "3년되는날\n후 6개월", Type: "Text", Width: 80, SaveName: "afterThreeYearDate", Align: "Center" },
            { Header: "이수/교육종류", Type: "Text", Width: 80, SaveName: "eduType", Align: "Center" },
            { Header: "교육시간", Type: "Text", Width: 60, SaveName: "eduTimes", Align: "Center" },
            { Header: "연기일", Type: "Text", Width: 80, SaveName: "delayDate", Align: "Center" },
            { Header: "이수일", Type: "Text", Width: 80, SaveName: "passDate", Align: "Center" },
            { Header: "이수시간", Type: "Text", Width: 60, SaveName: "passTimes", Align: "Center" },
            { Header: "접수일", Type: "Text", Width: 80, SaveName: "receiptDate", Align: "Center" }
        ];

        IBS_InitSheet(mySheet4, initdata);
        mySheet4.SetEditable(0);
        mySheet4.SetEditableColorDiff(0);
        mySheet4.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet4.LoadSearchData(pListDatas);
    }
    
    // 기술자 경력 내역 그리드 생성 함수
    function makeGrid5(pListDatas) {
    	commonFunc.initSheet("mySheet5");

        var initdata = {};
        
        createIBSheet2(document.getElementById("sheet5"), "mySheet5", "1060px", "300px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 0, SaveName: "requestNo", Hidden:true },            
            { Header: "사번", Type: "Text", Width: 80, SaveName: "userId", Align: "Center" },
            { Header: "성명", Type: "Text", Width: 80, SaveName: "userNm", Align: "Center"},  
            { Header: "순번", Type: "Float", Width: 40, SaveName: "Seq", Align: "Center"},  
            { Header: "생년월일", Type: "Text", Width: 80, SaveName: "resNo", Align: "Center" },          
            { Header: "업체명", Type: "Text", Width: 140, SaveName: "vendorNm", Align: "Center"},
            { Header: "참여기간", Type: "Text", Width: 160, SaveName: "playDateTerm", Align: "Center" },
            { Header: "인정일", Type: "Text", Width: 60, SaveName: "approveDays", Align: "Center" },
            { Header: "사업명", Type: "Text", Width: 200, SaveName: "constNm" },
            { Header: "발주처명", Type: "Text", Width: 180, SaveName: "orderVendorNm", Align: "Center" },
            { Header: "공사구분", Type: "Text", Width: 140, SaveName: "constType", Align: "Center" },
            { Header: "공법", Type: "Text", Width: 80, SaveName: "constMethod", Align: "Center" },
            { Header: "직무분야", Type: "Text", Width: 60, SaveName: "jobKind", Align: "Center" },
            { Header: "전문분야", Type: "Text", Width: 80, SaveName: "specialKind", Align: "Center" },
            { Header: "담당업무", Type: "Text", Width: 100, SaveName: "assignWork", Align: "Center" },
            { Header: "직위", Type: "Text", Width: 60, SaveName: "titleNm", Align: "Center" },
            { Header: "신고구분", Type: "Text", Width: 60, SaveName: "reportType", Align: "Center" },
            { Header: "기술인정보\n존재여부", Type: "Text", Width: 100, SaveName: "engineerExistType", Align: "Center" },
        ];

        IBS_InitSheet(mySheet5, initdata);
        mySheet5.SetEditable(0);
        mySheet5.SetEditableColorDiff(0);
        mySheet5.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet5.LoadSearchData(pListDatas);
    }
	
</script>