package data;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class TelecomServiceDAOTest {

	@Test
	public void testGetAllTelecomServices() throws SQLException {
		Assert.assertEquals(3, TelecomServiceDAO.getAllTelecomServices().size());
	}

	@Test
	public void testGetTelecomServiceById() throws SQLException {
		TelecomService telecomServiceRetvievedById = TelecomServiceDAO.getTelecomServiceById(1);
		Assert.assertEquals("Telephony", telecomServiceRetvievedById.getName());
	}

}
