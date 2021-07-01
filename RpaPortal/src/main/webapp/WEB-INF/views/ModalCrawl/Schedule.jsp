<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<!-- 상세영역 -->
	    <div class="detail_box">
        	<table class="detail_tbl">
	            <caption>상세영역</caption>
	            <colgroup>
	                <col style="width:12%;" />
	                <col style="width:12%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                    <th class="detail_th_l">
	                    	<label class="lbl_every"><input type="radio" class="rdo_every" checked="checked" /><span class="spn_every">월</span></label>
						</th>
	                    <td class="detail_td_l">
	                    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_all_month" /><span class="spn_every">매월</span></label>
	                    </td>
	                    <td class="detail_td_l" id="chk_months"></td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">
	                    	<label class="lbl_every"><input type="radio" class="rdo_every" name="day_week_type" id="rdo_day" checked="checked" /><span class="spn_every">일</span></label>
						</th>
	                    <td class="detail_td_l">
	                    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_all_day" /><span class="spn_every">매일</span></label>
	                    </td>
	                    <td class="detail_td_l" id="chk_days"></td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l" rowspan="2">
	                    	<label class="lbl_every"><input type="radio" class="rdo_every" name="day_week_type" id="rdo_week" /><span class="spn_every">요일</span></label>
						</th>
	                    <td class="detail_td_l">
	                    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_all_week" /><span class="spn_every">매주</span></label>
	                    </td>
	                    <td class="detail_td_l" id="chk_weeks"></td>
	                </tr>
	                <tr>
	                    <td class="detail_td_l">
	                    	<label class="lbl_every"><input type="checkbox" class="chk_every" id="chk_all_week_day" /><span class="spn_every">매요일</span></label>
	                    </td>
	                    <td class="detail_td_l" id="chk_week_days"></td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">
	                    	<label class="lbl_every"><input type="radio" class="rdo_every" checked="checked" /><span class="spn_every">시작시간</span></label>
						</th>
	                    <td class="detail_td_l" colspan="2">
	                    	<select class="combo_every" id="time_hour" name="time_hour">
	                            <option selected="selected" value="00">00</option>
	                            <option value="01">01</option>
	                            <option value="02">02</option>
	                            <option value="03">03</option>
	                            <option value="04">04</option>
	                            <option value="05">05</option>
	                            <option value="06">06</option>
	                            <option value="07">07</option>
	                            <option value="08">08</option>
	                            <option value="09">09</option>
	                            <option value="10">10</option>
	                            <option value="11">11</option>
	                            <option value="12">12</option>
	                            <option value="13">13</option>
	                            <option value="14">14</option>
	                            <option value="15">15</option>
	                            <option value="16">16</option>
	                            <option value="17">17</option>
	                            <option value="18">18</option>
	                            <option value="19">19</option>
	                            <option value="20">20</option>
	                            <option value="21">21</option>
	                            <option value="22">22</option>
	                            <option value="23">23</option>
	                        </select>
	                        <select class="combo_every" id="time_minute" name="time_minute">
	                            <option selected="selected" value="00">00</option>
	                            <option value="05">05</option>
	                            <option value="10">10</option>
	                            <option value="15">15</option>
	                            <option value="20">20</option>
	                            <option value="25">25</option>
	                            <option value="30">30</option>
	                            <option value="35">35</option>
	                            <option value="40">40</option>
	                            <option value="45">45</option>
	                            <option value="50">50</option>
	                            <option value="55">55</option>
	                        </select>
	                    </td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">
	                    	<label class="lbl_every"><input type="radio" class="rdo_every" checked="checked" /><span class="spn_every">만료일자</span></label>
						</th>
	                    <td class="detail_td_l" colspan="2">
	                    	<input type="text" class="datepicker_ymd" id="exp_date" readonly="readonly" />
	                    </td>
	                </tr>
	            </tbody>
	        </table>
	    </div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common" id="btn_create">생성</a>
	    	<a class="btn_common" id="btn_delete">삭제</a>
	    </div>
	    <!-- 그리드영역 -->
		<div id="sheet"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${menuId}";
	
    // 페이지 로드
    $(document).ready(function (e) {
        commonFunc.createDatepicker(".datepicker_ymd", "YearMonthDay");
        createCheckBoxType("chk_months", "month");
        createCheckBoxType("chk_days", "day");
        createCheckBoxType("chk_weeks", "week");
        createCheckBoxType("chk_week_days", "weekDay");
        disableDayOrWeek();
        listSchedule(menuId);
    });
	
 	// 웹크롤링 스케쥴 목록 조회
	function listSchedule(pMenuId) {
		$.ajax({
			url: "/AjaxCrawl/ListSchedule.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId }),
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
 	
	// 웹크롤링 스케쥴 정보 생성
	function createSchedule(pMenuId, pMonthz, pDayz, pWeekz, pWeekDayz, pStartTime, pExpDate, pEmpNo) {
		$.ajax({
			url: "/AjaxCrawl/CreateSchedule.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId, "monthz": pMonthz, "dayz": pDayz, "weekz": pWeekz, "weekDayz": pWeekDayz, "startTime": pStartTime, "expDate": pExpDate, "empNo": pEmpNo }),
			dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "생성 되었습니다.", null, commonFunc.refreshPage);
				}
				else {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "오류가 발생 하였습니다.", null, commonFunc.refreshPage);
				}
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 웹크롤링 스케쥴 정보 삭제
	function deleteSchedule(pMenuId, pSeqStr) {
		$.ajax({
			url: "/AjaxCrawl/DeleteSchedule.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId, "seqStr": pSeqStr }),
			dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "삭제 되었습니다.", null, commonFunc.refreshPage);
				}
				else {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "오류가 발생 하였습니다.", null, commonFunc.refreshPage);
				}
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1060px", "197px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
        	{ Header: "상태", Type: "Status", Width: 50, SaveName: "status", Align: "Center" },
        	{ Header: "삭제", Type: "DelCheck", Width: 50, SaveName: "delete", Align: "Center" },
            { Header: "메뉴ID", Type: "Text", Width: 80, SaveName: "menuId", Align: "Center", Edit: false },
            { Header: "순번", Type: "Text", Width: 50, SaveName: "seq", Align: "Center", Edit: false },
            { Header: "월", Type: "Text", Width: 140, SaveName: "monthz", Align: "Center", Edit: false },
            { Header: "일", Type: "Text", Width: 190, SaveName: "dayz", Align: "Center", Edit: false },
            { Header: "주차", Type: "Text", Width: 140, SaveName: "weekz", Align: "Center", Edit: false },
            { Header: "요일", Type: "Text", Width: 120, SaveName: "weekDayz", Align: "Center", Edit: false },
            { Header: "시작시간", Type: "Text", Width: 80, SaveName: "startTime", Align: "Center", Edit: false },
            { Header: "만료일자", Type: "Text", Width: 80, SaveName: "expDate", Align: "Center", Edit: false },
            { Header: "작성자", Type: "Text", Width: 80, SaveName: "regUserNm", Align: "Center", Edit: false },
        ];
		
        IBS_InitSheet(mySheet, initdata);
        //mySheet.SetEditable(1);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }	
	
 	// 체크박스 생성 함수
    function createCheckBox(pElmtNm, pType, pValue, pStr) {
    	var area = document.getElementById(pElmtNm);
    	
    	var lblEvery = document.createElement("label");
    	lblEvery.className = "lbl_every";
		area.appendChild(lblEvery);
		
		var chkEvery = document.createElement("input");
		chkEvery.id = pType + "_" + pValue;
		chkEvery.className = "chk_every";
		chkEvery.type  = "checkbox";
		lblEvery.appendChild(chkEvery);
		
		var spnEvery = document.createElement("span");
		spnEvery.className = "spn_every";
		if (pType == "weekDay" || pStr == "마지막") {
			spnEvery.innerText = pStr;
		}
		else {
			spnEvery.innerText = pValue + pStr;
		}
		lblEvery.appendChild(spnEvery);
	}
 	
 	// 분류별 체크 박스 생성 함수
    function createCheckBoxType(pElmtNm, pType) {
    	if (pType == "month") {
    		var arrMonths = ["1","2","3","4","5","6","7","8","9","10","11","12"];
    		for (var i = 0; i < arrMonths.length; i++) {
    			createCheckBox(pElmtNm, pType, arrMonths[i], "월");
    		}
    	}
    	else if (pType == "day") {
    		var arrDays = ["1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","0"];
    		for (var i = 0; i < arrDays.length; i++) {
    			if (arrDays[i] == "0") {
    				createCheckBox(pElmtNm, pType, arrDays[i], "마지막");
    			}
    			else {
    				createCheckBox(pElmtNm, pType, arrDays[i], "일");
    			}
    		}
    	}
    	else if (pType == "week") {
    		var arrWeeks = ["1","2","3","4","5","6","0"];
    		for (var i = 0; i < arrWeeks.length; i++) {
    			if (arrWeeks[i] == "0") {
    				createCheckBox(pElmtNm, pType, arrWeeks[i], "마지막");
    			}
    			else {
    				createCheckBox(pElmtNm, pType, arrWeeks[i], "주차");	
    			}    			
    		}
    	}
    	else if (pType == "weekDay") {
    		var arrWeekDays = ["1","2","3","4","5","6","7"];
    		var arrWeekDaysHan = ["일","월","화","수","목","금","토"];
    		for (var i = 0; i < arrWeekDays.length; i++) {
    			createCheckBox(pElmtNm, pType, arrWeekDays[i], arrWeekDaysHan[i]);
    		}
    	}  	
	}      
    
 	// 지정한 엘리먼트의 모든 자식 체크박스 체크 함수
    function checkAllCheckBox(pElmtNm, pNum) {
 		var i = 1;
    	$(pElmtNm).children().each(function() {
			var chkBox = $(this).children()[0];
			if (i <= pNum) {
				$(chkBox).prop("checked", true);
			}
			else {
				$(chkBox).prop("checked", false);
			}
			i = i + 1;
		});
	}
 	
 	// 지정한 엘리먼트의 모든 자식 체크박스 체크해제 함수
    function unCheckAllCheckBox(pElmtNm, pNum) {
 		var i = 1;
    	$(pElmtNm).children().each(function() {
			var chkBox = $(this).children()[0];
			if (i <= pNum) {
				$(chkBox).prop("checked", false);
			}
			i = i + 1;
		});
	}
 	
 	// 지정한 엘리먼트의 모든 자식 체크박스 체크 및 활성화 함수
    function enableAllCheckBox(pElmtNm) {
    	$(pElmtNm).children().each(function() {
			var chkBox = $(this).children()[0];
			$(chkBox).prop("checked", false);
			$(chkBox).prop("disabled", false);
		});
	}
 	
    // 지정한 엘리먼트의 모든 자식 체크박스 체크해제 및 비활성화 함수
    function disableAllCheckBox(pElmtNm) {
    	$(pElmtNm).children().each(function() {
			var chkBox = $(this).children()[0];
    		$(chkBox).prop("checked", false);
    		$(chkBox).prop("disabled", true);
		});
	}
    
 	// 지정한 엘리먼트의 모든 자식 체크박스 체크여부 조회 함수
    function getIsCheckBox(pElmtNm, pNum) {
 		var returnVal = true;
 		
    	var i = 1;
    	var j = 0;
    	$(pElmtNm).children().each(function() {
			var chkBox = $(this).children()[0];
			if (i <= pNum) {
				if (!$(chkBox).is(":checked")) {
					j = j + 1;
				}
			}
			i = i + 1;
		});
    	
    	if (j > 0) {
    		returnVal = false;
    	}
    	
    	return returnVal;
	}
    
 	// 지정한 엘리먼트의 모든 자식 체크박스 체크된 개수 조회 함수
    function getCheckBoxCheckCnt(pElmtNm) {
    	var i = 0;
    	$(pElmtNm).children().each(function() {
			var chkBox = $(this).children()[0];
			if ($(chkBox).is(":checked")) {
				i = i + 1;
			}
		});
    	return i;
	}
 	
 	// 지정한 엘리먼트의 모든 자식 체크박스 체크된 값 조회 함수
    function getCheckBoxCheckValueStr(pElmtNm) {
    	var returnVal = "";
    	$(pElmtNm).children().each(function() {
			var chkBox = $(this).children()[0];
			if ($(chkBox).is(":checked")) {
				returnVal = returnVal + $(chkBox).attr("id").split("_")[1] + ";"
			}
		});
    	return returnVal;
	}
 
    // 일, 요일 선택시 컨트롤 활성화 및 비활성화 함수
    function disableDayOrWeek() {
    	if ($("input:radio[name='day_week_type']:checked").attr("id") == "rdo_day") {
    		// 일 활성화
    		$("#chk_all_day").prop("disabled", false);
    		enableAllCheckBox("#chk_days");
    		
    		// 요일 비활성화 (주차,요일)
    		$("#chk_all_week").prop("checked", false);
    		$("#chk_all_week").prop("disabled", true);
    		$("#chk_all_week_day").prop("checked", false);
    		$("#chk_all_week_day").prop("disabled", true);
    		disableAllCheckBox("#chk_weeks");
    		disableAllCheckBox("#chk_week_days");
    	}
    	else {
    		// 요일 활성화 (주차,요일)
    		$("#chk_all_week").prop("disabled", false);
    		$("#chk_all_week_day").prop("disabled", false);
    		enableAllCheckBox("#chk_weeks");
    		enableAllCheckBox("#chk_week_days");
    		
    		// 일 비활성화
    		$("#chk_all_day").prop("checked", false);
    		$("#chk_all_day").prop("disabled", true);
    		disableAllCheckBox("#chk_days");
    	}
	}
    
 	// 저장 전, 확인 함수
	function createScheduleConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
	    	var monthz = getCheckBoxCheckValueStr("#chk_months");
	    	var dayz = getCheckBoxCheckValueStr("#chk_days");
	    	var weekz = getCheckBoxCheckValueStr("#chk_weeks");
	    	var weekDayz = getCheckBoxCheckValueStr("#chk_week_days");
	    	var startTime = $("#time_hour").val() + ":" + $("#time_minute").val();  
	    	var expDate = $("#exp_date").val();
	    	var empNo = commonFunc.certInfo.empNo;
	    	createSchedule(menuId, monthz, dayz, weekz, weekDayz, startTime, expDate, empNo);
        }
	}
 	
	// 삭제 전, 확인 함수
	function deleteScheduleConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
			var saveJson = mySheet.GetSaveJson().data;
			var saveJsonLen = saveJson.length;
			var seqStr = "";
			
			for (var i = 0; i < saveJsonLen; i++) {
				var seq = saveJson[i].seq;
				if (i == 0) {
					seqStr = seqStr + seq;	
				}
				else {
					seqStr = seqStr + "," +seq;
				}
			}
			
			deleteSchedule(menuId, seqStr);
        }
	}
	
    // 매월 체크 변경 이벤트
    $(document).on("change", "#chk_all_month", function (e) {
        if ($(this).is(":checked")) {
        	checkAllCheckBox("#chk_months", 12);
        }
        else {
        	unCheckAllCheckBox("#chk_months", 12);
        }
    });
    
 	// 매일 체크 변경 이벤트
    $(document).on("change", "#chk_all_day", function (e) {
        if ($(this).is(":checked")) {
        	checkAllCheckBox("#chk_days", 31);
        }
        else {
        	unCheckAllCheckBox("#chk_days", 31);
        }
    });
    
 	// 매주 체크 변경 이벤트
    $(document).on("change", "#chk_all_week", function (e) {
        if ($(this).is(":checked")) {
        	checkAllCheckBox("#chk_weeks", 6);
        }
        else {
        	unCheckAllCheckBox("#chk_weeks", 6);
        }
    });
    
 	// 매요일 체크 변경 이벤트
    $(document).on("change", "#chk_all_week_day", function (e) {
        if ($(this).is(":checked")) {
        	checkAllCheckBox("#chk_week_days", 7);
        }
        else {
        	unCheckAllCheckBox("#chk_week_days", 7);
        }
    });
    
 	// 해당월 체크 변경 이벤트
    $(document).on("click", "#chk_months .chk_every", function (e) {
    	if (getIsCheckBox("#chk_months", 12) ==  true) {
    		$("#chk_all_month").prop("checked", true);
    	}
    	else {
    		$("#chk_all_month").prop("checked", false);
    	}
    });
    
 	// 해당일 체크 변경 이벤트
    $(document).on("click", "#chk_days .chk_every", function (e) {
    	if (getIsCheckBox("#chk_days", 31) ==  true) {
    		$("#chk_all_day").prop("checked", true);
    	}
    	else {
    		$("#chk_all_day").prop("checked", false);
    	}
    });
    
 	// 해당주차 체크 변경 이벤트
    $(document).on("click", "#chk_weeks .chk_every", function (e) {
    	if (getIsCheckBox("#chk_weeks", 6) ==  true) {
    		$("#chk_all_week").prop("checked", true);
    	}
    	else {
    		$("#chk_all_week").prop("checked", false);
    	}
    });
    
 	// 해당요일 체크 변경 이벤트 
    $(document).on("click", "#chk_week_days .chk_every", function (e) {
    	if (getIsCheckBox("#chk_week_days", 7) ==  true) {
    		$("#chk_all_week_day").prop("checked", true);
    	}
    	else {
    		$("#chk_all_week_day").prop("checked", false);
    	}
    });
    
 	// 일, 요일 체크 변경 이벤트
    $(document).on("click", "input:radio[name='day_week_type']", function (e) {
    	disableDayOrWeek();
    });
 	
 	// 저장 버튼 클릭 이벤트
    $(document).on("click", "#btn_create", function (e) {
    	if (getCheckBoxCheckCnt("#chk_months") == 0) {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "월을 선택하지 않았습니다.", null, null);
    		return false;
    	}
    	
    	if ($("input:radio[name='day_week_type']:checked").attr("id") == "rdo_day") {
    		if (getCheckBoxCheckCnt("#chk_days") == 0) {
    			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "일을 선택하지 않았습니다.", null, null);
        		return false;
        	}
    	}
    	else {
    		if (getCheckBoxCheckCnt("#chk_weeks") == 0) {
    			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "주차를 선택하지 않았습니다.", null, null);
        		return false;
        	}
    		
    		if (getCheckBoxCheckCnt("#chk_week_days") == 0) {
    			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "요일을 선택하지 않았습니다.", null, null);
        		return false;
        	}
    	}
    	
    	if ($("#exp_date").val() == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "만료일자를 선택하지 않았습니다.", null, null);
    		return false;
    	}
    	
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "생성 하시겠습니까?", null, createScheduleConfirm);
    });
 	
 	// 삭제 버튼 클릭 이벤트
    $(document).on("click", "#btn_delete", function (e) {
    	var saveStr = mySheet.GetSaveString();
    	
    	if (saveStr == "") {
    		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "삭제 대상을 선택하지 않았습니다.", null, null);
    		return false;
    	}
    	
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "삭제 하시겠습니까?", null, deleteScheduleConfirm);    	
    });
 	
</script>	