package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionProviderMockup {

	private static final Logger LOGGER = Logger.getLogger(ConnectionProviderMockup.class.getName());

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/lptelecom?useSSL=false", "root", "root");
			// System.out.println("Successfully connected to database.");
			LOGGER.info("Successfully connected to database.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.severe("Database connection failed! :(");
			// System.out.println("Database connection failed! :(");
			e.printStackTrace();
		}
		return conn;
	}
}
