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
	    	<a class="btn_common" id="btn_text_extract">텍스트 추출</a>
	    </div>
	    <!-- 데이터영역 -->
	    <textarea id="ocr_data" style="width:1054px;height:240px"></textarea>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${ocrRequestVO.menuId}";
	var requestNo = "${ocrRequestVO.requestNo}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		listText(requestNo);
	});
	
	// OCR 텍스트 추출 목록 조회
	function listText(pRequestNo) {
		$.ajax({
			url: "/AjaxOcr/ListText.do",
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
	
	// OCR 텍스트 추출 상세 조회
	function getText(pRequestNo, pSeq) {
		$.ajax({
			url: "/AjaxOcr/GetText.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "requestNo": pRequestNo, "seq": pSeq }),
		    dataType : "json",
	        async: true,
			success: function(data) {
				$("#ocr_data").val(data.ocrData);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// OCR 텍스트 추출 실행
	function runOcrText(pRequestNo, pEmpNo) {
		$.ajax({
			url: "/AjaxOcr/RunOcrText.do",
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
			if (mySheet.GetCellValue(Row, "actionCd") == "RA006001") {
				var requestNo = mySheet.GetCellValue(Row, "requestNo");
				var seq = mySheet.GetCellValue(Row, "seq");
				getText(requestNo, seq);	
			}
   		}
	}
 	
	// 오류가 발생한 행에 폰트 색상 변경 함수
	function mySheet_OnRowSearchEnd(row) {
    	if (mySheet.GetCellValue(row, "actionCd") == "RA006002"){
			mySheet.SetRowFontColor(row , "#FF0000");
		}
	}
 
 	// 텍스트 추출 전, 확인 함수
	function runOcrTextConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
			var empNo = commonFunc.certInfo.empNo;
			runOcrText(requestNo, empNo);
        }
	}
 	
	// 텍스트 추출 버튼 클릭 이벤트
	$(document).on("click", "#btn_text_extract", function (e) {
		libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "텍스트 추출을 진행 하시겠습니까?", null, runOcrTextConfirm);
	});
	
</script>