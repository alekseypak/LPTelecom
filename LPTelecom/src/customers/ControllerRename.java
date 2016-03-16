package customers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Customer;
import data.CustomerDAO;

/**
 * Servlet implementation class ControllerRename
 */
@WebServlet("/ControllerRename")
public class ControllerRename extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ControllerRename.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerRename() {
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
		request.setCharacterEncoding("UTF-8");
		// HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String new_name = request.getParameter("new_name");
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer;
		String message = "Rename unsuccessful.";
		try {
			customer = customerDAO.getCustomer(email);
			if (CustomerDAO.renameCustomer(customer, new_name)) {
				message = "Rename successful.";
				LOGGER.info(message);
				request.setAttribute("customer", customerDAO.getCustomer(email));
				request.setAttribute("message", message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.severe("SQL query failed.");
		}
		request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);

	}

}
