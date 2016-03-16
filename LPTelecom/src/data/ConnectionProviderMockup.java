package data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ConnectionProviderMockup {

	private static final Logger LOGGER = Logger.getLogger(ConnectionProviderMockup.class.getName());

	@SuppressWarnings("unused")
	public static Connection getConnection() {
		if (false) {
			try {

				FileHandler fileHandler = new FileHandler("Logs/LPTelecomLog.txt", 1_000_000, 1);
				fileHandler.setFormatter(new SimpleFormatter());
				LOGGER.addHandler(fileHandler);
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			LOGGER.severe("Database connection failed! :(");
			LOGGER.log(Level.SEVERE, "Exception caught", e);
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/lptelecom?useSSL=false", "root", "root");
			// System.out.println("Successfully connected to database.");
			LOGGER.fine("Successfully connected to database.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.severe("Database connection failed! :(");
			// System.out.println("Database connection failed! :(");
			e.printStackTrace();
		}
		return conn;
	}
}
