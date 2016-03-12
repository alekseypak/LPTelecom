package data;

import java.sql.SQLException;

public class CustomerDAOTest {
	public static void main(String[] args) {
		CustomerDAO customerDAO = new CustomerDAO();
		testJohn(customerDAO);
		testJohnExists();
		testCreation(customerDAO, "ivan@foo.com", "12345", "Ivan Ivanov");
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
			System.out.println("Should be true: " + CustomerDAO.customerWithEmailExists("john@foo.com"));
			System.out.println("Should be false: " + CustomerDAO.customerWithEmailExists("foo@bar.co"));
			System.out.println("ivan@foo.com: " + CustomerDAO.customerWithEmailExists("ivan@foo.com"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void testCreation(CustomerDAO customerDAO, String email, String password, String name) {
		Customer newCustomer = new Customer(email, password, name);
		try {
			customerDAO.addCustomer(newCustomer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
