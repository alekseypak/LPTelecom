package data;

import java.sql.SQLException;

@Deprecated
public class TelecomServiceDAOLPTest {
	public static void main(String[] args) {
		listGetterTest();
		getByIdTest(1);
	}

	@Deprecated
	private static void listGetterTest() {
		// Why is this not called getAllTelecomServicesTest? Perhaps it might
		// clash with a pre-rolled test framework.
		System.out.println("Let's try to get all the services...");
		try {
			for (TelecomService ts : TelecomServiceDAO.getAllTelecomServices()) {
				System.out.printf("%s: %s: %s\n", ts.getId(), ts.getName(), ts.getDescr());

			}
			;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Deprecated
	private static TelecomService getByIdTest(int id) {
		System.out.printf("Let's try to get the service with id %s...\n", id);
		try {
			TelecomService telecomServiceRetvievedById = TelecomServiceDAO.getTelecomServiceById(id);
			System.out.println(telecomServiceRetvievedById.getName());
			return telecomServiceRetvievedById;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
