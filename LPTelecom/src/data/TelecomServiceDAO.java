package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TelecomServiceDAO {

	private static Connection getConnection() {
		Connection connection = ConnectionProviderMockup.getConnection();
		return connection;
	}

	public static List<TelecomService> getAllTelecomServices() throws SQLException {
		String query = "SELECT * FROM telecom_services";

		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		List<TelecomService> serviceList = new LinkedList<>();
		while (rs.next()) {
			// String password = rs.getString("password");
			int id = rs.getInt("service_id");
			String service_name = rs.getString("service_name");
			String description = rs.getString("descr");
			String service_name_alt = rs.getString("service_name_alt");
			String description_alt = rs.getString("descr_alt");

			TelecomService newService = new TelecomService(id, service_name, service_name_alt, description,
					description_alt);
			serviceList.add(newService);
			// customer = new Customer(email, password, name);
		}
		rs.close();
		connection.close();
		return serviceList;
		// return customer;
		// return null;
	}

	public static TelecomService getTelecomServiceById(int id) throws SQLException {
		String query = "SELECT * FROM telecom_services WHERE service_id = ?";

		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			// String password = rs.getString("password");
			String service_name = rs.getString("service_name");
			String description = rs.getString("descr");
			String service_name_alt = rs.getString("service_name_alt");
			String description_alt = rs.getString("descr_alt");

			TelecomService newService = new TelecomService(id, service_name, service_name_alt, description,
					description_alt);
			rs.close();
			connection.close();
			return newService;
		}
		return null;
	}

}
