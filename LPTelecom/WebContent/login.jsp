<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
</head>
<body>

<h1>LPTelecom</h1>

<h2>Customer access</h2>

<form method="post" action="/LPTelecom/ControllerLogin">
<input type="hidden" name="action" value="dologin">
<input type="text" name="email">
<input type="text" name="password">
<input type="submit" value="Log In">
</form>


</body>
</html>