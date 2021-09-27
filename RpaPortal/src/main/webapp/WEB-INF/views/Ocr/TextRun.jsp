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
				<span class="location_arrow">OCR</span>
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
	                    <th class="detail_th_l">업무설명</th>
	                    <td class="detail_td_l" colspan="3">${outMenuVO.content}</td>
	                </tr>
	            </tbody>
	        </table>
	    </div>
		<!-- 첨부영역 -->
		<div class="file_box">
			<form method="post" enctype="multipart/form-data" id="frm">
			  	<input type="file" name="files" class="input_file" multiple="multiple" id="input_file" />
				<input type="text" class="input_file_txt" readonly="readonly" id="input_file_txt" />
				<label for="input_file" class="label_file">찾아보기</label>
				<input type="hidden" id="menuId" name="menuId" value="${outMenuVO.menuId}" />
				<input type="hidden" id="empNo" name="empNo" />
			</form>
		</div>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_file_upload">파일업로드</a>
	    </div>
	     <!-- 그리드영역 -->
   	    <div id="sheet"></div>
      	<!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a href="/Ocr/ListMenu.do"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var menuId = "${outMenuVO.menuId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		$("#empNo").val(commonFunc.certInfo.empNo);
		listRequest(menuId, $("#empNo").val());
	});
   	   	
	// 파일 업로드 실행
	function uploadFiles() {
		$.ajax({
			url: "/AjaxOcr/UploadFiles.do",
			type: "POST",
			processData: false,
			enctype: "multipart/form-data",
			contentType: false,
			data : new FormData($("#frm")[0]),
			dataType : "json",
	        async: true,
			success: function(datas) {
				openDialog(datas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// OCR 요청 목록 조회
	function listRequest(pMenuId, pEmpNo) {
		$.ajax({
			url: "/AjaxOcr/ListRequest.do",
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
	
	// OCR 요청 사용범위 초과 여부 조회
	function getOcrRequestUseRangeOverYn() {
		var useRangeOverYn = "N";
		
		$.ajax({
			url: "/AjaxOcr/GetOcrRequestUseRangeOverYn.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
		    dataType : "json",
	        async: false,
			success: function(datas) {
				useRangeOverYn = datas.useRangeOverYn; 
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
		
		return useRangeOverYn;
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
			libraryFunc.createModal(null, null, null, 1100, 560, "결과보기", "/ModalOcr/TextRunResult.do" + "?pMenuId=" + menuId + "&pRequestNo=" + requestNo);
   		}
	}
 	
	// 파일 컨트롤 변경 이벤트 
	$(document).on("change", "#input_file", function (e) {
		var inputFile = document.getElementById('input_file');
	 	var fileList = inputFile.files;
	 	var fileListLen = fileList.length;
	 	var returnVal = "";

		// 10개 이상 체크 차단
	 	if (fileListLen > 10) {
	 		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "첨부파일은 10개이하로 선택해 주세요.", null, null, null);
	 		// input file 초기화
	 		inputFile.value = null;
	 		$("#input_file_txt").val("");
	 		return false;
	 	}
	 	
	 	// 첨부 확장자 체크 차단
	 	for (var i = 0; i < fileListLen; i++) {
	 		if (!checkAllowExtension(fileList[i].type)) {
	 			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "파일(" + fileList[i].name + ")의 확장자는 지원되지 않습니다.", null, null, null);
	 			// input file 초기화
		 		inputFile.value = null;
		 		$("#input_file_txt").val("");
		 		return false;
	 		}
	 	}
	 	
	 	// 체크 로직이 이상이 없는 경우만 
	 	for (var i = 0; i < fileListLen; i++) {
	 		returnVal = returnVal + fileList[i].name + ";";	 		
	 	}

 		$("#input_file_txt").val(returnVal);	
	});
	
	// 허용되는 이미지 확장자 정의 함수
	function checkAllowExtension(pInput) {
		var reg = /(image\/bmp|image\/gif|image\/jpeg|image\/png|image\/tiff)/;
		var returnVal = reg.test(pInput);
		return returnVal;
	}
	
	// 파일 업로드 전, 확인 함수
	function uploadFilesConfirm(pOption) {
		if (pOption.sdBtnKey == "o") {
			uploadFiles();
        }
	}
	
	// 파일 업로드 후, 대화상자 오픈 함수
	function openDialog(pData) {
		if (pData.status == "Success") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "업로드를 완료 하였습니다.", null, commonFunc.refreshPage, null);
			return false;
		}
		else {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", pData.errorMsg, null, commonFunc.refreshPage, null);
			return false;
		}
	}
	
	// 파일 업로드 버튼 클릭 이벤트
	$(document).on("click", "#btn_file_upload", function (e) {
		if (getOcrRequestUseRangeOverYn() == "Y") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "OCR 요청 월별 사용횟수가 초과 되었습니다. (월 1000회)", null, null, null);
	 		return false;
		}
		
		var fileList = document.getElementById('input_file').files;
	 	var fileListLen = fileList.length;
	 	
	 	if (fileListLen == 0) {
	 		libraryFunc.createDialog("Alert", null, null, null, null, "알림", "첨부파일이 선택되지 않았습니다.", null, null, null);
	 		return false;
	 	}
		
	 	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "업로드를 진행 하시겠습니까?", null, uploadFilesConfirm, null);
	});
	
</script>