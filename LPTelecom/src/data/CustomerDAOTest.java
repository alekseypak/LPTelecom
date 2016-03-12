package data;

import java.sql.SQLException;

public class CustomerDAOTest {
	public static void main(String[] args) {
		CustomerDAO customerDAO = new CustomerDAO();
		testJohn(customerDAO);
		testJohnExists();
	}

	public static void testJohn(CustomerDAO customerDAO) {
		try {
			Customer customer = customerDAO.getCustomer("john@foo.com");
			System.out.println(customer.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void testJohnExists() {
		try {
			System.out.println("Should be true: " + CustomerDAO.CustomerWithEmailExists("john@foo.com"));
			System.out.println("Should be false: " + CustomerDAO.CustomerWithEmailExists("foo@bar.co"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
