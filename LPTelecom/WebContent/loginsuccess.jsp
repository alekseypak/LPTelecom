<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login successful!</title>
</head>
<body>

	<h1>LPTelecom</h1>

	<h2>Hello, ${customer.name}!</h2>
<hr>
	<c:out value="${message}" />
<hr>

	<form method="post" action="/LPTelecom/ControllerRename">
		<h3>Tired of your old name?</h3>
		<input type="hidden" name="email" value="${customer.email}"> <label
			for="new_name">Enter new name: </label> <input id="new_name"
			type="text" name="new_name"> <input type="submit"
			value="Rename">
	</form>

	<hr>
	<c:if test="${fn:length(invoices) gt 0}">
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
					<p>Status: Payed</p>
				</c:if> <c:if test="${!invoice.payed}">
					<p>
						Status: <strong>Not payed!</strong>
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
				<form method="post" action="/LPTelecom/ControllerAddRemoveInvoice">
					<p>
						Perhaps you don't want it anymore? <input type="hidden"
							name="email" value="${customer.email}"> <input
							type="hidden" name="action_type" value="remove"> <input
							type="hidden" name="service_id"
							value="${invoice.invoiceTelecomService.id}"> <input
							type="submit" value="Unsubscribe">
					</p>
				</form>
			</li>

		</c:forEach>
	</ul>
	</c:if>
	<c:if test="${fn:length(invoices) == 0}">
	<h3>You don't have any invoices!</h3>
	</c:if>
	<hr>
	<form method="post" action="/LPTelecom/ControllerLogout">
		<input type="submit" value="Logout">
	</form>
</body>
</html>