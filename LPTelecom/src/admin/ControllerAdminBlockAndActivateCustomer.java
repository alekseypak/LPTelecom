package admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.CustomerDAO;

/**
 * Servlet implementation class ControllerBlockAndActivateCustomer
 */
@WebServlet("/ControllerAdminBlockAndActivateCustomer")
public class ControllerAdminBlockAndActivateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ControllerAdminBlockAndActivateCustomer.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerAdminBlockAndActivateCustomer() {
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
		String email = request.getParameter("email");
		String actionType = request.getParameter("action_type");
		String customerStatus = request.getParameter("customer_status");
		// Sanity check:
		if (!((customerStatus.equals("active") && actionType.equals("block"))
				|| (customerStatus.equals("blocked") && actionType.equals("activate")))) {

			LOGGER.log(Level.INFO, "Do we want to {0} {2} with status {1}",
					new Object[] { actionType, customerStatus, email });
		}
		String newCustomerStatus = "undefined";
		if (actionType.equals("block")) {
			newCustomerStatus = "blocked";
		} else if (actionType.equals("activate")) {
			newCustomerStatus = "active";
		}
		CustomerDAO customerDAO = new CustomerDAO();

		try {
			CustomerDAO.changeCustomerStatus(customerDAO.getCustomer(email), newCustomerStatus);
			request.getRequestDispatcher("/ControllerAdminLogin").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.severe("SQL query failed.");
			LOGGER.log(Level.SEVERE, "Exception caught", e);
			response.sendRedirect("/LPTelecom/");
		}
	}

}
