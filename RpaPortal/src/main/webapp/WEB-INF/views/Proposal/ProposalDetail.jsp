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
	                    <th class="detail_th_l">구분</th>
	                    <td class="detail_td_l">${outProposalVO.menuNm}</td>
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
	                    <th class="detail_th_l">제목</th>
	                    <td class="detail_td_l"colspan="3">${outProposalVO.proposalNm}</td>
	                </tr>
	                <tr>
	                    <th class="detail_th_l" >내용</th>
	                    <td class="detail_td_l"colspan="3">${outProposalVO.proposalContent}</td>
	                </tr>	  
	                <tr>
	                    <th class="detail_th_l" >검토의견</th>
	                    <td class="detail_td_l"colspan="3">${outProposalVO.reviewContent}</td>
	                </tr>	               
	            </tbody>
	        </table>
	        <br><br>
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
	    <br>
	    <!-- 버튼영역 -->
	    <div class="btn_box">
	    	<a class="btn_common" id="btn_immediate_call">즉시실행</a>
	    	<a class="btn_common" id="btn_schedule_open">예약실행</a>
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
		
	});
	
</script>