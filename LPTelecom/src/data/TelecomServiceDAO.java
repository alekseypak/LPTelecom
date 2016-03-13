package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TelecomServiceDAO {

	public static List<TelecomService> getAllTelecomServices() throws SQLException {
		String query = "SELECT * FROM telecom_services";

		Connection connection = ConnectionProviderMockup.getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		List<TelecomService> serviceList = new LinkedList<>();
		while (rs.next()) {
			// String password = rs.getString("password");

			String name = rs.getString("name");
			String description = rs.getString("descr");
			String name_alt = rs.getString("name_alt");
			String description_alt = rs.getString("descr_alt");
			TelecomService newService = new TelecomService(name, name_alt, description, description_alt);
			serviceList.add(newService);
			// customer = new Customer(email, password, name);
		}
		return serviceList;
		// return customer;
		// return null;
	}

}
