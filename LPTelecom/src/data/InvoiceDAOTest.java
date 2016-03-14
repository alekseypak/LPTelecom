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
		statusUpdateTest("john@foo.com", true);

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

	private static void statusUpdateTest(String email, boolean payed) {
		System.out.println("Let's try to change an invoice status.");

		List<Invoice> invoiceList;
		try {
			invoiceList = InvoiceDAO.getInvoicesForEmail(email);
			listGetterWithEmailTest(email);
			if (!invoiceList.isEmpty()) {
				Invoice invoice = invoiceList.get(0);
				try {
					InvoiceDAO.SetInvoiceStatus(invoice, payed ? "payed" : "not payed", payed);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listGetterWithEmailTest(email);
			}

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
