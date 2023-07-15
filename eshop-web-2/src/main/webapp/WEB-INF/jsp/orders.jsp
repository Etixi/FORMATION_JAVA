<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes commandes</title>
</head>
<body>

    <c:import url="header.jsp"/>

    <h1>Mes commandes</h1>

    <c:if test="${!empty orders}">
        <ul>
            <c:forEach items="${orders}" var="order">
                <li>
                    <div>
                        <fmt:parseDate pattern="yyyy-MM-dd" value="${order.created}" var="created"/>
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${created}" var="createdDate"/>
                        <h3>Commande ${order.number}, passée le ${createdDate}</h3>
                        <ul>
                            <c:forEach items="${order.items}" var="item">
                                <li>
                                    <div>
                                        <img src="image/${item.book.imageId}" width="100">
                                        ${item.book.title}  <fmt:formatNumber pattern="0.00" value="${item.unitPrice}"/>  € ${item.quantity}
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                        <strong>Total : <fmt:formatNumber pattern="0.00" value="${order.totalAmount}"/> €</strong>
                    </div>
                </li>

            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${empty orders}">
        Vous n'avez pas de commande dans votre historique
    </c:if>


</body>
</html>