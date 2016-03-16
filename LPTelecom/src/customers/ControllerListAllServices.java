package customers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.TelecomServiceDAO;

/**
 * Servlet implementation class ControllerListAllServices
 */
@WebServlet("/ControllerListAllServices")
public class ControllerListAllServices extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ControllerListAllServices.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerListAllServices() {
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
		HttpSession session = request.getSession();
		try {
			session.setAttribute("all_services", TelecomServiceDAO.getAllTelecomServices());
			LOGGER.info("List of all services requested.");
		} catch (SQLException e) {
			LOGGER.severe("SQL query failed.");
			LOGGER.log(Level.SEVERE, "Exception caught", e);
			response.sendRedirect("/LPTelecom/");
		}
		String lang = request.getParameter("lang");

		session.setAttribute("lang", lang);
		request.getRequestDispatcher("/listservices.jsp").forward(request, response);

		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
