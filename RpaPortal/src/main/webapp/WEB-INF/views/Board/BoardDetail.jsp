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
				<div class="location_title">공지사항</div>	
			</div>
			<div class="location_right">
				<span class="location_home">홈</span>
				<span class="location_arrow">RPA 소식</span>
				<span class="location_arrow">공지사항</span>
			</div>
		</div>
		<!-- 제목 -->
		<div class="title">상세정보</div>
		<!-- 상세영역 -->
	    <div class="proposaldetail_box">
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
	                	<th class="detail_th_l">구분</th>
	                    <td class="detail_td_l">${outBoardVO.boardTypeNm}</td>
	                    <th class="detail_th_l">작성일자</th>
	                    <td class="detail_td_l">${outBoardVO.regDate}</td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">제목</th>
	                    <td class="detail_td_l"colspan="3">${outBoardVO.boardNm}</td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l" >내용</th>
	                    <td class="detail_td_l"colspan="3"><div style="min-height:350px;'">${outBoardVO.boardContent}</div></td>
	                </tr>              
	            </tbody>
	        </table>
	    </div>
	    <!-- 첨부영역 -->
	    <div class='file_control_box'>
    		${attIdFileBox}
	    </div>	    
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_update" style="display:none">수정</a>
	    	<a class="btn_common1" id="btn_delete" style="display:none">삭제</a>
	    </div>	    
   	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a href="/Board/ListBoard.do"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var seq = "${outBoardVO.seq}";
	var attId = "${outBoardVO.attId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		enableButtonControl();
	});
	
	// 공지사항 삭제
    function deleteBoard() {
    	$.ajax({
			url: "/AjaxBoard/DeleteBoard.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "seq": seq }),
		    dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "삭제되었습니다.", null, callbackDeleteBoard);
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
	
    // 과제 건의 삭제 후 목록 페이지로 이동
	function callbackDeleteBoard(){
		window.location.href = "/Board/ListBoard.do";
	}	
    
	// 버튼 활성화, 비활성화 함수
	function enableButtonControl() {				
		if (commonFunc.certInfo.roleType == "ROLE_ADMIN") {
			$("#btn_update").css("display", "inline-block");   
			$("#btn_delete").css("display", "inline-block");   
        }		
	}
	
    // 수정 버튼 클릭 이벤트
	$(document).on("click", "#btn_update", function (e) {			
		var mode = "BoardWrite"
		var saveMode = "U"
		
		window.location.href = "/Board/BoardWrite.do?pSeq="+ seq + "&pMode=" + mode + "&pSaveMode=" + saveMode;
	});
	
    // 삭제 버튼 클릭 이벤트
	$(document).on("click", "#btn_delete", function (e) {			
		libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "삭제 하시겠습니까?", null, deleteBoard);    
	});
    
</script>