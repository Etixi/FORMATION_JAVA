<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<header>
	<img alt="" src="images/eshop.png" width="50"></img>
	
	<nav>
		<a href="home.jsp"><spring:message code="header.home"/></a> 
		
		<a href="search"><spring:message code="header.search"/></a> 
		<a href="basket"><spring:message code="header.basket"/></a> 
		
		
		<c:if test="${!empty customer}">
			<a href="orders"><spring:message code="header.orders"/></a> 
		</c:if>
		
		<a href="login"><spring:message code="header.login"/></a>
		
	</nav>
</header>
