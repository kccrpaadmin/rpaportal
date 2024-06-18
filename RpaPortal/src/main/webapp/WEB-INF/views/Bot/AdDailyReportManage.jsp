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
				<span class="location_arrow">BOT</span>
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
	                <col style="width:10%;" />
	                <col style="width:20%;" />
	                <col style="width:13%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                	<th class="search_dtl_th">기준광고</th>
	                    <td class="search_dtl_td">
	                        ${select1ComboBox}
	                    </td>
	                	<th class="search_dtl_th">이전광고(비교시)</th>
	                    <td class="search_dtl_td">
	                    	${select2ComboBox}     
	                    </td>
	                </tr>	                
	            </tbody>
	        </table>
	    </div>
	    
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_search">조회</a>
	    </div>	    
	    <br>
	    
	    <!-- 차트영역 -->
    	<div class="chart_box">
			<div class=chart_header>스위첸 유튜브 광고 조회수 현황 (180일 기준)</div>
			<div id="chartHolder1"></div>
		</div>
		<br>
		
	    <!-- 그리드영역 -->
	    <div class="grid_box" style="float:right;">
		    <div class="grid_btn">
		    	<a class="btn_common2" id="btn_excel_download">엑셀</a>
		    </div>
	    </div>
   	    <div id="sheet" style="float:left; margin-top:5px;"></div>
   	    <br>
   	    
   	    <!-- 버튼영역 -->
	    <div class="btn_box" style="float:left;width:100%">
	    	<a href="/Bot/ListMenu.do?pCategoryCd=${outMenuVO.categoryCd}"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${outMenuVO.menuId}";	
	
	// 페이지 로드 
	$(document).ready(function (e) {		
		searchListAdDailyReportManage()
	});
	
	// 데이터 조회
	function listAdDailyReportManage(pAdSelect1, pAdSelect2) {
		$.ajax({
			url: "/AjaxBot/ListAdDailyReportManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({"adSelect1": pAdSelect1, "adSelect2": pAdSelect2}),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				make2DLineChartAd("chart1", "chartHolder1", "1078px", "350px", listDatas.chartData, "", "subjectStyleType1", "", "dayCount", "false");
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1120px", "510px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly + msPrevColumnMerge, PrevColumnMergeMode:0, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "주차", Type: "Text", Width: 100, SaveName: "weekCount", Align: "Center" },
        	{ Header: "구분", Type: "Text", Width: 300, SaveName: "type", Align: "Center"  },
            { Header: "월", Type: "Text", Width: 100, SaveName: "mon", Align: "Center" },
            { Header: "화", Type: "Text", Width: 100, SaveName: "tue", Align: "Center" },            
            { Header: "수", Type: "Text", Width: 100, SaveName: "wed", Align: "Center" },
            { Header: "목", Type: "Text", Width: 100, SaveName: "thu", Align: "Center" },
            { Header: "금", Type: "Text", Width: 100, SaveName: "fri", Align: "Center" },   
            { Header: "토", Type: "Text", Width: 100, SaveName: "sat", Align: "Center" },   
            { Header: "일", Type: "Text", Width: 100, SaveName: "sun", Align: "Center" }    
        ];
		
        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
 	// 셀 스타일 변경
	function mySheet_OnRowSearchEnd(row) {
		if (mySheet.GetCellValue(row, "type") == "구분") {
            mySheet.SetRowBackColor(row, "#FFFFD2"); //색
            mySheet.SetCellFontBold(row, "weekCount", 1);
            mySheet.SetCellFontBold(row, "type", 1);
            mySheet.SetCellFontBold(row, "mon", 1);
            mySheet.SetCellFontBold(row, "tue", 1);
            mySheet.SetCellFontBold(row, "wed", 1);
            mySheet.SetCellFontBold(row, "thu", 1);
            mySheet.SetCellFontBold(row, "fri", 1);
            mySheet.SetCellFontBold(row, "sat", 1);
            mySheet.SetCellFontBold(row, "sun", 1);
        } 		
	}
	
    // 스위첸 유튜브 광고 조회수 현황 차트 생성 함수
	function make2DLineChartAd (pChartNm, pChartElmtNm, pWidth, pHeight, pDatas, pSubject, pSubjectStyle, pUnit, pCategoryField, pButtonMode) {
		rMateChartH5.create(pChartNm, pChartElmtNm, "", pWidth, pHeight);
		var dynamicLayout = "";
		
		if (pDatas[0].beforeViewCount != null && pDatas[0].beforeViewCount != "")
		{
			dynamicLayout += '<Line2DSeries yField="beforeViewCount" displayName="이전광고" >'
								+'<showDataEffect>'
									+'<SeriesInterpolate/>'
								+'</showDataEffect>'
							+'</Line2DSeries>'
		}
		
		var layoutStr =
			'<rMateChart backgroundColor="#FAFAFA" borderStyle="none">'
				+'<Options>'
					+'<Caption text="' + pSubject + '" styleName="' + pSubjectStyle + '"/>'
		            +'<SubCaption text="' + pUnit + '" textAlign="right"/>'
		            +'<Legend useVisibleCheck="true"/>'
				+'</Options>'
				+'<NumberFormatter id="numFmt" useThousandsSeparator="true" precision="0"/>'
				+'<Line2DChart showDataTips="true" dataTipDisplayMode="axis" paddingBottom="25" buttonMode="' + pButtonMode + '">'
					+'<horizontalAxis>'
						+'<CategoryAxis id="hAxis" categoryField="' + pCategoryField + '"/>'
					+'</horizontalAxis>'
					+'<verticalAxis>'
						+'<LinearAxis formatter="{numFmt}"/>'					
					+'</verticalAxis>'
					+'<series>'
						+ '<Line2DSeries yField="latestViewCount" displayName="기준광고" >'
						+'<showDataEffect>'
							+'<SeriesInterpolate/>'
						+'</showDataEffect>'
						+'</Line2DSeries>'
						+ dynamicLayout
					+' </series>'
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
					+'.subjectStyleType1{fontSize:10;fontFamily:dotum;fontWeight:bold;color:#5D5D5D;}'
		     	+'</Style>'
			+'</rMateChart>';
		
		rMateChartH5.calls(pChartNm, {
		    "setLayout" : layoutStr,
		    "setData" : pDatas
		});
	}
    
	// 목록 조회 공통 함수
	function searchListAdDailyReportManage() {
		var adSelect1 = $("#ad_select_1").val();
		var adSelect2 = $("#ad_select_2").val();
		
		if(adSelect1 == adSelect2){
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "기준광고와 이전광고가 동일합니다. 다시 선택하시기 바랍니다.  ", null, null, null);
    		return false;
		}
		
		listAdDailyReportManage(adSelect1, adSelect2);
	}
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {		
		searchListAdDailyReportManage();
	});
	
	// 엑셀 다운로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_excel_download", function (e) {		
		var params = { Multipart: 0, FileName: "YoutubeAdReport.xls",  SheetName: "Sheet", Merge:1, AutoSizeColumn:1, ExcelRowHeight:20, SheetDesign:1 }
		mySheet.Down2Excel(params);
	});

</script>