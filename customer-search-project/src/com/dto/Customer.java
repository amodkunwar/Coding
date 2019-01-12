package com.dto;

import java.util.Date;

public class Customer {

	private String cusId;
	private String name;
	private Date dob;
	private long contact;
	private String city;
	private String password;

	public Customer(String name, Date dob, long contact, String city, String password) {
		this.name = name;
		this.dob = dob;
		this.contact = contact;
		this.city = city;
		this.password = password;
	}

	public Customer() {
		System.out.println(this.getClass().getSimpleName() + " class object created");
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [cusId=" + cusId + ", name=" + name + ", dob=" + dob + ", contact=" + contact + ", city="
				+ city + ", password=" + password + "]";
	}

}
