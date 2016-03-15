package admin;

import java.io.IOException;
import java.sql.SQLException;

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
		System.out.println(email);
		String actionType = request.getParameter("action_type");
		String customerStatus = request.getParameter("customer_status");
		// Sanity check:
		if (!((customerStatus.equals("active") && actionType.equals("block"))
				|| (customerStatus.equals("blocked") && actionType.equals("activate")))) {
			System.out.printf("Unexpected state: %s %s", actionType, customerStatus);
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
			e.printStackTrace();
			response.sendRedirect("/LPTelecom/");
		}
	}

}
