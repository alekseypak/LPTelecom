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
import data.Invoice;
import data.InvoiceDAO;

/**
 * Servlet implementation class ControllerPayment
 */
@WebServlet("/ControllerPayment")
public class ControllerPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerPayment() {
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
		int service_id = Integer.parseInt(service_id_string);
		// What could go wrong? :)
		boolean payed_now = false;
		String payed_now_string = request.getParameter("payed_now");
		if (payed_now_string != null) {
			payed_now = payed_now_string.equals("true");
		}

		List<Invoice> invoiceList = new ArrayList<Invoice>();
		Invoice invoice_to_be_updated = null;
		// That doesn't look like a good idea.
		// TODO: ask someone about the idiomatic solution.
		// The problem is: what to do if a large portion of the code relies on
		// something that might fail.
		// It's a good idea to keep as little as possible in the try section.
		// Or is it?

		try {
			invoiceList = InvoiceDAO.getInvoicesForEmail(email);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.printf("Correct service id: %d", service_id);
		for (Invoice invoice : invoiceList) {
			System.out.printf("Current service id: %d", invoice.getInvoiceTelecomService().getId());
			if (invoice.getInvoiceTelecomService().getId() == service_id) {
				invoice_to_be_updated = invoice;
				break;
			}
		}
		try {
			InvoiceDAO.setInvoiceStatus(invoice_to_be_updated, payed_now);
			String success_message = "Successful payment for "
					+ invoice_to_be_updated.getInvoiceTelecomService().getName();
			request.setAttribute("message", success_message);
			Customer customer = (Customer) session.getAttribute("customer");
			session.setAttribute("invoices", InvoiceDAO.getInvoicesForCustomer(customer));
			// session.setAttribute("all_services",
			// TelecomServiceDAO.getAllTelecomServices());
			// System.out.println("Email " + email + " found! Password
			// correct.");

			request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
