package data;

import java.io.Serializable;

public class Invoice implements Serializable {
	private Customer invoiceCustomer;
	private TelecomService invoiceTelecomService;
	private boolean payed;
	private String status;

	public Invoice(Customer invoiceCustomer, TelecomService invoiceTelecomService, boolean payed, String status) {
		super();
		this.invoiceCustomer = invoiceCustomer;
		this.invoiceTelecomService = invoiceTelecomService;
		this.payed = payed;
		this.status = status;
	}

	public Invoice(Customer invoiceCustomer, TelecomService invoiceTelecomService) {
		this(invoiceCustomer, invoiceTelecomService, false, "not payed");
	}

	public Customer getInvoiceCustomer() {
		return invoiceCustomer;
	}

	public void setInvoiceCustomer(Customer invoiceCustomer) {
		this.invoiceCustomer = invoiceCustomer;
	}

	public TelecomService getInvoiceTelecomService() {
		return invoiceTelecomService;
	}

	public void setInvoiceTelecomService(TelecomService invoiceTelecomService) {
		this.invoiceTelecomService = invoiceTelecomService;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
