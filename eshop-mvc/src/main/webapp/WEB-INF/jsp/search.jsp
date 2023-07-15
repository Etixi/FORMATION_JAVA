<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recherche</title>
</head>
<body>

<c:import url = "header.jsp"/>
	<h1>Recherche</h1>
	
		<form>
		<label for="keywordInput">Votre recherche</label>;
		<input id="keywordInput" type="search" name="keyword">
		<select name="catId">;
		<option value="-1">--- Toutes les catégories ---</option>

		
			<c:forEach items="${categories}" var="c">
			<option value="${c.id}">${c.name}</option>
			</c:forEach>
		</select>
		<button type="submit">Chercher :</button>
		</form>

<c:if test = "${books != null}">
<c:choose>
	<c:when test="${empty books}"> 
	<p>Pas de résultat</p>
	</c:when>
	<c:otherwise>
			<p>Livres  trouvés : ${books.size()}</p>
			<ul>

				<c:forEach items="${books}" var="b">
					<li> 
					<div>
						<img src="image/${b.imageId}" width=80">
						<a href="detail?id=${b.id}">${b.title} - ${b.price}</a>
					</div>
					</li>
				</c:forEach>	
			
			</ul>
		</c:otherwise>
	</c:choose>
</c:if>

</body>
</html>