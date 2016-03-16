package customers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Customer;
import data.CustomerDAO;

/**
 * Servlet implementation class ControllerSignUp
 */
@WebServlet("/ControllerSignUp")
public class ControllerSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ControllerSignUp.class.getName());

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
		request.setCharacterEncoding("UTF-8");
		// TODO: perhaps, a filter would be nice here.
		String action = request.getParameter("action");
		if (action != null && action.equals("dosignup")) {

			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String message = "Something went wrong.";
			String repeat_password = request.getParameter("repeat_password");
			String name = request.getParameter("name");
			if (email == null || email.isEmpty() || password == null || password.isEmpty() || name == null
					|| name.isEmpty() || repeat_password == null || repeat_password.isEmpty()) {
				// HttpSession session = request.getSession();
				// getSession(false) means a session is not to be created if it
				// doesn't exist.
				message = "All fields should be both non-empty.";
				LOGGER.info(message);
				request.setAttribute("message", message);
				request.getRequestDispatcher("/signup.jsp").forward(request, response);
				return;
			}

			if (!email.contains("@")) {
				// This is a not production grade email validation, obviously.
				// A proper regular expression is notoriously hard to write.
				message = email + " doesn't look like a valid email address.";
				LOGGER.info(message);
				request.setAttribute("message", message);
				request.getRequestDispatcher("/signup.jsp").forward(request, response);
				return;
			}

			try { // Perhaps this should be higher up?
				if (CustomerDAO.customerWithEmailExists(email)) {
					message = "User with email " + email + " already exists.";
					request.setAttribute("message", message);
					LOGGER.info(message);
					request.getRequestDispatcher("/signup.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!password.equals(repeat_password)) {
				message = "Passwords do not match.";
				request.setAttribute("message", message);
				LOGGER.info(message);
				request.getRequestDispatcher("/signup.jsp").forward(request, response);
				return;

			}

			Customer newCustomer = new Customer(email, password, name);
			CustomerDAO customerDAO = new CustomerDAO();
			try {
				customerDAO.addCustomer(newCustomer);
				message = "Customer with email " + email + " created.";
				HttpSession session = request.getSession();
				session.setAttribute("message", message);
				LOGGER.info(message);

				response.sendRedirect("/LPTelecom/login.jsp");
				// request.setAttribute("message", );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOGGER.severe("SQL query failed.");

			}

		}

	}
}