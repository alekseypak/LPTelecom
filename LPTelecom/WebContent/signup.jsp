<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign up page</title>
</head>
<body>

<h1>LPTelecom</h1>

<h2>Customer account creation</h2>

<p>Please fill out all fields:</p>

<form method="post" action="/LPTelecom/ControllerSignUp">
<input type="hidden" name="action" value="dosignup"> <br/>
email: <input type="text" name="email"><br/>
password: <input type="text" name="password"><br/>
name: <input type="text" name="name"><br/>
<input type="submit" value="Sign Up">
</form>

<p>${error_message}</p>
</html>