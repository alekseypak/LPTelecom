<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All our services</title>
</head>
<body>
	<h1>LPTelecom</h1>
	<c:if test="${lang == 'eng'}">
	<ul>
		<c:forEach var="element" items="${all_services}">

			<li>
				<h4>
					<c:out value="${element.name}" />										 
				</h4>
				<c:out value="${element.descr}" />				
			</li>

		</c:forEach>
	</ul>
	</c:if>
	
	<c:if test="${lang == 'rus'}">
	<ul>
		<c:forEach var="element" items="${all_services}">

			<li>
				<h4>
					<c:out value="${element.name_alt}" />										 
				</h4>
				<c:out value="${element.descr_alt}" />				
			</li>

		</c:forEach>
	</ul>
	</c:if>
</body>
</html>