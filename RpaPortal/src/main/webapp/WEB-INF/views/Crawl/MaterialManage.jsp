<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<!-- 로케이션 -->
		<div class="location_box">
			<div class="location_left">
				<div class="location_title">${outMenuVO.menuNm}</div>	
			</div>
			<div class="location_right">
				<span class="location_home">홈</span>
				<span class="location_arrow">웹크롤링</span>
				<span class="location_arrow">업무관리</span>
			</div>
		</div>
		<!-- 제목 -->
		<div class="title">상세정보</div>
		<!-- 검색영역 -->
	    <div class="search_dtl_box">
	        <table class="search_dtl_tbl">
	            <caption>검색영역</caption>
	            <colgroup>
	                <col style="width:8%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                	<th class="search_dtl_th">기준년월</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="datepicker_ym" readonly="readonly"  id="start_date" value="${startDate}" />
	                        ~
	                        <input type="text" class="datepicker_ym" readonly="readonly"  id="end_date" value="${endDate}" />
	                    </td>
	                </tr>
	            </tbody>
	        </table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_search">조회</a>
	    </div>
	    <!-- 차트영역 -->
    	<div class="chart_box">
			<div class=chart_header>철스크랩 가격 변동 현황</div>
			<div id="chartHolder1"></div>
		</div>
		<!-- 차트영역 -->
    	<div class="chart_box">
			<div class=chart_header>가격 지수 변동 현황</div>
			<div id="chartHolder2"></div>
		</div>
   	    <!-- 버튼영역 -->
	    <div class="btn_box" style="float:left;width:100%">
	    	<a href="/Crawl/ListMenu.do"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${outMenuVO.menuId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		commonFunc.createDatepicker(".datepicker_ym", "YearMonth");
		listMaterialManage("RA014007", commonFunc.setReplaceData($("#start_date").val(), "-", ""), commonFunc.setReplaceData($("#end_date").val(), "-", ""));
	});
	
	// 원자재정보 목록 조회
	function listMaterialManage(pMaterialTypeCd, pStartDate, pEndDate) {
		$.ajax({
			url: "/AjaxCrawl/ListMaterialManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "materialTypeCd": pMaterialTypeCd, "startDate": pStartDate, "endDate": pEndDate }),
		    dataType : "json",
	        async: true,
			success: function(datas) {
				make2DLineChartSteel("chart1", "chartHolder1", "1078px", "400px", datas.outListCrawlMaterialSteelScrapVO, "", "subjectStyleType1", "단위(달러)", "standardDate", "false");
				make2DLineChart("chart2", "chartHolder2", "1078px", "400px", datas.outListCrawlMaterialSteelScrapAndRebarVO, "", "subjectStyleType1", "단위(%)", "standardDate", "false");
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 원자재정보 철스크랩 차트 생성
	function make2DLineChartSteel(pChartNm, pChartElmtNm, pWidth, pHeight, pDatas, pSubject, pSubjectStyle, pUnit, pCategoryField, pButtonMode) {
		rMateChartH5.create(pChartNm, pChartElmtNm, "", pWidth, pHeight);
		var layoutStr =
			'<rMateChart backgroundColor="#FAFAFA" borderStyle="none">'
				+'<Options>'
					+'<Caption text="' + pSubject + '" styleName="' + pSubjectStyle + '"/>'
		            +'<SubCaption text="' + pUnit + '" textAlign="right"/>'
		            +'<Legend useVisibleCheck="true"/>'
				+'</Options>'
				+'<NumberFormatter id="numFmt" useThousandsSeparator="true" precision="2"/>'
				+'<Line2DChart showDataTips="true" dataTipDisplayMode="axis" paddingBottom="25" buttonMode="' + pButtonMode + '">'
					+'<horizontalAxis>'
						+'<CategoryAxis categoryField="' + pCategoryField + '"/>'
					+'</horizontalAxis>'
					+'<verticalAxis>'
						+'<LinearAxis formatter="{numFmt}"/>'					
					+'</verticalAxis>'
					+'<series>'
						+'<Line2DSeries yField="amt" displayName="철스크랩" labelPosition="up" itemRenderer="CircleItemRenderer">'
							+'<showDataEffect>'
								+'<SeriesInterpolate/>'
							+'</showDataEffect>'
							+'<lineStroke>'
								+'<Stroke color="#ea4335" weight="2"/>'
							+'</lineStroke>'
						+'</Line2DSeries>'
					+'</series>'
					+'<annotationElements>'
					 	+'<CrossRangeZoomer zoomType="horizontal" verticalLabelPlacement="bottom" horizontalLabelPlacement="left" enableZooming="true" enableCrossHair="true">'
			            +'</CrossRangeZoomer>'
		            +'</annotationElements>'
		            +'<backgroundElements>'
						+'<GridLines/>'
						+'<AxisMarker>'
							+'<lines>'
								+'<AxisLine value="0" lineStyle="normal">'
									+'<stroke>'
										+'<Stroke color="#FF7171" weight="1"/>'
									+'</stroke>'
								+'</AxisLine>'
							+'</lines>'
						+'</AxisMarker>'
					+'</backgroundElements>'
				+'</Line2DChart>'
				+'<Style>'
					+'.subjectStyleType1{fontSize:15;fontFamily:dotum;fontWeight:bold;color:#5D5D5D;}'
		     	+'</Style>'
			+'</rMateChart>';
		
		rMateChartH5.calls(pChartNm, {
		    "setLayout" : layoutStr,
		    "setData" : pDatas
		});
	}
	
	function make2DLineChart(pChartNm, pChartElmtNm, pWidth, pHeight, pDatas, pSubject, pSubjectStyle, pUnit, pCategoryField, pButtonMode) {
		rMateChartH5.create(pChartNm, pChartElmtNm, "", pWidth, pHeight);
		var layoutStr =
			'<rMateChart backgroundColor="#FAFAFA" borderStyle="none">'
				+'<Options>'
					+'<Caption text="' + pSubject + '" styleName="' + pSubjectStyle + '"/>'
		            +'<SubCaption text="' + pUnit + '" textAlign="right"/>'
		            +'<Legend useVisibleCheck="true"/>'
				+'</Options>'
				+'<NumberFormatter id="numFmt" useThousandsSeparator="true"/>'
				+'<Line2DChart showDataTips="true" dataTipDisplayMode="axis" paddingBottom="25" buttonMode="' + pButtonMode + '" itemClickJsFunction="itemClickJsFunc">'
					+'<horizontalAxis>'
						+'<CategoryAxis categoryField="' + pCategoryField + '"/>'
					+'</horizontalAxis>'
					+'<verticalAxis>'
						+'<LinearAxis formatter="{numFmt}"/>'					
					+'</verticalAxis>'
					+'<series>'
						+'<Line2DSeries yField="scrapRate" displayName="철스크랩" fillJsFunction="" labelPosition="" itemRenderer="RectangleItemRenderer">'
							+'<showDataEffect>'
								+'<SeriesInterpolate/>'
							+'</showDataEffect>'
							+'<lineStroke>'
								+'<Stroke color="#ea4335" weight="2"/>'
							+'</lineStroke>'
						+'</Line2DSeries>'
						+'<Line2DSeries yField="rebarRate" displayName="철근" fillJsFunction="" labelPosition="" itemRenderer="CircleItemRenderer">'
							+'<showDataEffect>'
								+'<SeriesInterpolate/>'
							+'</showDataEffect>'
							+'<lineStroke>'
								+'<Stroke color="#7e30eb" weight="2"/>'
							+'</lineStroke>'
						+'</Line2DSeries>'
					+'</series>'
					+'<annotationElements>'
					 	+'<CrossRangeZoomer zoomType="horizontal" verticalLabelPlacement="bottom" horizontalLabelPlacement="left" enableZooming="true" enableCrossHair="true">'
			            +'</CrossRangeZoomer>'
		            +'</annotationElements>'
		            +'<backgroundElements>'
						+'<GridLines/>'
						+'<AxisMarker>'
							+'<lines>'
								+'<AxisLine value="0" lineStyle="normal">'
									+'<stroke>'
										+'<Stroke color="#FF7171" weight="1"/>'
									+'</stroke>'
								+'</AxisLine>'
							+'</lines>'
						+'</AxisMarker>'
					+'</backgroundElements>'
				+'</Line2DChart>'
				+'<Style>'
					+'.subjectStyleType1{fontSize:15;fontFamily:dotum;fontWeight:bold;color:#5D5D5D;}'
		     	+'</Style>'
			+'</rMateChart>';
		
		rMateChartH5.calls(pChartNm, {
		    "setLayout" : layoutStr,
		    "setData" : pDatas
		});
	}
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {
		listMaterialManage("RA014007", commonFunc.setReplaceData($("#start_date").val(), "-", ""), commonFunc.setReplaceData($("#end_date").val(), "-", ""));
	});
	
</script>