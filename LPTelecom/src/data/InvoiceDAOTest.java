package data;

import java.sql.SQLException;
import java.util.List;

public class InvoiceDAOTest {
	public static void main(String[] args) {
		listGetterTest();
		System.out.println();
		listGetterWithEmailTest("a@a.com");
		CustomerDAO customerDAO = new CustomerDAO();
		Customer john;
		try {
			john = customerDAO.getCustomer("john@foo.com");
			listGetterWithCustomerTest(john);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		statusUpdateTest("john@foo.com");

		DeleteInvoiceTest();

	}

	private static void listGetterTest() {
		// Why is this not called getAllTelecomServicesTest? Perhaps it might
		// clash with a pre-rolled test framework.
		System.out.println("Let's try to get all the invoices...");
		try {
			List<Invoice> allInvoices = InvoiceDAO.getAllInvoices();
			System.out.printf("There are %d of them.\n", allInvoices.size());
			for (Invoice invoice : allInvoices) {
				System.out.printf("%s: %s: %s\n", invoice.getInvoiceCustomer().getName(),
						invoice.getInvoiceTelecomService().getName(), invoice.getStatus());

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void listGetterWithEmailTest(String email) {
		// TODO Auto-generated method stub
		System.out.println("Let's try to get all the invoices for " + email + "...");
		try {
			for (Invoice invoice : InvoiceDAO.getInvoicesForEmail(email)) {
				System.out.printf("%s: %s: %s\n", invoice.getInvoiceCustomer().getName(),
						invoice.getInvoiceTelecomService().getName(), invoice.getStatus());

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void listGetterWithCustomerTest(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("Let's try to get all the invoices for a customer: " + customer.getName() + "...");
		try {
			for (Invoice invoice : InvoiceDAO.getInvoicesForCustomer(customer)) {
				System.out.printf("%s: %s: %s\n", invoice.getInvoiceCustomer().getName(),
						invoice.getInvoiceTelecomService().getName(), invoice.getStatus());

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void statusUpdateTest(String email) {
		System.out.println("Let's try to change an invoice status.");

		List<Invoice> invoiceList;
		try {
			invoiceList = InvoiceDAO.getInvoicesForEmail(email);
			if (!invoiceList.isEmpty()) {
				Invoice invoice = invoiceList.get(0);
				System.out.printf("Its billed to %s for %s and the status is %s.\n",
						invoice.getInvoiceCustomer().getName(), invoice.getInvoiceTelecomService().getName(),
						invoice.getStatus());
				InvoiceDAO.setInvoiceStatus(invoice, !invoice.isPayed());
				invoiceList = InvoiceDAO.getInvoicesForEmail(email);
				Invoice hopefully_update_invoice = invoiceList.get(0);
				System.out.printf("Its billed to %s for %s and the status is %s.\n",
						hopefully_update_invoice.getInvoiceCustomer().getName(),
						hopefully_update_invoice.getInvoiceTelecomService().getName(),
						hopefully_update_invoice.getStatus());
			}

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static Invoice createInvoiceTest(Customer customer, TelecomService telecomService) {
		Invoice newInvoice = new Invoice(customer, telecomService);
		return createInvoiceTest(newInvoice);
	}

	private static Invoice createInvoiceTest(Invoice newInvoice) {
		// TODO Auto-generated method stub
		System.out.printf("Let's try to create a new Invoice for %s and %s.\n",
				newInvoice.getInvoiceCustomer().getName(), newInvoice.getInvoiceTelecomService().getName());
		try {

			InvoiceDAO.insertInvoice(newInvoice);
			return newInvoice;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static void createAndDeleteInvoiceTest(Invoice invoice) {
		try {
			createInvoiceTest(invoice);

			listGetterWithCustomerTest(invoice.getInvoiceCustomer());
			System.out.println("Let's try to delete it now.");
			InvoiceDAO.deleteInvoice(invoice);
			listGetterWithCustomerTest(invoice.getInvoiceCustomer());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void DeleteInvoiceTest() {
		CustomerDAO customerDAO = new CustomerDAO();
		try {
			Customer customer = customerDAO.getCustomer("john@foo.com");
			TelecomService telecomService = TelecomServiceDAO.getAllTelecomServices().get(1);
			createAndDeleteInvoiceTest(new Invoice(customer, telecomService));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
