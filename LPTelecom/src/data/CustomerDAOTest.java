package data;

import java.sql.SQLException;

public class CustomerDAOTest {
	public static void main(String[] args) {
		CustomerDAO customerDAO = new CustomerDAO();
		testJohn(customerDAO);
		testJohnExists();
		testCreationAndDeletion(customerDAO, "ivan@foo.com", "12345", "Ivan Ivanov");
		/*
		 * try { customerDAO.addCustomer(new Customer("b@a.com", "111",
		 * "Валентин Заблокировский", "blocked")); } catch (SQLException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */

	}

	public static void testJohn(CustomerDAO customerDAO) {
		try {
			System.out.println("Let's try to retrieve john@foo.com's name.");
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void testCreationAndDeletion(CustomerDAO customerDAO, String email, String password, String name) {
		testCreationAndDeletion(customerDAO, email, password, name, "active");
	}

	public static void testCreationAndDeletion(CustomerDAO customerDAO, String email, String password, String name,
			String status) {
		Customer newCustomer = new Customer(email, password, name, status);
		try {
			// TODO: Should remove Ivan first.

			System.out.println("ivan@foo.com exists: " + CustomerDAO.customerWithEmailExists("ivan@foo.com"));
			System.out.println("Let's try to add ivan@foo.com");
			customerDAO.addCustomer(newCustomer);
			System.out.println("ivan@foo.com exists: " + CustomerDAO.customerWithEmailExists("ivan@foo.com"));
			customerDAO.deleteCustomer(newCustomer);
			System.out.println("Let's try to delete ivan@foo.com");
			System.out.println("ivan@foo.com exists: " + CustomerDAO.customerWithEmailExists("ivan@foo.com"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
