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
				<div class="location_title">과제 건의</div>	
			</div>
			<div class="location_right">
				<span class="location_home">홈</span>
				<span class="location_arrow">RPA 소식</span>
				<span class="location_arrow">RPA 과제 건의함</span>
			</div>
		</div>
		<!-- 제목 -->
		
		<!-- 상세영역 -->
	    <div class="proposalwrite_box">
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
	                    <th class="detail_th_l">요청부서</th>
	                    <td class="detail_td_l"><input type="text" class="txt_box_proposalDeptNm" id="proposalDeptNm" disabled/></td>
	                    <th class="detail_th_l">요청자</th>
	                    <td class="detail_td_l" ><input type="text" class="txt_box_proposalUserNm"  id="proposalUserNm" disabled/></td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l">구분</th>
	                    <td class="detail_td_l"colspan="3">${menuCdComboBox}</td>	                    
	                </tr>
	                <tr>
	                    <th class="detail_th_l">제목</th>
	                    <td class="detail_td_l"colspan="3"><input type="text" class="txt_box_proposalNm"  id="proposal_nm" /></td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l" >내용</th>
	                    <td class="detail_td_l"colspan="3"><textarea class="txt_box_proposalContent" id="proposal_content" rows="20"></textarea></td>
	                </tr>	  	                            
	            </tbody>
	        </table>	        
	    </div>
	    <br>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common" id="btn_write">작성</a>
	    </div>	    
   	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a href="/Proposal/ListProposal.do"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
	
	// 전역 변수
	
	
	// 페이지 로드 
	$(document).ready(function (e) {
		$("#proposalDeptNm").val(commonFunc.certInfo.deptNm);
		$("#proposalUserNm").val(commonFunc.certInfo.userNm);
	});
	
	// 저장 전, 확인 함수
	function saveProposalWriteConfirm() {		
		var menuId = $("#menu_cd").val();
		var proposalNm = $("#proposal_nm").val();
		var proposalContent = $("#proposal_content").val();
		var proposalDeptCd = commonFunc.certInfo.deptCd;
		var regUserId = commonFunc.certInfo.empNo;
		    
		saveProposalWrite(menuId, proposalNm, proposalContent, proposalDeptCd, regUserId);
        
	}
	
	// 과제 건의 내용 저장
    function saveProposalWrite(pMenuId, pProposalNm, pProposalContent, pProposalDeptCd, pRegUserId) {
    	$.ajax({
			url: "/AjaxProposal/SaveProposalWrite.do",
			type: "POST",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({ "menuId": pMenuId, "proposalNm": pProposalNm, "proposalContent": pProposalContent, "proposalDeptCd": pProposalDeptCd, "regUserId": pRegUserId }),
		    dataType : "json",
	        async: true,
			success: function(data) {
				if (data.status == "Success") {
					libraryFunc.createDialog("Alert", null, null, null, null, "알림", "저장되었습니다.", null, callbackSaveProposalWrite);
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
	function callbackSaveProposalWrite(){
		window.location.href = "/Proposal/ListProposal.do";
	}	
	
	// 작성 버튼 클릭 이벤트
	$(document).on("click", "#btn_write", function (e) {								
		// 내용이 입력되지 않은 경우
    	if (commonFunc.getCheckNullYn($("#menu_cd").val()) == "Y") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "메뉴구분을 선택하지 않았습니다.", null, null);
			return false;
		}
		
    	if (commonFunc.getCheckNullYn($("#proposal_nm").val()) == "Y") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "제목을 입력하지 않았습니다.", null, null);
			return false;
		}
    	
    	if (commonFunc.getCheckNullYn($("#proposal_content").val()) == "Y") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "내용을 입력하지 않았습니다.", null, null);
			return false;
		}
    	
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장 하시겠습니까?", null, saveProposalWriteConfirm);    	
    });
	
</script>