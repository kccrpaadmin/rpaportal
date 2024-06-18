<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<style>
	.div_cursor {cursor: pointer;}
	::-webkit-scrollbar {
		width: 5px; /* 스크롤 바의 너비 */
	  	border-radius: 10px;
	  	background-color: #f0f0f0; /* 스크롤 바의 배경색 */
	}
	::-webkit-scrollbar-thumb {
		border-radius: 10px;
		background-color: skyblue; /* 스크롤 바의 색상 */
	}
	
	@keyframes big{
		
		to{
			height:100px;
		}
	}
	ul#ul_botTimeline1 {
        width: 20px;
        height: 20px;
        background-color: orange;
        animation-name: big;
        animation-duration: 2s;
        animation-timing-function: linear;
        animation-delay: 2s;
        animation-iteration-count: 4;
        animation-direction: alternate;
        animation-fill-mode: none;
        animation-play-state: running;
      }
</style>
<head>
	<title></title>
	<meta http-equiv="Contents-Type" content="text/html" charset="utf-8" />
	<link rel="shortcut icon" type="text/css" href="../../../resources/imgs/favicon/kccenc.ico" />
	<link rel="stylesheet" type="text/css" href="../../../resources/css/font/nanumsquare.css" />
	<link rel="stylesheet" type="text/css" href="../../../resources/css/rpa.css?ver=0.1"/>

	<link rel="stylesheet" type="text/css" href="../../../resources/js/library/seeddialog/seeddialog.min.css" />
	<link rel="stylesheet" type="text/css" href="../../../resources/js/library/seedmodal/seedmodal.min.css" />
	<link rel="stylesheet" type="text/css" href="../../../resources/js/library/seedtree/seedtree.min.css" />
	<link rel="stylesheet" type="text/css" href="../../../resources/js/library/seedprogress/seedprogress.min.css" />
	<link rel="stylesheet" type="text/css" href="../../../resources/js/jquery/jquery-ui-1.12.1/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="../../../resources/js/library/rMateChartH5Web_v6.0/rMateChartH5/Assets/Css/rMateChartH5.css" />
