package admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		String admin_login = request.getParameter("admin_login");
		String admin_password = request.getParameter("admin_password");
		if (AdminDAO.AdminLoginAndPasswordMatch(admin_login, admin_password)) {

			List<Invoice> allInvoices = new ArrayList<Invoice>();
			List<Customer> allCustomers = new ArrayList<Customer>();
			try {
				allInvoices = InvoiceDAO.getAllInvoices();
				request.setAttribute("allInvoices", allInvoices);
				allCustomers = CustomerDAO.getAllCustomers();
				request.setAttribute("allCustomers", allCustomers);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
			// response.sendRedirect("/LPTelecom/");
			// doGet(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("error_message", "Wrong credentials!");
			response.sendRedirect("/LPTelecom/adminlogin.jsp");
		}
	}

}
