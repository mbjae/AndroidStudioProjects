package com.example.database;

public class Customer {

	private String custName;
	private String phone;
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Customer [custName=" + custName + ", phone=" + phone + "]";
	}
}
