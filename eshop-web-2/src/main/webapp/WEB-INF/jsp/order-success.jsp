<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Commande passée</title>
</head>
<body>
	<c:import url ="header.jsp"/> 
	
	<h1>Votre commande est passée: ${order.number}</h1>
</body>
</html>