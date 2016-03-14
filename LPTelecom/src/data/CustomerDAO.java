package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
	// private Connection connection;

	public CustomerDAO() {
	}

	private static Connection getConnection() {
		Connection connection = ConnectionProviderMockup.getConnection();
		return connection;
	}

	public Customer getCustomer(String email) throws SQLException {
		String query = "SELECT * FROM customers WHERE email=?";
		Connection connection = getConnection();
		// TODO: connection might be null!
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, email);
		Customer customer = null;

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			String password = rs.getString("password");
			String name = rs.getString("name");
			String status = rs.getString("status");
			customer = new Customer(email, password, name, status);
		}
		return customer;

	}

	public static boolean customerWithEmailExists(String email) throws SQLException {
		String query = "SELECT count(*) AS count FROM customers WHERE email=?";
		Connection connection = getConnection();
		// TODO: connection might be null!
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, email);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) {
			// count should never be more than 1.
			int c = rs.getInt("count");
			// System.out.println(email + " " + c);
			return c > 0;
		}

		return false;
	}

	public boolean addCustomer(Customer newCustomer) throws SQLException {
		// TODO: might be a good idea to run customerWithEmailExists...
		String query = "INSERT INTO customers (email, password, name, status) VALUES (?, ?, ?, ?)";
		Connection connection = getConnection();

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, newCustomer.getEmail());
		statement.setString(2, newCustomer.getPassword());
		statement.setString(3, newCustomer.getName());
		statement.setString(4, newCustomer.getStatus());
		statement.executeUpdate();
		statement.close();
		return true;
	}

	public boolean deleteCustomer(Customer badCustomer) throws SQLException {
		String query = "DELETE FROM customers WHERE email = ?";
		Connection connection = getConnection();

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, badCustomer.getEmail());
		statement.executeUpdate();
		statement.close();
		return true;
	}

	public static int getCustomerId(Customer customer) throws SQLException {
		return getCustomerIdByEmail(customer.getEmail());
	}

	public static int getCustomerIdByEmail(String email) throws SQLException {
		String query = "SELECT customer_id FROM customers WHERE email=?";
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, email);
		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			int customer_id = rs.getInt("customer_id");
			return customer_id;
		}
		return -1;
	}

	public static boolean renameCustomer(Customer customer, String new_name) throws SQLException {

		// String query = "UPDATE customers SET name=? WHERE email=?";
		// This doesn't work. I should supply a primary key in the WHERE clause
		// or mess with the DBMS (which I don't do because I want to solve it
		// with the default DBMS settings).
		// The following query is inspired by
		// http://stackoverflow.com/questions/4429319/you-cant-specify-target-table-for-update-in-from-clause

		String query = "UPDATE customers " + "SET name = ? "
				+ "WHERE customer_id = (SELECT customer_id FROM (SELECT * FROM customers) "
				+ "AS same_customers WHERE email = ?);";

		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(query);

		statement.setString(1, new_name);
		statement.setString(2, customer.getEmail());
		System.out.println(statement.toString());
		int result = statement.executeUpdate();
		statement.close();
		return result == 1;

	}

	public static boolean changeCustomerStatus(String email, String newStatus) throws SQLException {
		String query = "UPDATE customers " + "SET status = ? "
				+ "WHERE customer_id = (SELECT customer_id FROM (SELECT * FROM customers) "
				+ "AS same_customers WHERE email = ?);";

		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(query);

		statement.setString(1, newStatus);
		statement.setString(2, email);
		System.out.println(statement.toString());
		int result = statement.executeUpdate();
		statement.close();
		return result == 1;
	}

	public static boolean changeCustomerStatus(Customer customer, String newStatus) throws SQLException {
		return changeCustomerStatus(customer.getEmail(), newStatus);

	}
}
