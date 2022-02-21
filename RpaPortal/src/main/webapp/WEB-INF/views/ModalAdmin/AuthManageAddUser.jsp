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
							<input type="text" class="txt_box_l" style="width:160px;" id="search_txt"  />	
							<input type="button"  id="btn_search" value="조회" />						
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
	menuId = "${menuId}";
	menuType = "${menuType}";
	
	// 페이지 로드 
	$(document).ready(function (e) {		
		ListAuthManageSearchUser(menuId, menuType, "");
	});
	
	// 직원 조회
	function ListAuthManageSearchUser(pMenuId, pMenuType, pSearchTxt) {
		$.ajax({
			url: "/AjaxAdmin/ListAuthManageSearchUser.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId, "menuType": pMenuType, "searchTxt": pSearchTxt }),
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
	
	// 개인 권한 저장
	function CreateAuthManage(pMenuId, pMenuType, pAuthTypeCd, pAuthType, pEmpNo) {
		$.ajax({
			url: "/AjaxAdmin/CreateAuthManage.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({"menuId": pMenuId, "menuType": pMenuType, "authTypeCd": pAuthTypeCd, "authType": pAuthType, "empNo": pEmpNo}),
			dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "추가 되었습니다.", null, function () {
						commonFunc.refreshPage();
						parent.commonFunc.refreshPage();
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
		commonFunc.initSheet("mySheet");
	
	    var initdata = {};
	
	    createIBSheet2(document.getElementById("sheet"), "mySheet", "560px", "340px");
	
	    initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
	    initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
	    initdata.Cols = [                        
	        { Header: "사번", Type: "Text", Width: 100, SaveName: "userId", Align: "Center" },
	        { Header: "ID", Type: "Text", Width: 0, SaveName: "epUserId", Align: "Center", Hidden: true },
	        { Header: "이름", Type: "Text", Width: 100, SaveName: "userNm", Align: "Center" },
	        { Header: "소속부서", Type: "Text", Width: 243, SaveName: "deptNm"}
	    ];
	
	    IBS_InitSheet(mySheet, initdata);
	    mySheet.SetEditable(0);
	    mySheet.SetEditableColorDiff(0);
	    mySheet.SetColFontUnderline("userNm", true);
	    mySheet.SetDataLinkMouse("userNm", true);
	    mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
	    mySheet.LoadSearchData(pListDatas);
	}
	
	// 그리드 클릭 함수
	function mySheet_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
		
		var userNm = mySheet.GetCellValue(Row, "userNm");	
		var param = [];
		param.push(Row);		
		
		libraryFunc.createDialog("Confirm", null, null, null, null, "알림", userNm + " 직원 권한을 추가하시겠습니까?", null, CreateAuthManageUserConfirm, param);    
	}
	
	function CreateAuthManageUserConfirm(pOption, pParam){
		if (pOption.sdBtnKey == "o") {
			var authTypeCd = "RA001001";
			var authType = mySheet.GetCellValue(pParam[0], "epUserId");
			var empNo = commonFunc.certInfo.empNo;
			
			CreateAuthManage(menuId, menuType, authTypeCd, authType, empNo);	
		}	
	}
		
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {
		var searchTxt = $("#search_txt").val()
		
		ListAuthManageSearchUser(menuId, menuType, searchTxt);
	});
	
	// 엔터 이벤트
	$(document).on("keydown", "#search_txt", function (e) {
		if (e.keyCode == 13) {
			$("#btn_search").trigger("click");
		}
	});
 	
</script>	