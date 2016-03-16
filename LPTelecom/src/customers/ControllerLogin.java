package customers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Customer;
import data.CustomerDAO;
import data.Invoice;
import data.InvoiceDAO;
import data.TelecomService;
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

	private static final Logger LOGGER = Logger.getLogger(ControllerLogin.class.getName());

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
		String error_message = "No error!";
		if (action != null && action.equals("dologin")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
				error_message = "Email and password should be both non-empty.";
				request.setAttribute("error_message", error_message);
				LOGGER.info(error_message);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
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
					List<Invoice> invoicesForCustomer = InvoiceDAO.getInvoicesForCustomer(customer);
					session.setAttribute("invoices", invoicesForCustomer);
					List<TelecomService> allTelecomServices = TelecomServiceDAO.getAllTelecomServices();
					session.setAttribute("all_services", allTelecomServices);
					List<TelecomService> missingTelecomServices = new LinkedList<TelecomService>(allTelecomServices);
					List<TelecomService> customerTelecomServices = new LinkedList<TelecomService>();
					for (Invoice customerInvoice : invoicesForCustomer) {
						customerTelecomServices.add(customerInvoice.getInvoiceTelecomService());
					}
					missingTelecomServices.removeAll(customerTelecomServices);

					session.setAttribute("missingServices", missingTelecomServices);
					String success_message = "Login with email " + email + " successful!";
					request.setAttribute("message", success_message);
					LOGGER.info(success_message);
					System.out.println(success_message);
					request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
					return;
				} else {

					if (customer == null) {
						error_message = "email " + email + " not found.";
						request.setAttribute("error_message", error_message);
						LOGGER.info(error_message);
					}
					if (customer != null && !password.equals(customer.getPassword())) {
						error_message = "Wrong password for email " + email + ".";
						request.setAttribute("error_message", error_message);
						LOGGER.info(error_message);
					}
					request.getRequestDispatcher("/login.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOGGER.severe("Password SQL lookup failed!");
			}

		}

	}

}
