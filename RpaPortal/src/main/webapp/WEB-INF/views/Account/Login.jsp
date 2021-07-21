<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="login">
	<form id="frm" name="frm" action="/Account/Login.do" method="post">
		<div id="login_header">
			<input type="text" id="UserId" name="UserId" value="${UserId}" />
			<input type="password" id="Pwd" name="Pwd" value="${Pwd}" />
			<input type="submit" id="btn" name="btn" value="" />
		</div>		
	</form>
</div>
