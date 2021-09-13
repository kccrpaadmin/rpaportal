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
				<div class="location_title">상세내용</div>	
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
	                    <td class="detail_td_l">${outProposalVO.statusNm}</td>
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
	                    <td class="detail_td_l"colspan="3"><div style="min-height:150px;'">${outProposalVO.reviewContent}</div></td>
	                </tr>	               
	            </tbody>
	        </table>
	        <br>	   
	        <div class="proposaltitle" style="display:none">
	        	 <div class="proposalnm">${outProposalVO.proposalNm}</div>
	        	 <div class="proposaldetail">
	        	 	<p>구분: ${outProposalVO.menuNm}</p>
	        	 	<p>요청부서: ${outProposalVO.proposalDeptNm}</p>
	        	 	<p>요청자: ${outProposalVO.regUserNm}</p>
	        	 </div>
	        </div>
	        <div class="proposalcontent" style="display:none">${outProposalVO.proposalContent}</div>       
	    </div>
	    <!-- 첨부영역 -->
	    <div class='file_control_box'>
   		    	${attIdFileBox}
	    </div>	    
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common1" id="btn_update" style="display:none">수정</a>
	    	<a class="btn_common1" id="btn_delete" style="display:none">삭제</a>
	    	<a class="btn_common1" id="btn_review" style="display:none">검토의견작성</a>
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
	var attId = "${outProposalVO.attId}";
	
	// 페이지 로드 
	$(document).ready(function (e) {
		enableButtonControl();
		listAttFile(attId);
	});
	
	// 첨부파일 목록 조회
	function listAttFile(pAttId) {
		$.ajax({
			url: "/AjaxProposal/ListAttFile.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "attId": pAttId }),
		    dataType : "json",
	        async: true,
			success: function(listDatas) {
				makeAttTable(listDatas);
			},
			error: function(xhr, status, err) {
				commonFunc.handleErrorMsg(xhr, status, err);
				return false;
			}
		});
	}
	
	// 과제 건의 삭제
    function deleteProposal() {
    	$.ajax({
			url: "/AjaxProposal/DeleteProposal.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "proposalNo": proposalNo }),
		    dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "삭제되었습니다.", null, callbackDeleteProposal);
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
    function makeAttTable(pListDatas) {
    	var fileListLen = pListDatas.data.length;
    	
	 	for (var i = 0; i < fileListLen; i++) {
	 		var str = "<tr id=file" + i + ">"
	 		           +    "<td class='file_td_l'>" + pListDatas.data[i].attId + "</td>"
	 		           +    "<td class='file_td_l'>" + " KB</td>"
	 		           + "</tr>";
	 		$("#file_table").append(str);
	 	}
    }
	
    // 과제 건의 삭제 후 목록 페이지로 이동
	function callbackDeleteProposal(){
		window.location.href = "/Proposal/ListProposal.do";
	}	
    
	// 버튼 활성화, 비활성화 함수
	function enableButtonControl() {		
		if ("${outProposalVO.statusCd}" == "RA013001"){
			if (commonFunc.certInfo.empNo == "${outProposalVO.regUserId}") {
				$("#btn_update").css("display", "inline-block");   
				$("#btn_delete").css("display", "inline-block");   
	        }	
		}		
		 
		if (commonFunc.certInfo.roleType == "ROLE_ADMIN") {
			$("#btn_review").css("display", "inline-block");   
        }		
	}
	
    // 수정 버튼 클릭 이벤트
	$(document).on("click", "#btn_update", function (e) {			
		var mode = "ProposalWrite"
		var saveMode = "U"
		
		window.location.href = "/Proposal/ProposalWrite.do?pProposalNo="+ proposalNo + "&pMode=" + mode + "&pSaveMode=" + saveMode;
	});
	
    // 삭제 버튼 클릭 이벤트
	$(document).on("click", "#btn_delete", function (e) {			
		libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "삭제 하시겠습니까?", null, deleteProposal);    
	});
    
	// 검토의견작성 버튼 클릭 이벤트
	$(document).on("click", "#btn_review", function (e) {			
		var mode = "ProposalReview"
		var saveMode = "U"
		
		window.location.href = "/Proposal/ProposalReview.do?pProposalNo="+ proposalNo + "&pMode=" + mode + "&pSaveMode=" + saveMode;
	});
	
</script>