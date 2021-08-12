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
	                <col style="width:15%;" />
	                <col style="width:35%;" />
	                <col style="width:15%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                    <th class="detail_th_l">업체명</th>
	                    <td class="detail_td_l">${outEseroVO.vendorNm}</td>
	                    <th class="detail_th_l">전화번호</th>
	                    <td class="detail_td_l">${outEseroVO.telNo}</td>
	                </tr>	    <tr>
	                    <th class="detail_th_l">주소</th>
	                    <td class="detail_td_l" colspan="3">${outEseroVO.addr}</td>
	                </tr>	               
	            </tbody>
	        </table>
	    </div>	
	    <br>
		<!-- 그리드영역 -->		
		<div id="sheet"></div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var vendorCd = "${vendorCd}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listEseroManageVendorSlipList(vendorCd);
	});
	
	// 업체별 최근 1년간 전표 데이터 조회
	function listEseroManageVendorSlipList(pVendorCd) {
		$.ajax({
			url: "/AjaxBot/ListEseroManageVendorSlipList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "vendorCd": pVendorCd}),
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1060px", "420px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
          
           // { Header: "업체명", Type: "Text", Width: 150, SaveName: "vendorNm", Align: "Center" },
            { Header: "전표번호", Type: "Text", Width: 160, SaveName: "slipId", Align: "Center" },
            { Header: "회계처리일자", Type: "Text", Width: 90, SaveName: "glDate", Align: "Center" },
            { Header: "발의부서", Type: "Text", Width: 200, SaveName: "deptNm", Align: "Center" },
            { Header: "적요", Type: "Text", Width: 600, SaveName: "summary" }
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }    
</script>