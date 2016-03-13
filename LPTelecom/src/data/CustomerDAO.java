package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
	// private Connection connection;

	public CustomerDAO() {
	}

	public Customer getCustomer(String email) throws SQLException {
		String query = "SELECT * FROM customers WHERE email=?";
		Connection connection = ConnectionProviderMockup.getConnection();
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
		Connection connection = ConnectionProviderMockup.getConnection();
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
		Connection connection = ConnectionProviderMockup.getConnection();

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
		Connection connection = ConnectionProviderMockup.getConnection();

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, badCustomer.getEmail());
		statement.executeUpdate();
		statement.close();
		return true;
	}

}
