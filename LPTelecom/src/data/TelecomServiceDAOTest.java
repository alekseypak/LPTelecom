package data;

import java.sql.SQLException;

public class TelecomServiceDAOTest {
	public static void main(String[] args) {
		listGetterTest();
	}

	private static void listGetterTest() {
		// Why is this not called getAllTelecomServicesTest? Perhaps it might
		// clash with a pre-rolled test framework.
		System.out.println("Let's try to get all the services...");
		try {
			for (TelecomService ts : TelecomServiceDAO.getAllTelecomServices()) {
				System.out.printf("%s: %s\n", ts.getName(), ts.getDescr());

			}
			;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
