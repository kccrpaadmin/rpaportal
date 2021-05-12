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
	                	<th class="search_dtl_th">기준일자</th>
	                    <td class="search_dtl_td">
	                        <input type="text" class="datepicker_ym" readonly="readonly"  id="standard_date" value="${standardDate}" />
	                    </td>
	                </tr>
	            </tbody>
	        </table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common" id="btn_search">조회</a>
	    </div>
	    <!-- 차트영역 -->
    	<div class="chart_box">
			<div class=chart_header>동 가격 변동 현황</div>
			<div id="chartHolder1"></div>
		</div>
		<!-- 차트영역 -->
    	<div class="chart_box">
			<div class=chart_header>환율 변동 현황</div>
			<div id="chartHolder2"></div>
		</div>
		<!-- 차트영역 -->
    	<div class="chart_box">
			<div class=chart_header>전월 대비 금액 현황</div>
			<div id="chartHolder3"></div>
		</div>
	    <!-- 그리드영역 -->
		<div id="sheet" class="margin_top_20 margin_left_20" style="float:left;"></div>
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
		listKomisManage(commonFunc.setReplaceData($("#standard_date").val(), "-", ""));
	});
	
	// 동관 및 환율정보 데이터 조회
	function listKomisManage(pYearMon) {
		$.ajax({
			url: "/AjaxCrawl/ListKomisManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "yearMon": pYearMon }),
		    dataType : "json",
	        async: true,
			success: function(datas) {
				make2DLineChartCopper("chart1", "chartHolder1", "1078px", "400px", datas.outListCrawlKomisVO.data, "", "subjectStyleType1", "단위(달러)", "standardDate", "false", datas.outMinOrMaxData.minCopperAmt, datas.outMinOrMaxData.maxCopperAmt);
				make2DLineChartExchange("chart2", "chartHolder2", "1078px", "400px", datas.outListCrawlKomisVO.data, "", "subjectStyleType1", "단위(환율)", "standardDate", "false", datas.outMinOrMaxData.minExchangeAmt, datas.outMinOrMaxData.maxExchangeAmt);
				make2DColumnChart("chart3", "chartHolder3", "530px", "400px", datas.outListCrawlKomisVOAverage, "", "subjectStyleType1", "단위(원)", "sortType", "");
				makeGrid(datas.outListCrawlKomisVO);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 동 가격 변동현황 차트 생성 함수
	function make2DLineChartCopper(pChartNm, pChartElmtNm, pWidth, pHeight, pDatas, pSubject, pSubjectStyle, pUnit, pCategoryField, pButtonMode, pMiniMum, pMaxiMum) {
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
						+'<LinearAxis formatter="{numFmt}" minimum="' + pMiniMum + '" maximum="' + pMaxiMum + '"/>'					
					+'</verticalAxis>'
					+'<series>'
						+'<Line2DSeries yField="copperAmt" displayName="동가격" labelPosition="up" itemRenderer="CircleItemRenderer">'
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
	
	// 환율 변동현황 차트 생성 함수
	function make2DLineChartExchange(pChartNm, pChartElmtNm, pWidth, pHeight, pDatas, pSubject, pSubjectStyle, pUnit, pCategoryField, pButtonMode, pMiniMum, pMaxiMum) {
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
						+'<LinearAxis formatter="{numFmt}" minimum="' + pMiniMum + '" maximum="' + pMaxiMum + '"/>'					
					+'</verticalAxis>'
					+'<series>'
						+'<Line2DSeries yField="exchangeAmt" displayName="환율" labelPosition="up" itemRenderer="CircleItemRenderer">'
							+'<showDataEffect>'
								+'<SeriesInterpolate/>'
							+'</showDataEffect>'
							+'<lineStroke>'
								+'<Stroke color="#4285f4" weight="2"/>'
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
	
	// 전월 대비 금액 현황 생성 함수
	function make2DColumnChart(pChartNm, pChartElmtNm, pWidth, pHeight, pDatas, pSubject, pSubjectStyle, pUnit, pCategoryField, pLabelRotation) {
		rMateChartH5.create(pChartNm, pChartElmtNm, "", pWidth, pHeight);
		var layoutStr =
			'<rMateChart backgroundColor="#FAFAFA" borderStyle="none">'
				+'<Options>'
					+'<Caption text="' + pSubject + '" styleName="' + pSubjectStyle + '"/>'
		            +'<SubCaption text="' + pUnit + '" textAlign="right"/>'
				+'</Options>'
				+'<NumberFormatter id="numFmt" useThousandsSeparator="true" precision="2"/>'
				+'<Column2DChart showDataTips="true" selectionMode="multiple" columnWidthRatio="0.4" columnWidthMinRatio="0.1" maxColumnWidth="12">'
					+'<horizontalAxis>'
						+'<CategoryAxis id="hAxis" categoryField="' + pCategoryField +'"/>'
					+'</horizontalAxis>'
					+'<verticalAxis>'
						+'<LinearAxis formatter="{numFmt}"/>'
					+'</verticalAxis>'
					+'<series>'
						+'<Column2DSeries yField="copperAmt" displayName="" fillJsFunction="" labelPosition="outside">'
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
	
	// 그리드 생성 함수
    function makeGrid(pListDatas) {
    	commonFunc.initSheet("mySheet");
		
        var initdata = {};

        createIBSheet2(document.getElementById("sheet"), "mySheet", "528px", "474px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "기준일자", Type: "Text", Width: 158, SaveName: "standardDate", Align: "Center" },
            { Header: "동가격", Type: "Float", Width: 185, SaveName: "copperAmt", PointCount: 2 },
            { Header: "환율", Type: "Float", Width: 185, SaveName: "exchangeAmt", PointCount: 2 }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {
		listKomisManage(commonFunc.setReplaceData($("#standard_date").val(), "-", ""));
	});
	
</script>