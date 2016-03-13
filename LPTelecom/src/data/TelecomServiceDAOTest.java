package data;

import java.sql.SQLException;

public class TelecomServiceDAOTest {
	public static void main(String[] args) {
		try {
			TelecomServiceDAO.getAllTelecomServices();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
