<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<div class="home_box">
			<div class="home_header">
				<div class="home_header_left">
					<div class="home_title">공지사항</div>
					<a class="home_notice_content" href="/Ocr/ListMenu.do">RPA 오픈 안내</a>
				</div>
				<div class="home_header_right">
					<div class="home_title">OCR</div>
					<a class="home_ocr_content" href="/Ocr/ListMenu.do">텍스트 추출 바로가기</a>
				</div>
			</div>
			<div class="home_body">
				<div class="home_body_left">
					<div class="home_title">웹크롤링 과제현황</div>
					<div class="home_task1">
						<div class="home_represent_menu_left">
							<div class="home_represent_top" id="crawl_dept_nm"></div>
							<div class="home_represent_middle" id="crawl_menu_nm"></div>
							<div class="home_represent_middle" id="crawl_time"></div>
							<div class="home_represent_bottom" id="crawl_content"></div>
						</div>
						<div class="home_represent_menu_right">
							<a href="/Crawl/ListMenu.do"><img src="/resources/imgs/button/btn_home_more.png" /></a>
						</div>
					</div>
				</div>
				<div class="home_body_center">
					<div class="home_title">웹크롤링 타임라인</div>
					<div class="home_task2">
						<div class="home_time_line_box" id="home_time_line_box_crawl"></div>
					</div>
				</div>
				<div class="home_body_right">
					<div class="home_title">웹크롤링 사용현황</div>
					<div class="home_task2">
						<div id="chartHolder1"></div>
					</div>
				</div>
			</div>
			<div class="home_body">
				<div class="home_body_left">
					<div class="home_title">BOT 과제현황</div>
					<div class="home_task1">
						<div class="home_represent_menu_left">
							<div class="home_represent_top" id="bot_dept_nm"></div>
							<div class="home_represent_middle" id="bot_menu_nm"></div>
							<div class="home_represent_middle" id="bot_time"></div>
							<div class="home_represent_bottom" id="bot_content"></div>
						</div>
						<div class="home_represent_menu_right">
							<a href="/Bot/ListMenu.do"><img src="/resources/imgs/button/btn_home_more.png" /></a>
						</div>
					</div>
				</div>
				<div class="home_body_center">
					<div class="home_title">BOT 타임라인</div>
					<div class="home_task2">
						<div class="home_time_line_box" id="home_time_line_box_bot"></div>
					</div>
				</div>
				<div class="home_body_right">
					<div class="home_title">BOT 사용현황</div>
					<div class="home_task2">
						<div id="chartHolder2"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	
	// 페이지 로드 
	$(document).ready(function (e) {
		$("body").css("background-color", "#f5f5fb");
		getCrawlMenu("RA002003", commonFunc.certInfo.empNo);
		getBotMenu("RA004001", commonFunc.certInfo.empNo);
		listTimeLineCrawl("RA002001");
		listTimeLineBot("RA002003");
		listDeptRunTimeCrawl("RA002001");
		listDeptRunTimeBot("RA002003");
	});
	
	// 웹크롤링 메뉴 정보 상세 조회
	function getCrawlMenu(pMenuId, pEmpNo) {
		$.ajax({
			url: "/AjaxMenu/GetCrawlMenu.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId, "empNo": pEmpNo }),
		    dataType : "json",
	        async: false,
			success: function(data) {
				setCrawlMenu(data);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 봇 메뉴 정보 상세 조회
	function getBotMenu(pMenuId, pEmpNo) {
		$.ajax({
			url: "/AjaxMenu/GetBotMenu.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId, "empNo": pEmpNo }),
		    dataType : "json",
	        async: false,
			success: function(data) {
				setBotMenu(data);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 웹크롤링 홈화면 타임라인 목록 조회
	function listTimeLineCrawl(pWorkTypeCd) {
		$.ajax({
			url: "/AjaxHome/ListTimeLine.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "workTypeCd": pWorkTypeCd }),
		    dataType : "json",
	        async: false,
			success: function(datas) {
				commonFunc.createHomeTimeLineList("home_time_line_box_crawl", datas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 봇 홈화면 타임라인 목록 조회
	function listTimeLineBot(pWorkTypeCd) {
		$.ajax({
			url: "/AjaxHome/ListTimeLine.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "workTypeCd": pWorkTypeCd }),
		    dataType : "json",
	        async: false,
			success: function(datas) {
				commonFunc.createHomeTimeLineList("home_time_line_box_bot", datas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 웹크롤링 홈화면 절감시간 목록 조회
	function listDeptRunTimeCrawl(pWorkTypeCd) {
		$.ajax({
			url: "/AjaxHome/ListDeptRunTime.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "workTypeCd": pWorkTypeCd }),
		    dataType : "json",
	        async: false,
			success: function(datas) {
				make2DColumnChart("chart1", "chartHolder1", "450px", "240px", datas, "", "subjectStyleType1", "단위(시간)", "deptNm", "");
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 봇 홈화면 절감시간 목록 조회
	function listDeptRunTimeBot(pWorkTypeCd) {
		$.ajax({
			url: "/AjaxHome/ListDeptRunTime.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "workTypeCd": pWorkTypeCd }),
		    dataType : "json",
	        async: false,
			success: function(datas) {
				make2DColumnChart("chart2", "chartHolder2", "450px", "240px", datas, "", "subjectStyleType1", "단위(시간)", "deptNm", "");
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 웹클롤링 과제현황 데이터 변경
	function setCrawlMenu(pData) {
		$("#crawl_dept_nm").text("[" + pData.deptNm + "]");
		$("#crawl_menu_nm").text(pData.menuNm);
		$("#crawl_time").text(pData.timeTypeNm + " / " + pData.runSeq + " / " + pData.runTime);
		$("#crawl_content").text(pData.content);		
	}
	
	// 봇 과제현황 데이터 변경
	function setBotMenu(pData) {
		$("#bot_dept_nm").text("[" + pData.deptNm + "]");
		$("#bot_menu_nm").text(pData.menuNm);
		$("#bot_time").text(pData.timeTypeNm + " / " + pData.runSeq + " / " + pData.runTime);
		$("#bot_content").text(pData.content);
	}
	
	// 홈화면 절감시간 차트 생성 함수
	function make2DColumnChart(pChartNm, pChartElmtNm, pWidth, pHeight, pDatas, pSubject, pSubjectStyle, pUnit, pCategoryField, pLabelRotation) {
		rMateChartH5.create(pChartNm, pChartElmtNm, "", pWidth, pHeight);
		var layoutStr =
			'<rMateChart backgroundColor="#ffffff" borderStyle="none">'
				+'<Options>'
					+'<Caption text="' + pSubject + '" styleName="' + pSubjectStyle + '"/>'
		            +'<SubCaption text="' + pUnit + '" textAlign="right"/>'
				+'</Options>'
				+'<NumberFormatter id="numFmt" useThousandsSeparator="true"/>'
				+'<Column2DChart showDataTips="true" selectionMode="multiple" columnWidthRatio="0.4" columnWidthMinRatio="0.1" maxColumnWidth="12">'
					+'<horizontalAxis>'
						+'<CategoryAxis id="hAxis" categoryField="' + pCategoryField +'"/>'
					+'</horizontalAxis>'
					+'<verticalAxis>'
						+'<LinearAxis formatter="{numFmt}"/>'
					+'</verticalAxis>'
					+'<series>'
						+'<Column2DSeries yField="deptRunTime" displayName="" fillJsFunction="" labelPosition="outside">'
							+'<showDataEffect>'
								+'<SeriesInterpolate/>'
							+'</showDataEffect>'
							+'<fills>'
								+'<SolidColor color="#ff0078" alpha="0.7"/>'
								+'<SolidColor color="#6200eb" alpha="0.7"/>'
								+'<SolidColor color="#007cce" alpha="0.7"/>'
								+'<SolidColor color="#05d1ce" alpha="0.7"/>'
								+'<SolidColor color="#ff8f29" alpha="0.7"/>'
								+'<SolidColor color="#ff4956" alpha="0.7"/>'
								+'<SolidColor color="#ff0078" alpha="0.7"/>'
								+'<SolidColor color="#6200eb" alpha="0.7"/>'
							+'</fills>'
						+'</Column2DSeries>'
					+'</series>'
				  	+'<horizontalAxisRenderers>'
	                	+'<Axis2DRenderer axis="{hAxis}" labelRotation="' + pLabelRotation + '"/>'
	              	+'</horizontalAxisRenderers>'
	              	+'<backgroundElements>'
						+'<GridLines/>'
						+'<AxisMarker>'
							+'<lines>'
								+'<AxisLine value="0" lineStyle="normal">'
									+'<stroke>'
										+'<Stroke color="#dddddd" weight="1"/>'
									+'</stroke>'
								+'</AxisLine>'
							+'</lines>'
						+'</AxisMarker>'
					+'</backgroundElements>'
				+'</Column2DChart>'
			 	+'<Style>'
			 		+'.subjectStyleType1{fontSize:15;fontFamily:dotum;fontWeight:bold;color:#5D5D5D;}'
	         	+'</Style>'
			+'</rMateChart>';
		
		rMateChartH5.calls(pChartNm, {
		    "setLayout" : layoutStr,
		    "setData" : pDatas
		});
	}	
	
</script>