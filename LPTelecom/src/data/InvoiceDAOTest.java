package data;

import java.sql.SQLException;

public class InvoiceDAOTest {
	public static void main(String[] args) {
		listGetterTest();
	}

	private static void listGetterTest() {
		// Why is this not called getAllTelecomServicesTest? Perhaps it might
		// clash with a pre-rolled test framework.
		System.out.println("Let's try to get all the invoices...");
		try {
			for (Invoice invoice : InvoiceDAO.getAllInvoices()) {
				System.out.printf("%s: %s: %s\n", invoice.getInvoiceCustomer().getName(),
						invoice.getInvoiceTelecomService().getName(), invoice.getStatus());

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
