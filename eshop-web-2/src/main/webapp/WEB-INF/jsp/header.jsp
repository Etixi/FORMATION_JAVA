<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<header>
	<img alt="" src="images/eshop.png" width="50"></img>
	
	<nav>
		<a href="home.jsp"><fmt:message key="header.home"/></a> 
		
		<a href="search"><fmt:message key="header.search"/></a> 
		<a href="basket"><fmt:message key="header.basket"/></a> 
		
		
		<c:if test="${!empty customer}">
			<a href="orders"><fmt:message key="header.orders"/></a> 
		</c:if>
		
		<a href="login"><fmt:message key="header.login"/></a>
		
	</nav>
</header>
