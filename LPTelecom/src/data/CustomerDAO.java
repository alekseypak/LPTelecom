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
			customer = new Customer(email, password, name);
		}
		return customer;

	}

	public static boolean CustomerWithEmailExists(String email) throws SQLException {
		String query = "SELECT count(*) as count FROM customers WHERE email=?";
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

}
