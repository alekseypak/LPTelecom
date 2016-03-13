package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelecomServiceDAO {

	public static void getAllTelecomServices() throws SQLException {
		String query = "SELECT * FROM telecom_services";
		Connection connection = ConnectionProviderMockup.getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			// String password = rs.getString("password");
			String name = rs.getString("name");
			String description = rs.getString("descr");
			System.out.printf("%s: %s\n", name, description);

			// customer = new Customer(email, password, name);
		}
		// return customer;
		// return null;
	}

}