</head>

	<!-- 컨텐츠 -->
	<div id="wrap">
		<div id="container">
			<div class="contents contents2">
				<div class="home_wrap">
					<div class="home_box_wrap clearfix">
						<div class="home_card_box card_box1 fl">
							<div class="card_body clearfix">
								<div class="card_body_txt_box fl">
									<p><strong>${getHomeTaskRunTimeVO.totalCnt}</strong>개</p>
								</div>
								<div class="fr">
									<i class="ico_home_card1"></i>
								</div>
							</div>
							<div class="card_bottom">
								<p>총 과제 수</p>
							</div>
						</div>
						<div class="home_card_box card_box2 fl div_cursor" onclick="location.href='/Crawl/ListMenu.do'">
							<div class="card_body clearfix">
								<div class="card_body_txt_box fl">
									<p><strong>${getHomeTaskRunTimeVO.crawlCnt}</strong>개</p>
								</div>
								<div class="fr">
									<i class="ico_home_card2"></i>
								</div>
							</div>
							<div class="card_bottom">
								<p>웹 크롤링</p>
							</div>
						</div>
						<div class="home_card_box card_box3 fl div_cursor" onclick="location.href='/Ocr/ListMenu.do'">
							<div class="card_body clearfix">
								<div class="card_body_txt_box fl">
									<p><strong>${getHomeTaskRunTimeVO.ocrCnt}</strong>개</p>
								</div>
								<div class="fr">
									<i class="ico_home_card3"></i>
								</div>
							</div>
							<div class="card_bottom">
								<p>OCR</p>
							</div>
						</div>
						<div class="home_card_box card_box4 fl div_cursor" onclick="location.href='/Bot/ListMenu.do?pCategoryCd=Main'">
							<div class="card_body clearfix">
								<div class="card_body_txt_box fl">
									<p><strong>${getHomeTaskRunTimeVO.botCnt}</strong>개</p>
								</div>
								<div class="fr">
									<i class="ico_home_card4"></i>
								</div>
							</div>
							<div class="card_bottom">
								<p>BOT</p>
							</div>
						</div>
						<div class="home_card_box card_box5 fl">
							<span class="txt_top">연간 약</span>
							<div class="card_body clearfix">
								<div class="card_body_txt_box fl">
									<p><strong>${getHomeTaskRunTimeVO.runTime}</strong>시간</p>
								</div>
								<div class="fr">
									<i class="ico_home_card5"></i>
								</div>
							</div>
							<div class="card_bottom">
								<p>총 절감시간</p>
							</div>
						</div>
					</div>
					<div class="home_box_wrap clearfix">
						<div class="fl">
							<div class="clearfix home_box_wrap">
								<div class="home_box_style home_notice_box fl">
									<div class="clearfix">
										<div class="home_box_title_box fl">
											<i class="ico_home_notice"></i>
											<span class="home_box_span">공지사항</span>
										</div>
										<div class="fr"><a href="/Board/ListBoard.do" class="ico_home_more"></a></div>
									</div>
									<div class="div_cursor" onclick="location.href='/Board/BoardDetail.do?pSeq=${listBoardVO[0].seq}&pMode=BoardDetail'">
										<p><a class="home_notice_content" style="font-weight:500; font-family:emoji; font-size:14px;">${listBoardVO[0].boardNm}</a></p>
									</div>
								</div>
								<div class="home_box_style home_ocr_box fl">
									<div class="home_box_title_box">
										<i class="ico_home_ocr"></i>
										<span class="home_box_span">OCR</span>
									</div>
									<div class="btn_ocr"><a href="/Ocr/ListMenu.do">텍스트 추출 바로가기<i
												class="ico_home_move"></i></a></div>
								</div>
							</div>
							<div class="home_box_style home_new_box">
								<div class="clearfix">
									<div class="home_box_title_box fl">
										<i class="ico_home_new"></i>
										<span class="home_box_span">대표과제 소개</span>
									</div>
									<div class="fr"><a href="/Bot/ListMenu.do?pCategoryCd=Main" class="ico_home_more"></a></div>
								</div>
								<div class="home_box_text_box div_cursor" id="div_noticeTask">
									<p style="font-family:emoji; font-size:15px;"><i class="ico_home_new_title"></i>${getHomeNoticeTaskVO.menuNm}</p>
									<hr />
									<p class="new_text" style="font-family:emoji;">${getHomeNoticeTaskVO.content}</p>
								</div>
							</div>
						</div>
						<div class="home_box_style home_timeline_box fl">
							<div class="home_box_title_box">
								<i class="ico_home_timeline"></i>
								<span class="home_box_span">오늘의 타임라인</span>
							</div>
							<div class="tl_bot_box clearfix">
								<div class="tl_bbox fl clerfix">
									<div class="fl">
										<i class="ico_home_tl_all"></i>
									</div>
									<div class="tl_text_box fl div_cursor" onclick="listTimeLineBot('')">
										<p id="totCnt" class="tl_title"></p>
										<p class="tl_text">전체</p>
									</div>
								</div>
								<div class="tl_bbox fl clerfix">
									<div class="fl">
										<i class="ico_home_tl_com2"></i>
									</div>
									<div class="tl_text_box fl div_cursor" onclick="listTimeLineBot('4')">
										<p id="cmpltCnt" class="tl_title tl_text2"></p>
										<p class="tl_text tl_text2">수행완료</p>
									</div>
								</div>
								<div class="tl_bbox fl clerfix">
									<div class="fl">
										<i class="ico_home_tl_res"></i>
									</div>
									<div class="tl_text_box fl div_cursor" onclick="listTimeLineBot('3')">
										<p id="rsrvtCnt" class="tl_title tl_text3"></p>
										<p class="tl_text tl_text3">수행예약</p>
									</div>
								</div>
							</div>
							<div class="tl_bot_list_box" style="overflow: auto;height:185px; width:410.42px;">
								<ul id="ul_botTimeline">
									<li class="tl_bot_list_bbox clearfix">
										<div class="tl_bot_list_text_box fl">
											<span class="tl_status_st1">실행</span>
											<span>09:10</span>
											<span class="circle1"></span>
											<span>스마트건설 시스템 체크업무</span>
										</div>
										<div class="fr">
											<span class="tl_now_status_st1">BOT2</span>
										</div>
									</li>
									<li class="tl_bot_list_bbox">
										<div class="tl_bot_list_text_box">
											<span class="tl_status_st2">대기</span>
											<span>09:00</span>
											<span class="circle2"></span>
											<span>법인카드 변경신청업무</span>
										</div>
									</li>
									<li class="tl_bot_list_bbox">
										<div class="tl_bot_list_text_box">
											<span class="tl_status_st3">예약</span>
											<span>10:00</span>
											<span class="circle3"></span>
											<span>그룹사 인사발령 수집업무</span>
										</div>
									</li>
									<li class="tl_bot_list_bbox">
										<div class="tl_bot_list_text_box">
											<span class="tl_status_st3">예약</span>
											<span>11:00</span>
											<span class="circle3"></span>
											<span>겸용서식 세금계산서 자동...</span>
										</div>
									</li>
									<li class="tl_bot_list_bbox clearfix">
										<div class="tl_bot_list_text_box fl">
											<span class="tl_status_st1">실행</span>
											<span>11:30</span>
											<span class="circle1"></span>
											<span>스마트건설 시스템 체크업무</span>
										</div>
										<div class="fr">
											<span class="tl_now_status_st1">BOT3</span>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="clearfix">
						<div class="home_box_style home_bot_graph_box fl">
							<div class="home_box_title_box">
								<i class="ico_home_bot"></i>
								<span class="home_box_span">BOT 가동 현황(최근 3개월)</span>
							</div>
							<div>
								<div id="chartHolder1"></div>
							</div>
						</div>
						<div class="home_box_style home_rpa_graph_box fl">
							<div class="home_box_title_box">
								<i class="ico_home_rpa"></i>
								<span class="home_box_span">RPA 수행 현황(최근 3년)</span>
							</div>
							<div>
								<div id="chartHolder2"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


