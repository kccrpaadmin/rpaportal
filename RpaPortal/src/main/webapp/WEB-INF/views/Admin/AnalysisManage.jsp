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
				<div class="location_title">방문자통계</div>	
			</div>
			<div class="location_right">
				<span class="location_home">관리자</span>
				<span class="location_arrow">방문자통계</span>
			</div>
		</div>
		
		
	    <div class="">
			<div class="home_task2">
				<!-- 검색영역 -->
			    <div class="search_dtl_box">
			        <table class="search_dtl_tbl">
			            <caption>검색영역</caption>
			            <colgroup>
			                <col style="width:8%;" />
			                <col style="width:40%;" />
			                <col />
			            </colgroup>
			            <tbody>
			                <tr>
			                	<th class="search_dtl_th">조회기간</th>
			                    <td class="search_dtl_td">
			                        <input type="text" class="datepicker_ym" readonly="readonly"  id="year_mon_day1" value="${beginDate}" />
			                        <span>~</span>
			                        <input type="text" class="datepicker_ym" readonly="readonly"  id="year_mon_day2" value="${endDate}" />
			                    </td>
			                </tr>	                
			            </tbody>
			        </table>
			    </div>
			    <!-- 버튼영역 -->
			    <div class="btn_box">
			    	<a class="btn_common1" id="btn_search" >조회</a>
			    </div>
			    <div class="title" >BOT 방문수</div>
				<div class="graph_box"  id="chartHolder1"style="margin-bottom: 20px"></div>
				<div class="title" >웹크롤링 방문수</div>
				<div class="graph_box"  id="chartHolder2" style="margin-bottom: 20px"></div>
				<div class="title" >OCR 방문수</div>
				<div class="graph_box"  id="chartHolder3" ></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
		
	//전역 변수
	menuId = "";

	// 페이지 로드 
	$(document).ready(function (e) {
		commonFunc.createDatepicker(".datepicker_ym", "YearMonthDay");
		goSearch();
	});
	
	// 검색 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {
		goSearch();
	});
	
	function showMore(seriesId, displayText, index, data, values) {
		var beginDate = $("#year_mon_day1").val();
		var endDate = $("#year_mon_day2").val(); 
		
		var accessUrl = data.accessUrl;
		
		if(values[0] == data.runCnt){
			accessUrl = data.runUrl;
		} else {
			accessUrl = data.manageUrl;
		}

		libraryFunc.createModal(null, null, null, 650, 650, "상세내역", "/ModalAdmin/VisitList.do" 
				+ '?pAccessUrl=' + accessUrl 
				+ '&pBeginDate=' + beginDate
				+ '&pEndDate=' + endDate
				);
	}
	

	function goSearch(){
		var beginDate = $("#year_mon_day1").val();
		var endDate = $("#year_mon_day2").val();
		listBotProcessUsage(beginDate,endDate);
		listCrawlProcessUsage(beginDate,endDate);
		listOcrProcessUsage(beginDate,endDate);
	}
	
	function listBotProcessUsage(beginDate,endDate) {
		$.ajax({
			url: "/AjaxAdmin/ListProcessVisits.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "searchCondition": "Bot", "beginDate": beginDate,"endDate": endDate}),
		    dataType : "json",
	        async: false,
			success: function(datas) {
				var height = datas.length * 40
				height = height + "px";
				make2DColumnChart("chart1", "chartHolder1", "1000px", height, datas, "", "subjectStyleType1", "단위(건)", "menuNm", "");
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	function listCrawlProcessUsage(beginDate,endDate) {
		$.ajax({
			url: "/AjaxAdmin/ListProcessVisits.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "searchCondition": "Crawl", "beginDate": beginDate,"endDate": endDate}),
		    dataType : "json",
	        async: false,
			success: function(datas) {
				make2DColumnChart("chart2", "chartHolder2", "1000px", "300px", datas, "", "subjectStyleType1", "단위(건)", "menuNm", "");
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	function listOcrProcessUsage(beginDate,endDate) {
		$.ajax({
			url: "/AjaxAdmin/ListProcessVisits.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "searchCondition": "Ocr", "beginDate": beginDate,"endDate": endDate}),
		    dataType : "json",
	        async: false,
			success: function(datas) {
				make2DColumnChart("chart3", "chartHolder3", "1000px", "200px", datas, "", "subjectStyleType1", "단위(건)", "menuNm", "");
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 메뉴 접속량 차트 생성 함수	
	function make2DColumnChart(pChartNm, pChartElmtNm, pWidth, pHeight, pDatas, pSubject, pSubjectStyle, pUnit, pCategoryField, pLabelRotation) {
		rMateChartH5.create(pChartNm, pChartElmtNm, "", pWidth, pHeight);
		var layoutStr =
			 '<rMateChart backgroundColor="#FFFFFF" borderStyle="none">'
            +'<Options>'
	            +'<Caption text="' + pSubject + '" styleName="' + pSubjectStyle + '"/>'
	            	+'<SubCaption text="' + pUnit + '" textAlign="right"/>'
              +'<Legend defaultMouseOverAction="false" useVisibleCheck="true"/>'
             +'</Options>'
          +'<Bar2DChart showDataTips="true" selectionMode="single" barWidthRatio="0.66" itemClickJsFunction="showMore">'
                +'<horizontalAxis>'
                    +'<LinearAxis interval="10"/>'
               +'</horizontalAxis>'
               +'<verticalAxis>'
                  +'<CategoryAxis categoryField="menuNm" id="hAxis"/>'
               +'</verticalAxis>'
                 +'<series>'
                /* Bar2D Multi-Sereis 를 생성시에는 Bar2DSeries 여러 개 정의합니다 */
                  +'<Bar2DSeries labelPosition="inside" halfWidthOffset="1" showValueLabels="true" xField="runCnt" displayName="업무수행" color="#ffffff" insideLabelYOffset="-2">'
                      +'<showDataEffect>'
                            +'<SeriesInterpolate/>'
                        +'</showDataEffect>'
                   +'</Bar2DSeries>'
                  +'<Bar2DSeries labelPosition="inside" halfWidthOffset="1" showValueLabels="true" xField="workManage" displayName="업무관리" color="#ffffff" insideLabelYOffset="-2">'
                         +'<showDataEffect>'
                            +'<SeriesInterpolate/>'
                        +'</showDataEffect>'
                   +'</Bar2DSeries>'
              +'</series>'
              +'<verticalAxisRenderers>'
         		+'<Axis2DRenderer axis="{hAxis}" fontSize="12" showLine="true" tickPlacement="none" minorTickPlacement="none"/>'
       		  +'</verticalAxisRenderers>'
           +'</Bar2DChart>'
       +'</rMateChart>';
		
		rMateChartH5.calls(pChartNm, {
		    "setLayout" : layoutStr,
		    "setData" : pDatas
		});
		console.log(pDatas);
	}	
</script>