package data;

import java.io.Serializable;

public class TelecomService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6235688574230984183L;
	private String name;
	private int id;
	// This is the same id you would see in the database.
	// Rationale: name is subject to change.

	public TelecomService(int id, String name, String name_alt, String descr, String descr_alt) {
		super();
		this.id = id;
		this.name = name;
		this.name_alt = name_alt;
		this.descr = descr;
		this.descr_alt = descr_alt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descr == null) ? 0 : descr.hashCode());
		result = prime * result + ((descr_alt == null) ? 0 : descr_alt.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((name_alt == null) ? 0 : name_alt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TelecomService other = (TelecomService) obj;
		if (descr == null) {
			if (other.descr != null)
				return false;
		} else if (!descr.equals(other.descr))
			return false;
		if (descr_alt == null) {
			if (other.descr_alt != null)
				return false;
		} else if (!descr_alt.equals(other.descr_alt))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (name_alt == null) {
			if (other.name_alt != null)
				return false;
		} else if (!name_alt.equals(other.name_alt))
			return false;
		return true;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String name_alt;
	private String descr;
	private String descr_alt;

}
