<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<!-- 컨텐츠 -->
	<div class="contents">
		<!-- 그리드영역 -->
		<div id="sheet"></div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common" id="btn_business_license_extract">사업자등록증 추출</a>
	    </div>
	    <!-- 상세영역 -->
	    <div class="detail_box">
        	<table class="detail_tbl">
	            <caption>상세영역</caption>
	            <colgroup>
	                <col style="width:12%;" />
	                <col style="width:38%;" />
	                <col style="width:12%;" />
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                    <th class="detail_th_l">탬플릿ID</th>
	                    <td class="detail_td_l" id="template_id"></td>
	                    <th class="detail_th_l">탬플릿명</th>
	                    <td class="detail_td_l" id="template_nm"></td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">사업자번호</th>
	                    <td class="detail_td_l" id="biz_no"></td>
	                    <th class="detail_th_l">업체명</th>
	                    <td class="detail_td_l" id="vendor_nm"></td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">대표자명</th>
	                    <td class="detail_td_l" id="rep_nm"></td>
	                    <th class="detail_th_l">법인번호</th>
	                    <td class="detail_td_l" id="corp_no"></td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">업태</th>
	                    <td class="detail_td_l" id="biz_type"></td>
	                    <th class="detail_th_l">업종</th>
	                    <td class="detail_td_l" id="biz_kind"></td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">주소</th>
	                    <td class="detail_td_l" colspan="3" id="addr"></td>
	                </tr>
	            </tbody>
	        </table>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	//전역 변수
	var menuId = "${ocrRequestVO.menuId}";
	var requestNo = "${ocrRequestVO.requestNo}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listBusinessLicense(requestNo);
	});
	
	// OCR 사업자등록증 추출 목록 조회
	function listBusinessLicense(pRequestNo) {
		$.ajax({
			url: "/AjaxOcr/ListBusinessLicense.do",
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
	
	// OCR 사업자등록증 추출 상세 조회
	function getBusinessLicense(pRequestNo, pSeq) {
		$.ajax({
			url: "/AjaxOcr/GetBusinessLicense.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo, "seq": pSeq }),
		    dataType : "json",
	        async: true,
			success: function(data) {
				$("#template_id").text(data.templateId);
				$("#template_nm").text(data.templateNm);
				$("#biz_no").text(data.bizNo);
				$("#vendor_nm").text(data.vendorNm);
				$("#rep_nm").text(data.repNm);
				$("#corp_no").text(data.corpNo);
				$("#biz_type").text(data.bizType);
				$("#biz_kind").text(data.bizKind);
				$("#addr").text(data.addr);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// OCR 사업자등록증 추출 실행
	function runOcrBusinessLicense(pRequestNo, pEmpNo) {
		$.ajax({
			url: "/AjaxOcr/RunOcrBusinessLicense.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo, "empNo": pEmpNo }),
			dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "요청이 성공 하였습니다.", null, commonFunc.refreshPage);
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

        createIBSheet2(document.getElementById("sheet"), "mySheet", "1060px", "322px");

        initdata.Cfg = { SearchMode: smLazyLoad, MergeSheet: msHeaderOnly, AutoFitColWidth: "search", MaxSort: 1 };
        initdata.HeaderMode = { Sort: 1, ColMove: 1, ColResize: 1, HeaderCheck: 0 };
        initdata.Cols = [
            { Header: "요청번호", Type: "Text", Width: 180, SaveName: "requestNo", Align: "Center" },
            { Header: "첨부ID", Type: "Text", Width: 100, SaveName: "attId", Hidden:true },
            { Header: "순번", Type: "Text", Width: 60, SaveName: "seq", Align: "Center" },
            { Header: "파일명", Type: "Text", Width: 270, SaveName: "fileNm" },
            { Header: "파일크기", Type: "Text", Width: 80, SaveName: "fileSize", Align: "Center" },
            { Header: "동작상태코드", Type: "Text", Width: 100, SaveName: "actionCd", Hidden:true },
            { Header: "동작상태", Type: "Text", Width: 80, SaveName: "actionNm", Align: "Center" },
            { Header: "오류메세지", Type: "Text", Width: 390, SaveName: "errorMsg" }
        ];
		
        IBS_InitSheet(mySheet, initdata);
        mySheet.SetEditable(0);
        mySheet.SetEditableColorDiff(0);
        mySheet.SetDataLinkMouse("fileNm", true);
        mySheet.SetColFontUnderline("fileNm", true);
        mySheet.SetTheme("LPP", "LightPurple"); // 테마 색상 변경
        mySheet.SetCountPosition(3); // 건수 정보 표시
        mySheet.LoadSearchData(pListDatas);
    }
	
 	// 그리드 클릭 함수
	function mySheet_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH) {
		if (Row == 0) {
			return false;
		}
		
		if (mySheet.ColSaveName(Col) == "fileNm") {
			if (mySheet.GetCellValue(Row, "actionCd") == "RA005001") {
				var requestNo = mySheet.GetCellValue(Row, "requestNo");
				var seq = mySheet.GetCellValue(Row, "seq");
				getBusinessLicense(requestNo, seq);
			}
   		}
	}
 	
	// 오류가 발생한 행에 폰트 색상 변경 함수
	function mySheet_OnRowSearchEnd(row) {
    	if (mySheet.GetCellValue(row, "actionCd") == "RA005002"){
			mySheet.SetRowFontColor(row , "#FF0000");
		}
	}
 
 	// 사업자등록증 추출 전, 확인 함수
	function runOcrBusinessLicenseConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
			var empNo = commonFunc.certInfo.empNo;
			runOcrBusinessLicense(requestNo, empNo);
        }
	}
 	
	// 사업자등록증 추출 버튼 클릭 이벤트
	$(document).on("click", "#btn_business_license_extract", function (e) {
		libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "사업자등록증 추출을 진행 하시겠습니까?", null, runOcrBusinessLicenseConfirm);
	});
	
</script>