<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="login">
	<form id="frm" name="frm" action="/Account/Login.do" method="post">
		<div id="login_header">
			<input type="text" id="UserId" name="UserId" value="${UserId}" onfocus="focusUserId()" onblur="blurUserId()" />
			<input type="password" id="Pwd" name="Pwd" value="${Pwd}" onfocus="focusPwd()" onblur="blurPwd()"/>
			<input type="submit" id="btn" name="btn" value="" />
		</div>		
	</form>
</div>

<script type="text/javascript">
	function focusUserId(){
		var inputUserId = document.getElementById("UserId");
		
		inputUserId.style.backgroundColor = "white";
		inputUserId.style.top = "302px"
		inputUserId.style.height = "45px"		
	}
	
	function blurUserId(){
		var inputUserId = document.getElementById("UserId");
		
		if(inputUserId.value != ""){
			inputUserId.style.backgroundColor = "white";
			inputUserId.style.top = "304px"
			inputUserId.style.height = "40px"
		}else{
			inputUserId.style.backgroundColor = "transparent";
			inputUserId.style.top = "302px"
			inputUserId.style.height = "45px"
		}		
	}
		
	function focusPwd(){
		var inputPwd = document.getElementById("Pwd");
				
		inputPwd.style.backgroundColor = "white";
		inputPwd.style.top = "379px"
		inputPwd.style.height = "45px"		
	}
	
	function blurPwd(){
		var inputPwd = document.getElementById("Pwd");
		
		if(inputPwd.value != ""){
			inputPwd.style.backgroundColor = "white";
			inputPwd.style.top = "381px"
			inputPwd.style.height = "40px"
		}else{
			inputPwd.style.backgroundColor = "transparent";
			inputPwd.style.top = "381px"
			inputPwd.style.height = "45px"
		}
	}
</script>