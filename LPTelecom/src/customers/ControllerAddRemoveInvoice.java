package customers;

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

import data.Customer;
import data.CustomerDAO;
import data.Invoice;
import data.InvoiceDAO;

/**
 * Servlet implementation class ControllerAddRemoveInvoice
 */
@WebServlet("/ControllerAddRemoveInvoice")
public class ControllerAddRemoveInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerAddRemoveInvoice() {
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
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String service_id_string = request.getParameter("service_id");
		String action_type = request.getParameter("action_type");
		int service_id = Integer.parseInt(service_id_string);
		System.out.printf("%s tried to remove %s", email, service_id_string);
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		Invoice invoice_to_be_updated = null;
		try {
			invoiceList = InvoiceDAO.getInvoicesForEmail(email);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.printf("Correct service id: %d\n", service_id);
		for (Invoice invoice : invoiceList) {
			System.out.printf("Current service id: %d\n", invoice.getInvoiceTelecomService().getId());
			if (invoice.getInvoiceTelecomService().getId() == service_id) {
				invoice_to_be_updated = invoice;
				break;
			}
		}
		Invoice badInvoice = invoice_to_be_updated;
		try {
			InvoiceDAO.deleteInvoice(badInvoice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String success_message = "Unsubscribed from  " + invoice_to_be_updated.getInvoiceTelecomService().getName();
		request.setAttribute("message", success_message);
		CustomerDAO customerDAO = new CustomerDAO();
		try {
			Customer customer = customerDAO.getCustomer(email);
			session.setAttribute("invoices", InvoiceDAO.getInvoicesForCustomer(customer));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("/LPTelecom/");
		}
		request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
	}

}
