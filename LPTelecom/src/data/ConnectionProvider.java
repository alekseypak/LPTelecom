package data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {

	private static final Logger LOGGER = Logger.getLogger(ConnectionProvider.class.getName());
	private static ConnectionProvider instance = new ConnectionProvider();
	private static DataSource ds;

	private ConnectionProvider() {
		LOGGER.info("Trying to create a DataSource.");
		try {
			InitialContext initContext = new InitialContext();

			LOGGER.info("Trying to access context.");
			Context env = (Context) initContext.lookup("java:comp/env");

			ds = (DataSource) env.lookup("jdbc/lptelecom");
			LOGGER.info("Created a DataSource.");
		} catch (NamingException e) {
			LOGGER.severe("JNDI lookup failed :(");
			LOGGER.log(Level.SEVERE, "Exception caught", e);
		}
	}

	public static ConnectionProvider getInstance() {
		return instance;
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			DataSource dds = ConnectionProvider.ds;
			connection = dds.getConnection();
			LOGGER.fine("Successfully connected to database.");
		} catch (SQLException e) {
			LOGGER.severe("Database connection failed! :(");
			LOGGER.log(Level.SEVERE, "Exception caught", e);
		}
		return connection;
	}

}
