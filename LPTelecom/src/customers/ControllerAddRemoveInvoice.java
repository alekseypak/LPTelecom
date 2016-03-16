package customers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
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
 * Servlet implementation class ControllerAddRemoveInvoice
 */
@WebServlet("/ControllerAddRemoveInvoice")
public class ControllerAddRemoveInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ControllerAddRemoveInvoice.class.getName());

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
		String success_message = "Something bad happened";
		if (action_type.equals("unsubscribe")) {
			LOGGER.log(Level.INFO, "{0} is trying to remove service with id {1}.",
					new Object[] { email, service_id_string });
			List<Invoice> invoiceList = new ArrayList<Invoice>();
			Invoice invoice_to_be_updated = null;
			try {
				invoiceList = InvoiceDAO.getInvoicesForEmail(email);
			} catch (SQLException e1) {
				LOGGER.severe("SQL query failed.");
				LOGGER.log(Level.SEVERE, "Exception caught", e1);
				response.sendRedirect("/LPTelecom/");
			}
			for (Invoice invoice : invoiceList) {
				if (invoice.getInvoiceTelecomService().getId() == service_id) {
					invoice_to_be_updated = invoice;
					break;
				}
			}
			Invoice badInvoice = invoice_to_be_updated;
			try {
				InvoiceDAO.deleteInvoice(badInvoice);
			} catch (SQLException e) {
				LOGGER.severe("SQL query failed.");
				LOGGER.log(Level.SEVERE, "Exception caught", e);
				response.sendRedirect("/LPTelecom/");
			}
			success_message = email + " unsubscribed from  "
					+ invoice_to_be_updated.getInvoiceTelecomService().getName() + ".";
		} else if (action_type.equals("subscribe")) {
			LOGGER.log(Level.INFO, "{0} is trying to add service with id {1}.",
					new Object[] { email, service_id_string });
			try {
				TelecomService telecomService = TelecomServiceDAO.getTelecomServiceById(service_id);
				CustomerDAO customerDAO = new CustomerDAO();
				Customer customer = customerDAO.getCustomer(email);
				Invoice newInvoice = new Invoice(customer, telecomService);
				InvoiceDAO.insertInvoice(newInvoice);
				success_message = email + " subscribed to " + telecomService.getName() + ".";

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.severe("SQL query failed.");
				LOGGER.log(Level.SEVERE, "Exception caught", e);
				response.sendRedirect("/LPTelecom/");
			}
		}
		LOGGER.info(success_message);
		request.setAttribute("message", success_message);
		CustomerDAO customerDAO = new CustomerDAO();
		try {
			Customer customer = customerDAO.getCustomer(email);
			session.setAttribute("invoices", InvoiceDAO.getInvoicesForCustomer(customer));
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
			request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
		} catch (SQLException e) {
			LOGGER.severe("SQL query failed.");
			LOGGER.log(Level.SEVERE, "Exception caught", e);
			response.sendRedirect("/LPTelecom/");
		}

	}

}
