package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
	private Connection connection;

	public Customer getCustomer(String email) throws SQLException {
		String query = "SELECT * FROM customers WHERE email=?";
		// TODO: get a connection.
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, email);
		Customer customer;

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			String password = rs.getString("password");
			String name = rs.getString("name");
			customer = new Customer(email, password, name);
		}
		return customer;

	}

}
