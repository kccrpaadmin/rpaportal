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
						<th class="search_dtl_th">업체명</th>
						<td class="search_dtl_td">
							<input type="text" class="txt_box_l" style="width:160px;" id="vendor_nm"  />	
							<input type="button"  id="btn_vendorsearch" value="조회" />						
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
	
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listVendor("");
	});
	
	// 조회 버튼 클릭 이벤트
	$(document).on("click", "#btn_vendorsearch", function (e) {
		var vendorNm = $("#vendor_nm").val()
		
		listVendor(vendorNm);
	});
	
	// 업체명 엔터 이벤트
	$(document).on("keydown", "#vendor_nm", function (e) {
		if (e.keyCode == 13) {
			$("#btn_vendorsearch").trigger("click");
		}
	});
	
	// 업체 조회
	function listVendor(pVendorNm) {
		$.ajax({
			url: "/AjaxBot/listVendor.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "vendorNm": pVendorNm }),
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
            { Header: "업체코드", Type: "Text", Width: 300, SaveName: "vendorCd", Align: "Center" },
            { Header: "업체명", Type: "Text", Width: 300, SaveName: "vendorNm", Align: "Center" },
            { Header: "사업자번호", Type: "Text", Width: 300, SaveName: "bizNo", Align: "Center"},
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetDataLinkMouse("vendorNm", true);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
    // 그리드 클릭 함수
	function mySheet_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
				
		var vendorCd = mySheet.GetCellValue(Row, "vendorCd");
		var vendorNm = mySheet.GetCellValue(Row, "vendorNm");
		
		if (mySheet.ColSaveName(Col) == "vendorNm") {
			parent.setVendorNm(vendorCd, vendorNm);
			
			libraryFunc.closeModal();	
		}		
	}
	
	
</script>