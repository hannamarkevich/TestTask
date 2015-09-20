package myPackage;

import java.util.Date;

public class Client {
	private int id = -1;
	private String firstName = "";
	private String lastName = "";
	private Date birthDate = new Date();
	private String address = "";
	private String phone = "";
	private String email = "";

	public Client() {

	}

	public int getId() {
		return id;
	}

	public Client(int id, String fName, String lName, Date birthDate,
			String address, String phone, String email) {
		this.id = id;
		firstName = fName;
		lastName = lName;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public String toString() {
		return firstName + " " + lastName + " " + address;

	}

	public String parseForHref() {
		return "id=" + id + "&firstName=" + firstName + "&lastName=" + lastName
				+ "&address=" + address + "&phone=" + phone + "&email=" + email;
	}

}
