<%@page import="com.infotel.eshop.dto.CustomerDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
	${customer }
	
		
		<p>
		<spring:message code="home.welcome" >
			<spring:argument value="${customer.firstName}"/> 
			<spring:argument value="${customer.lastName}"/> 
		</spring:message>
		</p>
	</c:if>
</body>
</html>