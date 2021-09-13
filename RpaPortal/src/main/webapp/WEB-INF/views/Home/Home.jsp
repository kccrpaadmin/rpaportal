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
					<div class="home_notice_content">RPA 오픈 안내</div>
				</div>
				<div class="home_header_right">
					<div class="home_title">OCR</div>
					<div class="home_ocr_content">텍스트 추출 바로가기</div>
				</div>
			</div>
			<div class="home_body">
				<div class="home_body_left">
					<div class="home_title">웹크롤링 운영현황</div>
					<div class="home_task"></div>
				</div>
				<div class="home_body_center">
					<div class="home_title">웹크롤링 타임라인</div>
					<div class="home_task"></div>
				</div>
				<div class="home_body_right">
					<div class="home_title">웹크롤링 사용현황</div>
					<div class="home_task">
						<div id="chartHolder1"></div>
					</div>
				</div>
			</div>
			<div class="home_body">
				<div class="home_body_left">
					<div class="home_title">BOT 운영현황</div>
					<div class="home_task"></div>
				</div>
				<div class="home_body_center">
					<div class="home_title">BOT 타임라인</div>
					<div class="home_task"></div>
				</div>
				<div class="home_body_right">
					<div class="home_title">BOT 사용현황</div>
					<div class="home_task">
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
		listDeptRunTimeWeb("RA002");
		listDeptRunTimeBot("RA004");
	});
	
	
	// 년간 부서별 업무 절감시간 목록 조회
	function listDeptRunTimeWeb(pUpMenuId) {
		$.ajax({
			url: "/AjaxHome/ListDeptRunTime.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "upMenuId": pUpMenuId }),
		    dataType : "json",
	        async: true,
			success: function(datas) {
				make2DColumnChart("chart1", "chartHolder1", "450px", "240px", datas, "", "subjectStyleType1", "단위(시간)", "deptNm", "");
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 년간 부서별 업무 절감시간 목록 조회
	function listDeptRunTimeBot(pUpMenuId) {
		$.ajax({
			url: "/AjaxHome/ListDeptRunTime.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "upMenuId": pUpMenuId }),
		    dataType : "json",
	        async: true,
			success: function(datas) {
				make2DColumnChart("chart2", "chartHolder2", "450px", "240px", datas, "", "subjectStyleType1", "단위(시간)", "deptNm", "");
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 년간 부서별 업무 절감시간 생성 함수
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
										+'<Stroke color="#f15151" weight="1"/>'
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