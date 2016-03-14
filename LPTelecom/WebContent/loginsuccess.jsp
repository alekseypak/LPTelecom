<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login successful!</title>
</head>
<body>

	<h1>LPTelecom</h1>

	<h2>Hello, ${customer.name}!</h2>

	<c:out value="${message}" />

	
	<form method="post" action="/LPTelecom/ControllerRename">
	<h3>Tired of your old name?</h3>
		<input type="hidden" name="email" value="${customer.email}"> <label
			for="new_name">Enter new name: </label> <input id="new_name"
			type="text" name="new_name"> <input type="submit"
			value="Rename">
	</form>


	

	<h3>Your invoices:</h3>
	<ul>
		<c:forEach var="invoice" items="${invoices}">

			<li>
				<h4>
					<c:out value="${invoice.invoiceTelecomService.name}" />
				</h4>
				<p>
					<em><c:out value="${invoice.invoiceTelecomService.descr}" /></em>
				</p> <c:if test="${invoice.payed}">
					<p>Payed</p>
				</c:if> <c:if test="${!invoice.payed}">
					<p>
						<strong>Not payed!</strong>
					</p>
					<div>
						<%-- <p>Hidden parameters: email: ${customer.email}, service_id: ${invoice.invoiceTelecomService.id}</p> --%>
						<form method="post" action="/LPTelecom/ControllerPayment">
							<input type="hidden" name="email" value="${customer.email}">
							<input type="hidden" name="service_id"
								value="${invoice.invoiceTelecomService.id}"> <input
								type="hidden" name="payed_now" value="true"> <label
								for="cc_number">Enter you CC card number: </label> <input
								id="cc_number" type="text" name="cc_number"> <input
								type="submit" value="Pay">
						</form>
					</div>
				</c:if>
			</li>

		</c:forEach>
	</ul>

</body>
</html>