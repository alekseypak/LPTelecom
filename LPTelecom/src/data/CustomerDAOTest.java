package data;

import java.sql.SQLException;

public class CustomerDAOTest {
	public static void main(String[] args) {
		CustomerDAO customerDAO = new CustomerDAO();
		testJohn(customerDAO);
	}

	public static void testJohn(CustomerDAO customerDAO) {
		try {
			Customer customer = customerDAO.getCustomer("john@foo.com");
			System.out.println(customer.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
