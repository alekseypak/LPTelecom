package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class InvoiceDAO {
	public static List<Invoice> getAllInvoices() throws SQLException {
		String query = "SELECT * " + "FROM customers JOIN invoices JOIN telecom_services "
				+ "ON customers.customer_id = invoices.customer_id AND invoices.service_id = telecom_services.service_id";
		Connection connection = getConnection();

		PreparedStatement statement = connection.prepareStatement(query);

		ResultSet rs = statement.executeQuery();
		List<Invoice> invoiceList = new LinkedList<>();
		while (rs.next()) {
			// TODO: Some not so nice code duplication here. Has to be a better
			// way.
			int id = rs.getInt("service_id");
			String service_name = rs.getString("service_name");
			String description = rs.getString("descr");
			String service_name_alt = rs.getString("service_name_alt");
			String description_alt = rs.getString("descr_alt");
			TelecomService newService = new TelecomService(id, service_name, service_name_alt, description,
					description_alt);
			String email = rs.getString("email");
			String password = rs.getString("password");
			String name = rs.getString("name");
			String status = rs.getString("status");
			Customer customer = new Customer(email, password, name, status);
			boolean payed = rs.getBoolean("payed");
			String invoice_status = rs.getString("invoice_status");
			invoiceList.add(new Invoice(customer, newService, payed, invoice_status));
		}
		rs.close();
		statement.close();
		return invoiceList;

	}

	public static List<Invoice> getInvoicesForCustomer(Customer customer) throws SQLException {
		return getInvoicesForEmail(customer.getEmail());
	}

	public static List<Invoice> getInvoicesForEmail(String email) throws SQLException {
		String query = "SELECT * FROM customers JOIN invoices JOIN telecom_services "
				+ "ON customers.customer_id = invoices.customer_id AND invoices.service_id = telecom_services.service_id "
				+ "WHERE email = ? ORDER BY customers.name";
		Connection connection = getConnection();

		PreparedStatement statement = connection.prepareStatement(query);

		statement.setString(1, email);
		ResultSet rs = statement.executeQuery();
		List<Invoice> invoiceList = new LinkedList<>();
		while (rs.next()) {
			// TODO: Some not so nice code duplication here. Has to be a better
			// way.
			int id = rs.getInt("service_id");
			String service_name = rs.getString("service_name");
			String description = rs.getString("descr");
			String service_name_alt = rs.getString("service_name_alt");
			String description_alt = rs.getString("descr_alt");
			TelecomService newService = new TelecomService(id, service_name, service_name_alt, description,
					description_alt);
			// String email = rs.getString("email");
			String password = rs.getString("password");
			String name = rs.getString("name");
			String status = rs.getString("status");
			Customer customer = new Customer(email, password, name, status);
			boolean payed = rs.getBoolean("payed");
			String invoice_status = rs.getString("invoice_status");
			invoiceList.add(new Invoice(customer, newService, payed, invoice_status));
		}
		rs.close();
		statement.close();
		return invoiceList;

	}

	public static boolean SetInvoiceStatus(Invoice invoice, String status, boolean payed) throws SQLException {
		String query = "UPDATE invoices SET invoice_status=?, payed=? WHERE customer_id=? AND service_id=?";
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, status);
		statement.setInt(2, payed ? 1 : 0);
		// This is correct!
		// > The driver converts this to an SQL BIT or BOOLEAN value when it
		// > sends it to the database.
		// https://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html#setBoolean(int,%20boolean)
		// We have a TinyInt(1) instead.>
		statement.setInt(3, CustomerDAO.getCustomerId(invoice.getInvoiceCustomer()));
		statement.setInt(4, invoice.getInvoiceTelecomService().getId());
		int result = statement.executeUpdate();

		statement.close();
		return result == 1;
	}

	private static Connection getConnection() {
		Connection connection = ConnectionProviderMockup.getConnection();
		return connection;
	}

	public static boolean setInvoiceStatus(Invoice invoice, boolean payed) throws SQLException {
		return SetInvoiceStatus(invoice, payed ? "payed" : "not payed", payed);
	}

	public static boolean deleteInvoice(Invoice badInvoice) throws SQLException {
		String query = "DELETE FROM invoices WHERE customer_id = ? AND service_id = ?";
		Connection connection = getConnection();

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, CustomerDAO.getCustomerIdByEmail(badInvoice.getInvoiceCustomer().getEmail()));
		statement.setInt(2, badInvoice.getInvoiceTelecomService().getId());
		int result = statement.executeUpdate();
		statement.close();
		return result == 1;
	}

	public static boolean insertInvoice(Invoice newInvoice) throws SQLException {
		// TODO: might be a good idea to run customerWithEmailExists...
		String query = "INSERT INTO invoices (customer_id, service_id, payed, invoice_status) VALUES (?, ?, ?, ?)";
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(query);

		int customer_id = CustomerDAO.getCustomerId(newInvoice.getInvoiceCustomer());
		statement.setInt(1, customer_id);
		statement.setInt(2, newInvoice.getInvoiceTelecomService().getId());
		statement.setInt(3, newInvoice.isPayed() ? 1 : 0);
		statement.setString(4, newInvoice.getStatus());
		int result = statement.executeUpdate();
		statement.close();
		return result == 1;
	}

}
