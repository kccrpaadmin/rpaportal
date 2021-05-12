<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<!-- 차트영역 -->
    	<div class="chart_box">
			<div class=chart_header>웹크롤링 - 운영현황</div>
			<div>
				<div></div>
			</div>
		</div>
		<!-- 차트영역 -->
    	<div class="chart_box margin_left_20">
			<div class=chart_header>웹크롤링 - 년간 부서별 업무 절감시간</div>
			<div id="chartHolder1"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
	
	// 페이지 로드 
	$(document).ready(function (e) {
		$("body").css("background-color", "#f5f5fb");
		listDeptRunTime();
	});

	// 년간 부서별 업무 절감시간 목록 조회
	function listDeptRunTime() {
		$.ajax({
			url: "/AjaxMenu/ListDeptRunTime.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
		    dataType : "json",
	        async: true,
			success: function(datas) {
				make2DColumnChart("chart1", "chartHolder1", "500px", "200px", datas, "", "subjectStyleType1", "단위(시간)", "deptNm", "");
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
			'<rMateChart backgroundColor="#FAFAFA" borderStyle="none">'
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
								+'<SolidColor color="#03a9f5" alpha="0.7"/>'
								+'<SolidColor color="#fd6783" alpha="0.7"/>'
								+'<SolidColor color="#fabc05" alpha="0.7"/>'
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