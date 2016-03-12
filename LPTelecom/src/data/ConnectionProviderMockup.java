package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProviderMockup {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/lptelecom", "root", "root");
			System.out.println("Success!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No success! :(");
			e.printStackTrace();
		}
		return conn;
		// conn.
		// if (conn != null) {
		// try {
		// conn.close();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
	}
}
