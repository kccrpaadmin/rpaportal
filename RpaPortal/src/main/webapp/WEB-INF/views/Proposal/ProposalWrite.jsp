<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 컨테이너 -->
<div id="container">
	<form id="frm" name="frm" action="/Proposal/ProposalWrite.do" method="post" enctype="multipart/form-data">
		<!-- 컨텐츠 -->
		<div class="contents">
			<!-- 로케이션 -->
			<div class="location_box">
				<div class="location_left">
					<div class="location_title">과제 건의 작성</div>	
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
		                    <td class="detail_td_l"><input type="text" class="txt_box_proposalDeptNm" id="proposal_dept_nm" disabled/></td>
		                    <th class="detail_th_l">요청자</th>
		                    <td class="detail_td_l" ><input type="text" class="txt_box_proposalUserNm"  id="proposal_user_nm" name="proposal_user_nm" disabled/></td>
		                </tr>
		                <tr>
		                    <th class="detail_th_l">구분</th>
		                    <td class="detail_td_l"colspan="3">${menuCdComboBox}</td>	                    
		                </tr>
		                <tr>
		                    <th class="detail_th_l">제목</th>
		                    <td class="detail_td_l"colspan="3"><input type="text" class="txt_box_proposalNm"  id="proposal_nm" name="proposalNm" value="${outProposalVO.proposalNm}"/></td>
		                </tr>
		                <tr>
		                    <th class="detail_th_l" >내용</th>
		                    <td class="detail_td_l"colspan="3"><textarea class="txt_box_proposalContent" id="proposal_content" name="proposalContent" rows="20" ><c:out value="${outProposalVO.proposalContent}" /></textarea></td>
		                </tr>	  	                            
		            </tbody>
		        </table>	 
		        <input type="hidden" id="emp_no" name="empNo" />
		        <input type="hidden" id="proposal_dept_cd" name="proposalDeptCd" />       
		        <input type="hidden" id="reg_user_id" name="regUserId" />           
		        <input type="hidden" id="proposal_no" name="proposalNo" value="${outProposalVO.proposalNo}" />    
		    </div>	   
		    
		    <%-- ${fileListBoxAttId} --%>
		    <div class='file_control_box'>
			    <div class='file_control' style='float:left;width:49%'>
			    	<input type='hidden' name='AttId' value=''>
			    	<div class='file_header'>
			    		<div class='file_header_left'>제목</div>
			    		<div class='file_header_right'><a class='btn_common2' id='btn_add_AttId'>파일추가</a></div>
			    	</div>
			    	<div class='file_body'>
			    		<h1 class='file_h1'>파일명</h1>
			    		<div class='file_body_scroll'>
					    	<table class='file_tbl'>
				    			<colgroup>
				    				<col width='70%' />
				    				<col width='' />
				    			</colgroup>
						    	<tbody id='file_tbody_AttId'>
					    			<tr>
					    				<td class='file_tbl_td_l'>
					    					<a href='/FileDownload/Download.do?attId=424242&seq=1'>qwqqwqwqw</a>
					    					<input type='hidden' name='AttIdSavedSeqs' value='1' />
					    				</td>
					    				<td class='file_tbl_td_r'>
					    					<a class='btn_common2' id='btn_delete_AttId'>파일삭제</a>
					    				</td>
					    			</tr>
					    			<tr>
					    				<td class='file_tbl_td_l'>
					    					<a href='/FileDownload/Download.do?attId=424242&seq=1'>qwqqwqwqw</a>
					    					<input type='hidden' name='AttIdSavedSeqs' value='1' />
					    				</td>
					    				<td class='file_tbl_td_r'>
					    					<a class='btn_common2' id='btn_delete_AttId'>파일삭제</a>
					    				</td>
					    			</tr>
					    			<tr>
					    				<td class='file_tbl_td_l'>
					    					<a href='/FileDownload/Download.do?attId=424242&seq=1'>qwqqwqwqw</a>
					    					<input type='hidden' name='AttIdSavedSeqs' value='1' />
					    				</td>
					    				<td class='file_tbl_td_r'>
					    					<a class='btn_common2' id='btn_delete_AttId'>파일삭제</a>
					    				</td>
					    			</tr>

						    	</tbody>
						    </table>
					    </div>
				    </div>
			    </div>
			    
		    </div>
		    
		    
		    
		    <!-- 첨부영역 
			<div class="file_box" id="file_box">		
			        <a class="btn_add_file" id="btn_addfile" >파일추가</a>
			        	
					<div class="file_div">
			        	<table class="file_tbl" id="file_table" >
				            <caption>파일영역</caption>				            
				            <tr>
				            	<th class="file_th_l">파일명</th>
				            </tr>	            
				        </table>	        
			    	</div>
			</div>		
			-->			
		    <!-- 버튼영역 -->
		    <div class="btn_box">
		    	 <a class="btn_common1" id="btn_save">저장</a>  	
		    </div>	    
	   	    <!-- 버튼영역 -->
		    <div class="btn_box">
		    	<a href="/Proposal/ListProposal.do"><img src="/resources/imgs/button/btn_box_go_back.png" /></a>
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
		$("#proposal_dept_nm").val(commonFunc.certInfo.deptNm);
		$("#proposal_user_nm").val(commonFunc.certInfo.userNm);
		$("#emp_no").val(commonFunc.certInfo.empNo);
		$("#proposal_dept_cd").val(commonFunc.certInfo.deptCd);
		$("#reg_user_id").val(commonFunc.certInfo.empNo);
		$("#menuId").val("${outProposalVO.menuId}");		
	});
			
    // 저장 전, 확인 함수
	function saveProposalWriteConfirm() {		
		
		if($("#proposal_no").val() == "")
		{
			$("#frm").submit();
			//createProposalWrite(menuId, proposalNm, proposalContent, proposalDeptCd, regUserId);
		}
		else
		{
			updateProposalWrite(proposalNo, menuId, proposalNm, proposalContent);
		}
	}
	
	// 저장 버튼 클릭 이벤트
	$(document).on("click", "#btn_save", function (e) {								
		// 내용이 입력되지 않은 경우
    	if (commonFunc.getCheckNullYn($("#menuId").val()) == "Y") {
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
	
	// 파일 추가 버튼 클릭 이벤트
	$(document).on("click", "#btn_addfile", function (e) {	
		var str = "<tr>"
      				+ "<td class='file_td_l'><input type='file' name='files'/><a class='btn'>삭제하기</a></td>"        
                  	+ "</tr>";
		
        $("#file_table").append(str);		
	});
	

	
</script>