<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<h1>Login</h1>
	<form id="frm" name="frm" action="/Account/Login.do" method="post">
		<input type="text" id="UserId" name="UserId" value="${UserId}" />
		<input type="password" id="Pwd" name="Pwd" value="${Pwd}" />
		<input type="submit" id="btn" name="btn" value="로그인" />
	</form>
</div>
