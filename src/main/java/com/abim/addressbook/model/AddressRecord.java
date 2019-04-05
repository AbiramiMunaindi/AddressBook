package com.abim.addressbook.model;

public class AddressRecord {
	
	int id;
	String firstName;
	String lastName;
	String phoneNumber;
	String address;
	String emailAddres;
	
	public AddressRecord(int id, String firstName, String lastName, String phoneNumber, String address, String emailAddress) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.emailAddres = emailAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailAddres() {
		return emailAddres;
	}

	public void setEmailAddres(String emailAddres) {
		this.emailAddres = emailAddres;
	}
	
	@Override
	public String toString() {
		return "Address record:- [id:" + id + ", firstname:" + firstName + ", lastname:" + lastName + ", phonenumber:"
				+ phoneNumber + ", address:" + address + ", emailaddress:" + emailAddres + "]";
	}

}
