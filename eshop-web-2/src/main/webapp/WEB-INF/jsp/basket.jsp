<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon Panier</title>
</head>
<body>

<c:import url="header.jsp"/> 
	
	<h1>Mon Panier</h1>
	
	<c:choose>
		<c:when test="${empty basket || empty basket.items}">
		<p>Mon Panier est vide</p>
		</c:when>
		<c:otherwise>
			<table>
				<thead>
					<tr>
						<th>Article</th>
						<th>PU</th>
						<th>Quantité</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${basket.items}" var="item">
						<tr>
							<td>${item.book.title}</td>
							<td>
								<fmt:formatNumber pattern="0.00" value="${item.book.price}"/>
							</td>
							<td>${item.quantity}</td>
						</tr>
					</c:forEach>
				</tbody>
					
					<tfoot>
						<tr><td colspan="2">Total</td><fmt:formatNumber pattern="0.00" value="${basket.totalAmount}"/>&euro;</tr>
					</tfoot>
			</table>
			
			<form method="post">
				<button type="submit">Commander</button>
			</form>
			
			
		</c:otherwise>
	
	</c:choose>
		
		
	
</body>
</html>