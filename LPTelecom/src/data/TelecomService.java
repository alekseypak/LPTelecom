package data;

import java.io.Serializable;

public class TelecomService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6235688574230984183L;
	private String name;

	public TelecomService(String name, String name_alt, String descr, String descr_alt) {
		super();
		this.name = name;
		this.name_alt = name_alt;
		this.descr = descr;
		this.descr_alt = descr_alt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_alt() {
		return name_alt;
	}

	public void setName_alt(String name_alt) {
		this.name_alt = name_alt;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getDescr_alt() {
		return descr_alt;
	}

	public void setDescr_alt(String descr_alt) {
		this.descr_alt = descr_alt;
	}

	private String name_alt;
	private String descr;
	private String descr_alt;

}
