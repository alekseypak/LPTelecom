package customers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Customer;
import data.CustomerDAO;
import data.InvoiceDAO;
import data.TelecomServiceDAO;

/**
 * Servlet implementation class ControllerLogin
 */
@WebServlet("/ControllerLogin")
public class ControllerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerLogin() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		response.sendRedirect("/LPTelecom/login.jsp");
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
		if (action != null && action.equals("dologin")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
				request.setAttribute("error_message", "Email and password should be both non-empty");
				request.getRequestDispatcher("/loginfailed.jsp").forward(request, response);
				return;
			}
			CustomerDAO customerDAO = new CustomerDAO();

			try {
				Customer customer = customerDAO.getCustomer(email);
				if (customer != null && customer.getPassword().equals(password)) {
					request.setAttribute("email", email);
					HttpSession session = request.getSession();
					// request.getSession(false) returns null if there is no
					// existing session.
					// I don't quite understand why a session might not exist at
					// this point, but it happens apparently.
					// I do get NullPointerExceptions on the next line.
					// TODO: learn the session lifecycle.
					session.setAttribute("customer", customer);
					session.setAttribute("invoices", InvoiceDAO.getInvoicesForCustomer(customer));
					session.setAttribute("all_services", TelecomServiceDAO.getAllTelecomServices());
					String success_message = "Login with email " + email + " successful!";
					System.out.println(success_message);
					request.setAttribute("message", success_message);
					request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
					return;
				} else {
					if (customer == null) {
						request.setAttribute("error_message", "email " + email + " not found");
					}
					if (customer != null && !password.equals(customer.getPassword())) {
						request.setAttribute("error_message", "Wrong password for email " + email);
					}
					request.getRequestDispatcher("/loginfailed.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Password lookup failed!");
			}

		}

	}

}
