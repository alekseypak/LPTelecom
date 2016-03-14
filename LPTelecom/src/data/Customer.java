package data;

import java.io.Serializable;

public class Customer implements Serializable {

	private String email;
	private String password;
	private String name;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer(String email, String password, String name) {
		this(email, password, name, "active");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer(String email, String password, String name, String status) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
