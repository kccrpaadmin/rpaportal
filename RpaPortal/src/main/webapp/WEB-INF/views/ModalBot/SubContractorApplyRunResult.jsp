<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
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
		ListSubContractorApplyList(requestNo);
	});
	
	//  (세금)계산서 데이터 수집 목록 조회
	function ListSubContractorApplyList(pRequestNo) {
		$.ajax({
			url: "/AjaxBot/ListSubContractorApplyList.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo }),
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
	
	// (세금)계산서 그리드 생성 함수
    function makeGrid(pListDatas) {
    	commonFunc.initSheet("mySheet");

        var initdata = {};

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1060px", "510px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 100, SaveName: "requestNo", Hidden:true },
            { Header: "계약번호", Type: "Text", Width: 100, SaveName: "contractNo", Align: "Center" },
            { Header: "현장명", Type: "Text", Width: 200, SaveName: "siteNm", Align: "Center" },
            { Header: "공사명", Type: "Text", Width: 200, SaveName: "contractNm", Align: "Center" },
            { Header: "하수급인사업장\n관리번호", Type: "Text", Width: 200, SaveName: "subcontractorBusinessMngNo", Align: "Center" },
            { Header: "면허종류", Type: "Float", Width: 80, SaveName: "rpaLicenseType", Align: "Center" },
            { Header: "면허번호", Type: "Text", Width: 80, SaveName: "rpaLicenseNo", Align: "Center"  },
            { Header: "등록일자", Type: "Text", Width: 80, SaveName: "rpaLicenseDate", Align: "Center"  },
            { Header: "상태", Type: "Text", Width: 80, SaveName: "SuccessTypeCd", Align: "Center"  }      
        ];

        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.LoadSearchData(pListDatas);
    }
	
	
</script>