package customers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Customer;
import data.CustomerDAO;

/**
 * Servlet implementation class ControllerSignUp
 */
@WebServlet("/ControllerSignUp")
public class ControllerSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerSignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String action = request.getParameter("action");
		if (action != null && action.equals("dosignup")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String repeat_password = request.getParameter("repeat_password");
			String name = request.getParameter("name");
			if (email == null || email.isEmpty() || password == null || password.isEmpty() || name == null
					|| name.isEmpty() || repeat_password == null || repeat_password.isEmpty()) {
				request.setAttribute("error_message", "All fields should be both non-empty");
				request.getRequestDispatcher("/signup.jsp").forward(request, response);
				return;
			}

			try { // Perhaps this should be higher up?
				if (CustomerDAO.customerWithEmailExists(email)) {
					request.setAttribute("error_message", "User with email " + email + " already exists.");
					request.getRequestDispatcher("/signup.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!password.equals(repeat_password)) {
				request.setAttribute("error_message", "Passwords do not match");
				request.getRequestDispatcher("/signup.jsp").forward(request, response);
				return;

			}

			Customer newCustomer = new Customer(email, password, name);
			CustomerDAO customerDAO = new CustomerDAO();
			try {
				customerDAO.addCustomer(newCustomer);
				System.out.println("Customer " + email + " created");
				response.sendRedirect("/LPTelecom/login.jsp");
				// request.setAttribute("message", );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}