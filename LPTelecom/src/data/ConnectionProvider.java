package data;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	private static ConnectionProvider instance = new ConnectionProvider();
	private DataSource ds;

	private ConnectionProvider() {
		try {
			InitialContext initContext = new InitialContext();
			Context env = (Context) initContext.lookup("java:comp/env");

			ds = (DataSource) env.lookup("jdbc/lptelecom");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			// TODO: add logging here.
		}
	}

	private static Connection getConnection() {
		Connection connection = null;
		try {
			connection = instance.ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// TODO: add logging here.
			e.printStackTrace();
		}
		return connection;
	}

}