<script type="text/javascript">
	
	// 페이지 로드 
	$(document).ready(function (e) {		
		$("#div_noticeTask").attr("onclick", "getMenuAuth();")
		listTimeLineBot('');
		//getTaskRunTime();
		//$("body").css("background-color", "#f5f5fb");
		//getCrawlMenu("RA002003", commonFunc.certInfo.empNo);
		//getBotMenu("RA004001", commonFunc.certInfo.empNo);
		//listTimeLineCrawl("RA002001");
		//listTimeLineBot("RA002003");
		//listDeptRunTimeCrawl("RA002001");
		//listDeptRunTimeBot("RA002003");
		listBotRunTime();
		listBotNumberTime();
	});
	
	function fnListTimeLine(datas, pOrd){
		console.log("--");
		console.log(datas);
		
		var ul_list = document.getElementById("ul_botTimeline");
		
		var totCnt = 0;
		var cmpltCnt = 0;
		var rsrvtCnt = 0;
		/* var li_length = $("#ul_botTimeline > li").length
		
		if(li_length > 5){
			li_length = 5
		} else {
			li_length = datas.length;
		} */
		
		
		while(ul_list.firstChild)  {
			ul_list.removeChild(ul_list.firstChild);
		}
		
		if(datas.length > 0){
			for(var i = 0; i < datas.length; i++){
				
				var li = document.createElement("li");
				li.className = "tl_bot_list_bbox";
				li.style = "height:26px"
				
				var div = document.createElement('div');
				var span_status = document.createElement('span');
				var span_circle = document.createElement('span');
				var span_time = document.createElement('span');
				var span_title = document.createElement('span');
				var div_bot;
				var span_bot;
				
				span_title.className = "home_box_span";
				
				totCnt += 1;
				//class="home_box_span"
				if(datas[i].ord == "1")
				{
					//실행
					div.className = "tl_bot_list_text_box fl";
					
					span_status.className = "home_box_span tl_status_st1";
					span_title.textContent = textLengthOverCut(datas[i].menuNm, 17, "");
					span_circle.className = "home_box_span circle1";
					
					div_bot = document.createElement('div');
					div_bot.className = "fr";
					div_bot.style="margin-right:5px;"
					
					span_bot = document.createElement('span');
					span_bot.className = "home_box_span tl_now_status_st1";
					span_bot.textContent = datas[i].botType;
					
				} else if(datas[i].ord == "2"){
					//대기
					div.className = "home_box_span tl_bot_list_text_box";
					
					span_status.className = "home_box_span tl_status_st2";
					span_title.textContent = datas[i].menuNm
					span_circle.className = "home_box_span circle2";
					
				} else if(datas[i].ord == "3"){
					//예약
					div.className = "home_box_span tl_bot_list_text_box";
					
					span_status.className = "home_box_span tl_status_st3";
					span_title.textContent = datas[i].menuNm
					span_circle.className = "home_box_span circle3";
					
					rsrvtCnt += 1;
				} else {
					//완료,실패
					div.className = "home_box_span tl_bot_list_text_box";
					
					span_status.className = "home_box_span tl_status_st4";
					span_title.textContent = datas[i].menuNm
					span_circle.className = "home_box_span circle2";
					
					cmpltCnt += 1;
				}

				span_status.textContent = datas[i].statusType;
				
				span_time.textContent = datas[i].startTime;
				span_time.className = "home_box_span";
				span_time.style = "margin-left:5px"
				
				span_circle.style = "margin-left:7px"
				
				span_title.style = "margin-left:4px"
				
				li.appendChild(div).appendChild(span_status);
				li.appendChild(div).appendChild(span_time);
				li.appendChild(div).appendChild(span_circle);
				li.appendChild(div).appendChild(span_title);
				
				if(datas[i].ord == "1")
				{
					//실행 (우측 Bot 추가)
					li.appendChild(div_bot).appendChild(span_bot);
				}
				
				if(pOrd == "3" || pOrd =="4"){
					if(datas[i].ord == pOrd){
						document.getElementById("ul_botTimeline").appendChild(li);
					}
				} else {
					document.getElementById("ul_botTimeline").appendChild(li);
				}
				
			}
		}
		
		$("#totCnt").text(totCnt);
		$("#cmpltCnt").text(cmpltCnt);
		$("#rsrvtCnt").text(rsrvtCnt);
		/* var li = document.createElement("li");
		li.className = "tl_bot_list_bbox";
		
		var div = document.createElement('div');
		div.className = "tl_bot_list_text_box";
		
		var span_status = document.createElement('span');
		span_status.className = "tl_status_st2";
		span_status.textContent = "실행";
		
		var span_time = document.createElement('span');
		span_time.textContent = "11:30";
		span_time.style = "margin-left:5px"
		
		var span_circle = document.createElement('span');
		span_circle.className = "circle2";
		span_circle.style = "margin-left:7px"
		
		var span_title = document.createElement('span');
		span_title.textContent = "테스트";
		span_title.style = "margin-left:4px"
		
		li.appendChild(div).appendChild(span_status);
		li.appendChild(div).appendChild(span_time);
		li.appendChild(div).appendChild(span_circle);
		li.appendChild(div).appendChild(span_title);
		
		document.getElementById("ul_botTimeline").appendChild(li); */
		
		/* ul_list.append("<li class='tl_bot_list_bbox'>"
							"<div class='tl_bot_list_text_box'>"
								"<span class='tl_status_st2'>대기</span>"
								"<span>09:00</span>"
								"<span class='circle2'></span>"
								"<span>법인카드 변경신청업무</span>"
							"</div>"
						"</li>"
						); */
	}
	
	function textLengthOverCut(txt, len, lastTxt) {
        if (len == "" || len == null) { // 기본값
            len = 21;
        }
        if (lastTxt == "" || lastTxt == null) { // 기본값
            lastTxt = "...";
        }
        if (txt.length > len) {
            txt = txt.substr(0, len) + lastTxt;
        }
        return txt;
    }
		
	
	function fnSiteMove(pData, pMenuId, pAccessUrl){
		
		if (pData.authYn == "N") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "접근권한이 없습니다.", null, null, null);
			return false;
		}
		
		location.href = pAccessUrl+"?pMenuId=${getHomeNoticeTaskVO.menuId}&pEmpNo="+commonFunc.certInfo.empNo+"&pUserId="+commonFunc.certInfo.userId;
	}
	
	function getMenuAuth() {
		
		var pMenuId = "${getHomeNoticeTaskVO.menuId}"
		var pUserId = commonFunc.certInfo.userId;
		var pDutyCd = commonFunc.certInfo.dutyCd;
		var pDeptCd = commonFunc.certInfo.deptCd;
		var pUrlType = "Run";
		var pAccessUrl = "${getHomeNoticeTaskVO.runUrl}";
		var checkNullYn = commonFunc.getCheckNullYn(pAccessUrl);
		
		if (checkNullYn == "Y") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "업무수행 대상 메뉴가 아닙니다.", null, null, null);
			return false;
		}
		else {
			$.ajax({
				url: "/AjaxMenu/GetMenuAuth.do",
				type: "POST",
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify({ "urlType": pUrlType, "menuId": pMenuId, "userId": pUserId, "dutyCd": pDutyCd, "deptCd": pDeptCd }),
			    dataType : "json",
		        async: true,
				success: function(data) {
					fnSiteMove(data, pMenuId, pAccessUrl);
				},
				error: function(xhr, status, err) {
					commonFunc.handleErrorMsg(xhr, status, err);
					return false;
				}
			});	
		}
	}
	
	// 웹크롤링 홈화면 타임라인 목록 조회
	function getTaskRunTime() {
		$.ajax({
			url: "/AjaxHome/getTaskRunTime.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			//data : JSON.stringify({ "workTypeCd": pWorkTypeCd }),
			data : "",
		    dataType : "json",
	        async: false,
			success: function(datas) {
				test(datas);
			},
			error: function(xhr, status, err) {
				//commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
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
	function listTimeLineBot(pOrd) {
		$.ajax({
			url: "/AjaxHome/ListTimeLine.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "ord": "" }),
		    dataType : "json",
	        async: false,
			success: function(datas) {
				fnListTimeLine(datas, pOrd);
				//commonFunc.createHomeTimeLineList("home_time_line_box_bot", datas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 웹크롤링 홈화면 절감시간 목록 조회 (리뉴얼 후 사용안함)
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
	
	// 봇 홈화면 절감시간 목록 조회 (리뉴얼 후 사용안함)
	function listDeptRunTimeBot(pWorkTypeCd) {
		$.ajax({
			url: "/AjaxHome/ListDeptRunTime.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "workTypeCd": pWorkTypeCd }),
		    dataType : "json",
	        async: false,
			success: function(datas) {
				make2DColumnChart("chart2", "chartHolder2", "515px", "335px", datas, "", "subjectStyleType1", "단위(시간)", "deptNm", "45");
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 홈화면 봇 가동현황(최근3개월)
	function listBotRunTime() {
		$.ajax({
			url: "/AjaxHome/ListBotRunTime.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
		    dataType : "json",
		    data : JSON.stringify({ "workTypeCd": "" }),
	        async: false,
			success: function(datas) {
				make2DLineChart("chart1", "chartHolder1", "520px", "325px", datas, "", "subjectStyleType1", "단위(시간)", "runYear", "45");
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	function listBotNumberTime() {
		$.ajax({
			url: "/AjaxHome/ListBotNumberTime.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "workTypeCd": "" }),
		    dataType : "json",
	        async: false,
			success: function(datas) {
				make2DColumnChart("chart2", "chartHolder2", "520px", "325px", datas, "", "subjectStyleType1", "단위(시간)", "deptNm", "45");
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}

	// 웹클롤링 과제현황 데이터 변경
	function setTaskRunTime(pData) {
		console.log(pData);
		$("#TotalCnt").text(pData.totalCnt);
		$("#crawlCnt").text(pData.crawlCnt);
		$("#ocrCnt").text(pData.ocrCnt);
		$("#botCnt").text(pData.botCnt);
		$("#runTime").text(pData.runTime);
		//$("#crawl_menu_nm").text(pData.menuNm);
		//$("#crawl_time").text(pData.timeTypeNm + " / " + pData.runSeq + " / " + pData.runTime);
		//$("#crawl_content").text(pData.content);		
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
	function make2DLineChart(pChartNm, pChartElmtNm, pWidth, pHeight, pDatas, pSubject, pSubjectStyle, pUnit, pCategoryField, pLabelRotation) {
		rMateChartH5.create(pChartNm, pChartElmtNm, "", pWidth, pHeight);
		
		var layoutStr =
		    '<rMateChart backgroundColor="#FFFFFF" borderStyle="none">'
		        +'<Options>'
		            +'<Legend defaultMouseOverAction="true" useVisibleCheck="true" />'
		            +'<SubCaption text="시간" textAlign="left"/>'
		        +'</Options>'
		        +'<NumberFormatter id="nft" precision="0"/>'
		        +'<Line2DChart seriesMouseOverAction="series" showDataTips="true" dataTipFormatter="{nft}" paddingBottom="5">'
		            +'<horizontalAxis>'
		                +'<CategoryAxis id="hAxis" categoryField="week"/>'
		            +'</horizontalAxis>'
		            +'<verticalAxis>'
		                +'<LinearAxis id="vAxis" interval="50"/>'
		            +'</verticalAxis>'
		            +'<series>'
		                +'<Line2DSeries yField="bot1" displayName="Bot1">'
		                    +'<showDataEffect>'
		                        + '<SeriesClip duration="1000"/>'
		                    +'</showDataEffect>'
		                +'</Line2DSeries>'
		                +'<Line2DSeries yField="bot2" displayName="Bot2">'
	                    +'<showDataEffect>'
	                        + '<SeriesClip duration="1000"/>'
	                    +'</showDataEffect>'
	                	+'</Line2DSeries>'
	                	+'<Line2DSeries yField="bot3" displayName="Bot3">'
	                    +'<showDataEffect>'
	                        + '<SeriesClip duration="1000"/>'
	                    +'</showDataEffect>'
	                	+'</Line2DSeries>'
		            +'</series>'
		            /* +'<horizontalAxisRenderers>'
                		+'<Axis2DRenderer axis="{hAxis}" styleName="xStyle"/>'
              		+'</horizontalAxisRenderers>' */
		        +'</Line2DChart>'
		        /* +'<Style>'
                	+'.xStyle {labelRotation:45;}'
            	+'</Style>' */
		    +'</rMateChart>';
		    
		rMateChartH5.calls(pChartNm, {
		    "setLayout" : layoutStr,
		    "setData" : pDatas
		});
	}	
	
	function axisLabelFunc(id, value)
	{
	    return value+"월";
	}
	
	function make2DColumnChart(pChartNm, pChartElmtNm, pWidth, pHeight, pDatas, pSubject, pSubjectStyle, pUnit, pCategoryField, pLabelRotation) {
		rMateChartH5.create(pChartNm, pChartElmtNm, "", pWidth, pHeight);
		
		var now = new Date();
		var year = now.getFullYear();
		
		var layoutStr =
            '<rMateChart backgroundColor="#FFFFFF" borderStyle="none">'
                +'<Options>'
                    +'<Legend defaultMouseOverAction="true" useVisibleCheck="true" />'
                  	+'<SubCaption text="수행횟수" textAlign="left"/>'
                +'</Options>'
                +'<Column2DChart seriesMouseOverAction="none" showDataTips="true" selectionMode="single" paddingBottom="5">'
                    +'<horizontalAxis>'
                        +'<CategoryAxis categoryField="regMonth" padding="1"/>' //labelJsFunction="axisLabelFunc"
                    +'</horizontalAxis>'
                    +'<series>'
                        +'<Column2DSeries labelPosition="outside" yField="year1" displayName="'+(year-2)+'" showValueLabels="[-1]">'
	                        +'<showDataEffect>'
	                        	+'<SeriesInterpolate offset="0" elementOffset="100"/>'
	                    	+'</showDataEffect>'
                        +'</Column2DSeries>'
                        +'<Column2DSeries labelPosition="outside" yField="year2" displayName="'+(year-1)+'" showValueLabels="[-1]]">'
	                        +'<showDataEffect>'
	                        	+'<SeriesInterpolate offset="100" elementOffset="100"/>'
	                    	+'</showDataEffect>'
                        +'</Column2DSeries>'
                        +'<Column2DSeries labelPosition="outside" yField="year3" displayName="'+year+'" showValueLabels="[-1]">'
	                        +'<showDataEffect>'
	                        	+'<SeriesInterpolate offset="200" elementOffset="100"/>'
	                    	+'</showDataEffect>'
                        +'</Column2DSeries>'
                    +'</series>'
                +'</Column2DChart>'
            +'</rMateChart>';
            
            /*
		var layoutStr =
			'<rMateChart backgroundColor="#ffffff" borderStyle="none">'
				+'<Options>'
					//+'<Caption text="' + pSubject + '" styleName="' + pSubjectStyle + '"/>'
		            //+'<SubCaption text="' + pUnit + '" textAlign="right"/>'
				+'</Options>'
				+'<NumberFormatter id="numFmt" useThousandsSeparator="true"/>'
				+'<Column2DChart showDataTips="true" type="clustered">'
					+'<horizontalAxis>'
						+'<CategoryAxis id="hAxis" categoryField="runYear"/>'
					+'</horizontalAxis>'
					+'<verticalAxis>'
						+'<LinearAxis formatter="{numFmt}"/>'
					+'</verticalAxis>'
					+'<series alwayShowLabels="true">'
						+'<Column2DSeries yField="month1" displayName="2021" insideLabelJsFunction="seriesLabelFunc">'
						+'</Column2DSeries>'
						+'<Column2DSeries yField="month2" displayName="2022">'
						+'</Column2DSeries>'
						+'<Column2DSeries yField="month3" displayName="20234">'
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
*/
		rMateChartH5.calls(pChartNm, {
		    "setLayout" : layoutStr,
		    "setData" : pDatas
		});
	}	
	
</script>