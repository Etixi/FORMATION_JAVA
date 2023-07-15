<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="login.title"/></title>
</head>
<body>

	 <c:import url="header.jsp"/>
	
		<h1>
			<fmt:message key="login.title"/>
		</h1>

		<form action="login" method="post">
			<label for="usernameInput"><fmt:message key="login.username"/></label>
			<input id="usernameInput" type="text" name="username"> <label
				for="passwordInput"><fmt:message key="login.password"/></label> <input
				id="passwordInput" type="password" name="password">
			<button type="submit">
				<fmt:message key="login.sigin"/>
			</button>
		</form>
		<c:if test="${! empty error}">
			<div style="color: red;">
				<fmt:message key="login.failed"/>
			</div>
		</c:if>
	
</body>
</html>