<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
</head>
<body>

<h1>LPTelecom Admin Page</h1>

<c:if test="${fn:length(allInvoices) gt 0}">
		<h3>All invoices:</h3>
		<table style="border: 1px solid black;text-align: center;">
		<tr>
		<th>Customer name</th>
		<th>Customer email</th>
		<th>Customer status</th>
		<th>Invoice service</th>
		<th>Invoice status</th>
		</tr>
			<c:forEach var="invoice" items="${allInvoices}">
			<tr style="border: 1px solid black;">
				<td><c:out value="${invoice.invoiceCustomer.name}"/></td>
				<td><c:out value="${invoice.invoiceCustomer.email}"/></td>
				<td><c:out value="${invoice.invoiceCustomer.status}"/></td>
				<td><c:out value="${invoice.invoiceTelecomService.name}"/></td>
				<td><c:out value="${invoice.status}"/></td>
				<td><c:if test="${!invoice.payed && invoice.invoiceCustomer.status != 'blocked'}">
				<form method="post" action="/LPTelecom/ControllerAdminBlockAndActivateCustomer">
				
							<input type="hidden" name="email" value="${invoice.invoiceCustomer.email}">
							<input type="hidden" name="customer_status" value="${invoice.invoiceCustomer.status}"> 
							<input type="hidden" name="action_type" value="block"> 							 
							<input type="submit" value="Block customer">
				
					</form></c:if>
					<c:if test="${invoice.invoiceCustomer.status == 'blocked'}">
				<form method="post" action="/LPTelecom/ControllerAdminBlockAndActivateCustomer">
							
							<input type="hidden" name="email" value="${invoice.invoiceCustomer.email}">
							<input type="hidden" name="customer_status" value="${invoice.invoiceCustomer.status}"> 
							<input type="hidden" name="action_type" value="activate">  						
							<input type="submit" value="Activate customer">
				
					</form></c:if>
					
					</td>
					<td><form method="post" action="/LPTelecom/ControllerAdminBlockAndActivateCustomer">
				
							<input type="hidden" name="email" value="${invoice.invoiceCustomer.email}">
							<input type="hidden" name="customer_status" value="${invoice.invoiceCustomer.status}"> 
							<input type="hidden" name="action_type" value="block"> 							 
							<input type="submit" value="Force block customer">
				
					</form></td>
			</tr>
			</c:forEach>
		</table>
</c:if>

</body>
</html>