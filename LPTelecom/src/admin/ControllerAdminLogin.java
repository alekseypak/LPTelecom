package admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.AdminDAO;
import data.Customer;
import data.CustomerDAO;
import data.Invoice;
import data.InvoiceDAO;

/**
 * Servlet implementation class ControllerAdminLogin
 */
@WebServlet("/ControllerAdminLogin")
public class ControllerAdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ControllerAdminLogin.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerAdminLogin() {
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
		HttpSession session = request.getSession();
		String admin_login = request.getParameter("admin_login");
		String admin_password = request.getParameter("admin_password");
		// if (session.getAttribute("admin_logged_in") != null) {
		// System.out.println(session.getAttribute("admin_logged_in"));
		// This displays true (without any type casting). Interestingly
		// enough, I've casted it to Boolean implicitly and Eclipse removed
		// the cast. This seems surprising which is without doubt a sign of
		// poor understanding of Java object model on my part.
		// }
		if (session.getAttribute("admin_logged_in") != null
				|| AdminDAO.AdminLoginAndPasswordMatch(admin_login, admin_password))

		{

			List<Invoice> allInvoices = new ArrayList<Invoice>();
			List<Customer> allCustomers = new ArrayList<Customer>();
			try {
				allInvoices = InvoiceDAO.getAllInvoices();
				request.setAttribute("allInvoices", allInvoices);
				allCustomers = CustomerDAO.getAllCustomers();
				request.setAttribute("allCustomers", allCustomers);
			} catch (SQLException e) {
				LOGGER.severe("SQL query failed.");
				LOGGER.log(Level.SEVERE, "Exception caught", e);
				response.sendRedirect("/LPTelecom/");
			}

			session.setAttribute("admin_logged_in", true);
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
			// response.sendRedirect("/LPTelecom/");
			// doGet(request, response);
		} else {

			String error_message = "Wrong credentials!";
			session.setAttribute("error_message", error_message);
			LOGGER.info(error_message);
			response.sendRedirect("/LPTelecom/adminlogin.jsp");
		}
	}

}
