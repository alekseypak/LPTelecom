<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
</head>
<body>

<h1>LPTelecom</h1>

<h2>Admin access</h2>

<form method="post" action="/LPTelecom/ControllerAdminLogin">
<input type="hidden" name="action" value="dologin">
<input type="text" name="admin_login">
<input type="text" name="admin_password">
<input type="submit" value="Log In">
</form>

<c:if test="${error_message != null}"><p><strong><c:out value="${error_message}" /></strong></p></c:if>
</body>
</html>