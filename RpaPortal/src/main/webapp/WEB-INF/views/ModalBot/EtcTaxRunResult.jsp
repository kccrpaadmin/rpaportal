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
					<col style="width:10%;" />
	                <col style="width:20%;" />
	                <col />
				</colgroup>
				<tbody>
					<tr>
						<th class="search_dtl_th">조회구분</th>
						<td class="search_dtl_td">
							<label class="lbl_every"><input type="checkbox" class="chk_every" id="error_yn" /><span class="spn_every">불일치건만 조회</span></label>
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
	
	// 전역 변수
	var menuId = "${menuId}";
	var requestNo = "${requestNo}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		searchListEtcTaxList();
	});
	
	//  기타영수증 엑셀 업로드 데이터 목록 조회
	function listEtcTaxList(pRequestNo, pErrorYn) {
		$.ajax({
			url: "/AjaxBot/ListEtcTaxList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo, "errorYn": pErrorYn }),
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
	
	// 그리드 생성 함수
    function makeGrid(pListDatas) {
    	commonFunc.initSheet("mySheet");

        var initdata = {};

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1060px", "450px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 0, SaveName: "requestNo", Hidden:true },            
            { Header: "전표번호", Type: "Text", Width: 150, SaveName: "slipNo", Align: "Center" },
            { Header: "적요", Type: "Text", Width: 230, SaveName: "oraDescription" },
            { Header: "기존\n세금코드", Type: "Text", Width: 80, SaveName: "taxCd", Align: "Center" },
            { Header: "변경\n세금코드", Type: "Text", Width: 80, SaveName: "chgTaxCd", Align: "Center"},
            { Header: "현재 오라클\n세금코드", Type: "Text", Width: 80, SaveName: "oraTaxCd", Align: "Center"},
            { Header: "세금코드명", Type: "Text", Width: 202, SaveName: "oraTaxNm"},
            { Header: "삭제여부", Type: "Text", Width: 60, SaveName: "oraCancelFlag", Align: "Center"},
            { Header: "성공여부", Type: "Text", Width: 60, SaveName: "successType", Align: "Center"},
            { Header: "오류내용", Type: "Text", Width: 100, SaveName: "errorMsg", Align: "Center" },
            { Header: "오라클전표번호", Type: "Text", Width: 0, SaveName: "oraSlipNo", Hidden:true }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
    // 실패된 행에 폰트 색상 변경 함수
	function mySheet_OnRowSearchEnd(row) {
		if(mySheet.GetCellValue(row, "chgTaxCd") == "증빙삭제"){
			if (mySheet.GetCellValue(row, "oraCancelFlag") != "Y"){
				mySheet.SetRowFontColor(row , "#FF0000");
			}
		}  
		else {
			if(mySheet.GetCellValue(row, "chgTaxCd") != mySheet.GetCellValue(row, "oraTaxCd")){
				mySheet.SetRowFontColor(row , "#FF0000");
			}
		}
	}
    
	// 목록 조회 공통 함수
	function searchListEtcTaxList() {
		var errorYn = getCheckYn("#error_yn");		
		
		listEtcTaxList(requestNo, errorYn);
	}
	
	// 체크 박스 체크 여부 조회
	function getCheckYn(pChkBoxId) {
		var returnVal = "";
		if ($(pChkBoxId).is(":checked")) {
			returnVal = "Y";
		}
		return returnVal;
	}
    
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_search", function (e) {								
		searchListEtcTaxList();
	});
	
</script>