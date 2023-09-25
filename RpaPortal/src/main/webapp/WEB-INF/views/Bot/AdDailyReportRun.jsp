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
				<span class="location_arrow">업무수행</span>
			</div>
		</div>
		<!-- 제목 -->
		<div class="title">상세정보</div>
		<!-- 상세영역 -->
	    <div class="detail_box">
        	<table class="detail_tbl">
	            <caption>상세영역</caption>
	            <colgroup>
	                <col style="width:15%;" />
	                <col style="width:35%;" />
	                <col style="width:15%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                    <th class="detail_th_l">과제코드</th>
	                    <td class="detail_td_l">${outMenuVO.menuId}</td>
	                    <th class="detail_th_l">과제명</th>
	                    <td class="detail_td_l">${outMenuVO.menuNm}</td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">담당부서</th>
	                    <td class="detail_td_l">${outMenuVO.deptNm}</td>
	                    <th class="detail_th_l">업무분류</th>
	                    <td class="detail_td_l">${outMenuVO.workTypeNm}</td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">절감시간</th>
	                    <td class="detail_td_l">${outMenuVO.timeTypeNm} / ${outMenuVO.runSeq} / ${outMenuVO.runTime}</td>
	                    <th class="detail_th_l">진행상태</th>
	                    <td class="detail_td_l">${outMenuVO.statusNm}</td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">과제분류</th>
	                    <td class="detail_td_l">${outMenuVO.taskTypeNm}</td>
	                    <th class="detail_th_l">수행분류</th>
	                    <td class="detail_td_l">${outMenuVO.execTypeNm}</td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">최근요청번호</th>
	                    <td class="detail_td_l">${outMenuVO.requestNo}</td>
	                    <th class="detail_th_l">최근요청자</th>
	                    <td class="detail_td_l">${outMenuVO.regUserNm}</td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">최근시작시간</th>
	                    <td class="detail_td_l">${outMenuVO.regDate}</td>
	                    <th class="detail_th_l">최근종료시간</th>
	                    <td class="detail_td_l">${outMenuVO.chgDate}</td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">업무설명</th>
	                    <td class="detail_td_l" colspan="3">${outMenuVO.content}</td>
	                </tr>
	            </tbody>
	        </table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_target_ad" >수집대상광고</a>
	    	<a class="btn_common1" id="btn_immediate_call" >즉시실행</a>
	    	<a class="btn_common1" id="btn_schedule_open" style="display:none;">예약실행</a>
	    </div>
	    <!-- 그리드영역 -->
   	    <div id="sheet"></div>
   	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a href="/Bot/ListMenu.do"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${outMenuVO.menuId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listRequest(menuId, commonFunc.certInfo.empNo);
		enableButtonControl();
	});
	
	// BOT 요청 목록 조회
	function listRequest(pMenuId, pEmpNo) {
		$.ajax({
			url: "/AjaxBot/ListRequest.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId, "empNo": pEmpNo }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeGrid(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 봇 실행
	function runBot(pMenuId, pEmpNo, pUserId) {
		$.ajax({
			url: "/AjaxBot/RunBot.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId, "empNo": pEmpNo, "userId": pUserId }),
			dataType : "json",
	        async: true,
			success: function(data) {
				openDialogRunBot(data.requestStatus);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 버튼 활성화, 비활성화 함수
	function enableButtonControl() {
		if (commonFunc.certInfo.roleType == "ROLE_ADMIN") {
			$("#btn_schedule_open").css("display", "inline-block");   
        }
	}
		
	// 그리드 생성 함수
    function makeGrid(pListDatas) {
    	commonFunc.initSheet("mySheet");
		
        var initdata = {};

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1120px", "324px");

        initdata.Cfg = { SearchMode: smClientPaging, Page: 10, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 200, SaveName: "requestNo", Align: "Center" },
            { Header: "메뉴ID", Type: "Text", Width: 100, SaveName: "menuId", Hidden:true },
            { Header: "요청명", Type: "Text", Width: 400, SaveName: "requestNm", Align: "Center" },
            { Header: "진행상태", Type: "Text", Width: 110, SaveName: "statusNm", Align: "Center" },
            { Header: "요청자", Type: "Text", Width: 110, SaveName: "regUserNm", Align: "Center" },
            { Header: "시작시간", Type: "Text", Width: 150, SaveName: "regDate", Align: "Center" },
            { Header: "종료시간", Type: "Text", Width: 150, SaveName: "chgDate", Align: "Center" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetDataLinkMouse("requestNm", true);
        mySheet.SetColFontUnderline("requestNm", true);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.SetCountPosition(3); // 건수 정보 표시
		mySheet.SetPagingPosition(2); // 페이지 네비게이션 버튼 표시
        mySheet.LoadSearchData(pListDatas);
    }  
	
    // 그리드 클릭 함수
	function mySheet_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
		
		if (mySheet.ColSaveName(Col) == "requestNm") {
			var requestNo = mySheet.GetCellValue(Row, "requestNo");
			libraryFunc.createModal(null, null, null, 1100, 560, "결과보기", "/ModalBot/AdDailyReportRunResult.do" + "?pMenuId=" + menuId + "&pRequestNo=" + requestNo);
   		}
	}
   	
 	// 즉시실행 전, 확인 함수
	function runBotConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
			runBot(menuId, commonFunc.certInfo.empNo, commonFunc.certInfo.userId);
        }
	}
 	
	// 즉시실행 후, 대화상자 오픈 함수
	function openDialogRunBot(pData) {
		if (pData == "Progress") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "이미 실행중인 요청이 있습니다.<br/>잠시후에 다시시도 하세요.", null, commonFunc.refreshPage, null);
			return false;
		}
		else if (pData == "Success") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "요청을 완료 하였습니다.", null, commonFunc.refreshPage, null);
			return false;
		}
		else {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "요청중 오류가 발생 하였습니다.", null, commonFunc.refreshPage, null);
			return false;
		}
	}
	
	// 즉시실행 버튼 클릭 이벤트
	$(document).on("click", "#btn_immediate_call", function (e) {
		libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "요청을 진행 하시겠습니까?", null, runBotConfirm, null);
	});
	
	// 예약등록 버튼 클릭 이벤트
	$(document).on("click", "#btn_schedule_open", function (e) {
		libraryFunc.createModal(null, null, null, 1100, 560, "예약등록", "/ModalBot/Schedule.do?pMenuId=" + menuId);
	});	

	// 수집대상광고 버튼 클릭 이벤트
	$(document).on("click", "#btn_target_ad", function (e) {
		libraryFunc.createModal(null, null, null, 1100, 560, "수집대상광고", "/ModalBot/AdDailyReportTargetAd.do?pMenuId=" + menuId);
	});
</script>