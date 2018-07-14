package com.niit.DiyaDiamonds.DirectAccessObject;

import java.util.List;

import com.niit.DiyaDiamonds.Model.Customer;

public interface CustomerDao {


	public boolean addCustomer(Customer customer);
	public boolean delCustomer(String emailId);
	public Customer showCustomer(String emailId);
	public List<Customer> showAllCustomer();
	
}
