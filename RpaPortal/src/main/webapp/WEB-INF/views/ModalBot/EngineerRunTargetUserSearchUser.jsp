<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">	
		<div class="search_dtl_box">
			<table class="search_dtl_tbl">
				<colgroup>
					<col style="width:20%;" />
	                <col />
				</colgroup>
				<tbody>
					<tr>
						<th class="search_dtl_th">이름</th>
						<td class="search_dtl_td">
							<input type="text" class="txt_box_l" style="width:160px;" id="user_nm"  />	
							<input type="button"  id="btn_searchuser" value="조회" />						
						</td>				
					</tr>
					<tr>
						<th class="search_dtl_th">부문</th>
						<td class="search_dtl_td">
							<label class="lbl_every"><input type="radio" class="rdo_every" name="org_type" id="rdo_civil" value="RA011001" /><span class="spn_every" id="span_civil">토목</span></label>
							<label class="lbl_every"><input type="radio" class="rdo_every" name="org_type" id="rdo_build" value="RA011002" /><span class="spn_every" id="span_build">건축</span></label>	
						</td>				
					</tr>				
				</tbody>				
			</table>
	    </div>	
		<!-- 그리드영역 -->
		<div id="sheet"></div>
	</div>
</div>

<script type="text/javascript">
	
	//전역 변수
	
	
	// 페이지 로드 
	$(document).ready(function (e) {		
		ListEngineerTargetUserSearchUser("");
		checkButtonControl();
	});
	
	// 직원 조회
	function ListEngineerTargetUserSearchUser(pUserNm) {
		$.ajax({
			url: "/AjaxBot/ListEngineerTargetUserSearchUser.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "userNm": pUserNm }),
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
	
	// 대상 인원 목록 저장
	function createEngineerTargetUser(pUserId, pOrgTypeCd, pEmpNo) {
		$.ajax({
			url: "/AjaxBot/CreateEngineerTargetUser.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({"userId": pUserId, "orgTypeCd": pOrgTypeCd, "empNo": pEmpNo}),
			dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "추가 되었습니다.", null, function () {
						commonFunc.refreshPage();
						parent[0].commonFunc.refreshPage();
					});
				}
				else {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "오류가 발생 하였습니다.", null, commonFunc.refreshPage, null);
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
		commonFunc.initSheet("mySheet2");
	
	    var initdata = {};
	
	    createIBSheet2(document.getElementById("sheet"), "mySheet2", "543px", "300px");
	
	    initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
	    initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
	    initdata.Cols = [                        
	        { Header: "사번", Type: "Text", Width: 100, SaveName: "userId", Align: "Center" },
	        { Header: "이름", Type: "Text", Width: 100, SaveName: "userNm", Align: "Center" },
	        { Header: "생년월일", Type: "Text", Width: 100, SaveName: "resNo", Align: "Center"},
	        { Header: "소속부서", Type: "Text", Width: 243, SaveName: "deptNm"}
	    ];
	
	    IBS_InitSheet(mySheet2, initdata);
	    mySheet2.SetEditable(0);
	    mySheet2.SetEditableColorDiff(0);
	    mySheet2.SetColFontUnderline("userNm", true);
	    mySheet2.SetDataLinkMouse("userNm", true);
	    mySheet2.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
	    mySheet2.LoadSearchData(pListDatas);
	}
	
	// 그리드 클릭 함수
	function mySheet2_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
		
		var userNm = mySheet2.GetCellValue(Row, "userNm");	
		var param = [];
		param.push(Row);		
		
		libraryFunc.createDialog("Confirm", null, null, null, null, "알림", userNm + " 사원을 추가하시겠습니까?", null, createEngineerTargetUserConfirm, param);    
	}
	
	function createEngineerTargetUserConfirm(pOption, pParam){
		if (pOption.sdBtnKey == "o") {
			var userId = mySheet2.GetCellValue(pParam[0], "userId");
			var orgTypeCd = $("input:radio[name='org_type']:checked").attr("value");
			var empNo = commonFunc.certInfo.empNo;
			
			createEngineerTargetUser(userId, orgTypeCd, empNo);	
		}	
	}
	
	// 라디오 버튼 기본 체크 함수
	function checkButtonControl() {
		if (commonFunc.certInfo.deptCd == "C10043") {
			$("input:radio[name='org_type']:radio[value='RA011002']").prop('checked', true);
        }else{
        	$("input:radio[name='org_type']:radio[value='RA011001']").prop('checked', true);
        }
	}
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_searchuser", function (e) {
		var userNm = $("#user_nm").val()
		
		ListEngineerTargetUserSearchUser(userNm);
	});
	
	// 업체명 엔터 이벤트
	$(document).on("keydown", "#user_nm", function (e) {
		if (e.keyCode == 13) {
			$("#btn_searchuser").trigger("click");
		}
	});
 	
</script>	