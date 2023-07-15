<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${book.title}</title>
</head>
<body>

	<c:import url="header.jsp"/>
	<h1>Titre : "${book.title}"</h1>
	
	<div>
	<c:if test="${!empty book.imageId}">
			<img src="image/${book.imageId}" width="80">
	</c:if>

	<div>Prix : <fmt:formatNumber pattern="0.00" value="${book.price}"/>€</div>
	<div>ISBN :"${book.isbn}"</div>

	<fmt:parseDate pattern="yyyy-MM-dd" value="${book.release}" var="release"/>
	<div>Parution :<fmt:formatDate pattern="dd/MM/yyyy" value="${release}"/></div>

		<%-- Balise conditionnelle: c:if, c:when, c:choice, c:otherwise --%>
		<%-- c:forEach : balise d'itération : var, items et select --%>

		<div>
		<c:set var="authors" value="${fn:join(book.authors.stream().map(a->a.getName()).toArray(), ', ')}"></c:set>
			Auteurs:${authors}
		</div>

		<div>
		<c:if test="${!empty book.tags}">
				<%-- 
		<c:forEach items="${book.tags}" var="tag">
			${tag},
			</c:forEach>
		--%>


			<c:set var="tags" value="${fn:join(book.tags.toArray(),', ')}"></c:set>
			Tags:${tags}
		</c:if>
		</div>

		<p>"${book.overview}"</p>
	</div>
	
	<form action="detail" method="post">
    <input type="number" name="quantity" value="1">
    <input type="hidden" name="bookId" value="${book.id}">
    <button type="submit">Ajouter au panier</button>
	</form>
	
</body>
</html>