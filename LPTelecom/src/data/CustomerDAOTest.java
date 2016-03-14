package data;

import java.sql.SQLException;

public class CustomerDAOTest {
	public static void main(String[] args) {
		CustomerDAO customerDAO = new CustomerDAO();
		testJohn(customerDAO);
		testJohnExists();
		testCreationAndDeletion(customerDAO, "ivan@foo.com", "12345", "Ivan Ivanov");
		System.out.println("Next id better be 15.");
		testGetCustomerIdByEmail("a@a.com");

		testRenameCustomer(testGetJohn(), "Some other name");
		testRenameCustomer(testGetJohn(), "John Smith");

		// testRenameCustomer(testGetJohn(), "Some other name");

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

	public static Customer testGetJohn() {
		return testGetCustomer("john@foo.com");
	}

	private static Customer testGetCustomer(String email) {
		// TODO Auto-generated method stub
		CustomerDAO customerDAO = new CustomerDAO();
		try {
			return customerDAO.getCustomer("john@foo.com");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Customer("some@email.com", "12345", "John Another");
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

	public static int testGetCustomerIdByEmail(String email) {
		try {

			int customerIdByEmail = CustomerDAO.getCustomerIdByEmail(email);
			System.out.printf("Id for %s is %d.\n", email, customerIdByEmail);
			return customerIdByEmail;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public static void testRenameCustomer(Customer customer, String new_name) {
		System.out.printf("Let's try to rename %s to %s\n", customer.getName(), new_name);
		String email = customer.getEmail();
		CustomerDAO customerDAO = new CustomerDAO();

		Customer hopefullyTheSameCustomer = null;
		try {
			CustomerDAO.renameCustomer(customer, new_name);
			hopefullyTheSameCustomer = customerDAO.getCustomer(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("Their name is now %s.", hopefullyTheSameCustomer.getName());

	}

}
