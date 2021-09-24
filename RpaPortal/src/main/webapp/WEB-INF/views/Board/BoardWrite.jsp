<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<form id="frm" name="frm" action="/Board/BoardWrite.do" method="post" enctype="multipart/form-data">
		<!-- 컨텐츠 -->
		<div class="contents">
			<!-- 로케이션 -->
			<div class="location_box">
				<div class="location_left">
					<div class="location_title">공지사항 작성</div>	
				</div>
				<div class="location_right">
					<span class="location_home">홈</span>
					<span class="location_arrow">RPA 소식</span>
					<span class="location_arrow">공지사항</span>
				</div>
			</div>
			<!-- 제목 -->
			<!-- 상세영역 -->
		    <div class="proposalwrite_box">
	        	<table class="detail_tbl">
		            <caption>상세영역</caption>
		            <colgroup>
		                <col style="width:15%;" />
		                <col />
		            </colgroup>
		            <tbody>
		                <tr>
		                    <th class="detail_th_c">제목</th>
		                    <td class="detail_td_l"><input type="text" class="txt_box_proposalNm"  id="board_nm" name="boardNm" value="${outBoardVO.boardNm}"/></td>
		                </tr>
		                <tr>
		                    <th class="detail_th_c" >내용</th>
		                    <td class="detail_td_l"><textarea class="txt_box_proposalContent" id="board_content" name="boardContent" rows="20" ><c:out value="${outBoardVO.boardContent}" /></textarea></td>
		                </tr>	  	                            
		            </tbody>
		        </table>
		        <input type="hidden" id="reg_user_id" name="regUserId" />
		        <input type="hidden" id="seq" name="seq" value="${outBoardVO.seq}" />    
		        <input type="hidden" id="save_mode" name="saveMode" value="${saveMode}" />    
		    </div>	   
		    <!-- 첨부영역 -->
		    <div class='file_control_box'>
	    		${attIdFileBox}
		    </div>
		    <!-- 버튼영역 -->
		    <div class="btn_box">
		    	 <a class="btn_common1" id="btn_save">저장</a>  	
		    </div>	    
	   	    <!-- 버튼영역 -->
		    <div class="btn_box">
		    	<a href="/Board/ListBoard.do"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
		    </div>
		</div>
	</form>
</div>

<script type="text/javascript">
	
	// 전역 변수	
 	var fileListArr = new Array();
 	var fileArr = new Array();
 		
	// 페이지 로드 
	$(document).ready(function (e) {
		$("#reg_user_id").val(commonFunc.certInfo.empNo);
	});
			
    // 저장 함수
	function saveBoardWrite() {		
		if (pOption.sdBtnKey == "o") {
			$("#frm").submit();
		}		
	}
	
	// 저장 버튼 클릭 이벤트
	$(document).on("click", "#btn_save", function (e) {								
		// 내용이 입력되지 않은 경우
		if (commonFunc.getCheckNullYn($("#board_nm").val()) == "Y") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "제목을 입력하지 않았습니다.", null, null, null);
			return false;
		}
    	
    	if (commonFunc.getCheckNullYn($("#board_content").val()) == "Y") {
			libraryFunc.createDialog("Alert", null, null, null, null, "알림", "내용을 입력하지 않았습니다.", null, null, null);
			return false;
		}
    	
    	libraryFunc.createDialog("Confirm", null, null, null, null, "알림", "저장 하시겠습니까?", null, saveBoardWrite, null);    	
    });		
	
</script>