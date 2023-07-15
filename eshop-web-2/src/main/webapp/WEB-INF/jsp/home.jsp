<%@page import="com.infotel.eshop.dto.CustomerDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="home.title"/></title>

</head>

<body>
	<c:import url="header.jsp"/>
	<h1>Accueil</h1>
	<c:if test="${!empty customer}">
		<p>Bienvenue ${customer.firstName} ${customer.lastName} !</p>
		<p>
		<fmt:message key="home.welcome">
			<fmt:param value="${customer.firstName}"/> 
			<fmt:param value="${customer.lastName}"/> 
		</fmt:message>
		</p>
	</c:if>
</body>
</html>