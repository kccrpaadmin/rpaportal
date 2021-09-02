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
				<div class="location_title">과제건의 검토의견 작성</div>	
			</div>
			<div class="location_right">
				<span class="location_home">홈</span>
				<span class="location_arrow">RPA 소식</span>
				<span class="location_arrow">RPA 과제 건의함</span>
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
	                    <th class="detail_th_l">진행상태</th>
	                    <td class="detail_td_l">${statusCdComboBox}</td>
	                    <th class="detail_th_l">요청일시</th>
	                    <td class="detail_td_l">${outProposalVO.regDate}</td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">요청부서</th>
	                    <td class="detail_td_l">${outProposalVO.proposalDeptNm}</td>
	                    <th class="detail_th_l">요청자</th>
	                    <td class="detail_td_l">${outProposalVO.regUserNm}</td>
	                </tr>
	                 <tr>
	                    <th class="detail_th_l">구분</th>
	                    <td class="detail_td_l" colspan="3">${outProposalVO.menuNm}</td>	                    
	                </tr>
	                <tr>
	                    <th class="detail_th_l">제목</th>
	                    <td class="detail_td_l"colspan="3">${outProposalVO.proposalNm}</td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l" >내용</th>
	                    <td class="detail_td_l"colspan="3"><div style="min-height:250px;'">${outProposalVO.proposalContent}</div></td>
	                </tr>	  
	                <tr>
	                    <th class="detail_th_l" >검토의견</th>
	                    <td class="detail_td_l"colspan="3"><textarea class="txt_box_proposalContent" id="review_content" rows="12" >${outProposalVO.reviewContent}</textarea></td>
	                </tr>	               
	            </tbody>
	        </table> 
	    </div>
	    <br>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_save">저장</a>
	    </div>	    
   	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a href="/Proposal/ListProposal.do"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	var proposalNo = "${outProposalVO.proposalNo}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		$("#status_cd").val("${outProposalVO.statusCd}");		
	});
	
	// 과제 건의 내용 수정
    function saveProposalReview(pProposalNo, pStatusCd, pReviewContent, pRegUserId) {
    	$.ajax({
			url: "/AjaxProposal/SaveProposalReview.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "proposalNo": pProposalNo, "statusCd":pStatusCd, "reviewContent": pReviewContent, "regUserId": pRegUserId}),
		    dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "저장되었습니다.", null, callbackSaveProposalReview);
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
	
	// 과제 건의 저장 후 목록 페이지로 이동
	function callbackSaveProposalReview(){
		window.location.href = "/Proposal/ListProposal.do";
	}	
	
	// 저장 전, 확인 함수
	function saveProposalReviewConfirm() {			
		var proposalNo = "${outProposalVO.proposalNo}"
		var statusCd = $("#status_cd").val();
		var reviewContent = $("#review_content").val();
		var regUserId = commonFunc.certInfo.empNo;
		
		saveProposalReview(proposalNo, statusCd, reviewContent, regUserId);		
	}
		
	// 저장 버튼 클릭 이벤트
	$(document).on("click", "#btn_save", function (e) {								
		// 내용이 입력되지 않은 경우 
		if (commonFunc.getCheckNullYn($("#status_cd").val()) == "Y") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "진행상태를 입력하지 않았습니다.", null, null);
			return false;
		}
		
    	if (commonFunc.getCheckNullYn($("#review_content").val()) == "Y") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "검토의견을 입력하지 않았습니다.", null, null);
			return false;
		}
    	
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장 하시겠습니까?", null, saveProposalReviewConfirm);    	
    });	
	
</script>