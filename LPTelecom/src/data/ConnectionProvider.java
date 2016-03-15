package data;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	private static ConnectionProvider instance = new ConnectionProvider();
	private static DataSource ds;

	private ConnectionProvider() {
		System.out.println("Trying to create a DataSource.");
		try {
			InitialContext initContext = new InitialContext();

			System.out.println("Hello1");
			Context env = (Context) initContext.lookup("java:comp/env");
			System.out.println("Hello2");
			ds = (DataSource) env.lookup("jdbc/lptelecom");
			System.out.println("Created a DataSource.");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			// TODO: add logging here.
			e.printStackTrace();
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// TODO: add logging here.
			e.printStackTrace();
		}
		return connection;
	}

}
