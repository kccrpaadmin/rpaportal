<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
    <meta http-equiv="Contents-Type" content="text/html" charset="utf-8" />
   	<link rel="shortcut icon" type="text/css" href="/resources/imgs/favicon/kccenc.ico" />
    <link rel="stylesheet" type="text/css" href="/resources/css/font/nanumsquare.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/rpa.css?ver=0.1" />
    <link rel="stylesheet" type="text/css" href="/resources/js/library/seeddialog/seeddialog.min.css" />
    <link rel="stylesheet" type="text/css" href="/resources/js/library/seedmodal/seedmodal.min.css" />
    <link rel="stylesheet" type="text/css" href="/resources/js/library/seedprogress/seedprogress.min.css" />
    <link rel="stylesheet" type="text/css" href="/resources/js/jquery/jquery-ui-1.12.1/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="/resources/js/library/rMateChartH5Web_v6.0/rMateChartH5/Assets/Css/rMateChartH5.css"/>

	<script type="text/javascript" src="/resources/js/jquery/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="/resources/js/jquery/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/resources/js/library/seeddialog/seeddialog.min.js"></script>
	<script type="text/javascript" src="/resources/js/library/seedmodal/seedmodal.min.js"></script>
	<script type="text/javascript" src="/resources/js/library/seedprogress/seedprogress.min.js"></script>
	<script type="text/javascript" src="/resources/js/custom-func.js"></script>
	<script type="text/javascript" src="/resources/js/library-func.js"></script>
	<script type="text/javascript" src="/resources/js/common-func.js"></script>
	
	<script type="text/javascript" src="/resources/js/library/rMateChartH5Web_v6.0/LicenseKey/rMateChartH5License.js"></script>  
	<script type="text/javascript" src="/resources/js/library/rMateChartH5Web_v6.0/rMateChartH5/JS/rMateChartH5.js"></script>
	<script type="text/javascript" src="/resources/js/library/ibsheet/dev/ibleaders.js"></script>
    <script type="text/javascript" src="/resources/js/library/ibsheet/dev/ibsheetinfo.js"></script>
    <script type="text/javascript" src="/resources/js/library/ibsheet/dev/ibsheet.js"></script>
</head>
<body>
	<!-- CustumViewPreparer 결과값 받기 위해 필요 -->
	<tiles:importAttribute name="certInfo" />
	
	<script type="text/javascript">
		
		var libraryFunc;
		var commonFunc;
		
		// 페이지 로드
        $(document).ready(function (e) {
            libraryFunc = new LibraryFunc();
            commonFunc = new CommonFunc();
            // "" 없는 데이터는 문법 오류 발생, 이스케이프 문자 처리 가능
            commonFunc.setCertInfo(<c:out value="${certInfo}" escapeXml="false" />);
           	reSizeHeightMobile();
	    });
       	
       	// Ajax 시작시  함수
       	$(document).ajaxStart(function() {
       		libraryFunc.createProgress();
       	});
       	
       	// Ajax 종료시 함수
       	$(document).ajaxStop(function() {
       		libraryFunc.closeProgress();
       	});
           	
		// 메뉴 열기
		$(document).on("click", "#btn_lnb_menu_open", function (e) {
			openAndCloseLnb("Open");
		});
		
		// 메뉴 닫기
		$(document).on("click", "#btn_lnb_menu_close", function (e) {
			choiceLnbMenu("x");
			openAndCloseLnb("Close");
		});
		
		// 메뉴바 영역 버튼으로 메뉴 열기
		$(document).on("click", ".btn_lnb_bar", function (e) {
			var classNm = $(this).attr("class");
			var classNmLen = classNm.length;
			var type = classNm.substring(classNmLen - 1, classNmLen);
			
			openAndCloseLnb("Open");
			choiceLnbMenu(type);
		});
		
		// 메뉴 영역 버튼으로 메뉴 열기
		$(document).on("click", ".btn_lnb_menu", function (e) {
			var classNm = $(this).attr("class");
			var type = classNm.substring(26, 27);
			
			choiceLnbMenu(type);
		});
		
		// 메뉴 열고 닫기 함수
		function openAndCloseLnb(pType) {
			var type = pType; 
			
			if (type == "Open") {
				$("#lnb").css("width", "0");
				$("#lnb_menu").css("width", "210px");
			} 
			else {
				$("#lnb_menu").css("width", "0");
				$("#lnb").css("width", "80px");
			}
		}
		
		// 선택 메뉴 하위 메뉴 활성화 함수
		function choiceLnbMenu(pType) {
			var type = pType;
			
			// 소메뉴 영역 보이게 처리
			$(".lnb_sub_menu_box").css("display", "none");
			
			// 대메뉴 파란색(b) 스타일로 변경
			var lnbMenus = $(".btn_lnb_menu");
			var lnbMenusLen = lnbMenus.length;
			
			for (var i = 0; i < lnbMenusLen; i++) {
				var lnbMenu = lnbMenus[i];
				var oriClassNm = $(lnbMenu).attr("class");
				var oriClassNmLen = oriClassNm.length - 1;
				var chgClassNm = oriClassNm.substring(0, oriClassNmLen) + "b";
				$(lnbMenu).attr("class", chgClassNm);
			}
	
			// 사용자가 선택한 메뉴 찾기
			for (var i = 0; i < lnbMenusLen; i++) {
				var lnbMenu = lnbMenus[i];
				var oriClassNm = $(lnbMenu).attr("class");
				var btnType = oriClassNm.substring(26, 27);
				
				if (type == btnType) {
					var oriClassNmLen = oriClassNm.length - 1;
					var chgClassNm = oriClassNm.substring(0, oriClassNmLen) + "w";
					
					// 대메뉴 하얀색(w) 스타일로 변경
					$(lnbMenu).attr("class", chgClassNm);
					
					// 소메뉴 영역 보이게 처리
					$(lnbMenu).next().slideDown(500);;
				}
			}
		}
		
		// 모바일 높이 변경 (아이패드 크롬의 경우 너비를 못 맞추는 증상)  
		function reSizeHeightMobile() {
	  		if (commonFunc.getDeviceType() == "Mobile") {
          		$("#container").height(3000);
          	}
		}
		
	</script>
	
	<div id="wrap">
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
	</div>
</body>
</html>