package com.niit.DiyaDiamonds.DirectAccessObject;

import java.util.List;

import com.niit.DiyaDiamonds.Model.CustomerOrder;

public interface OrderDao 
{
boolean insert(CustomerOrder order);
	
	List<CustomerOrder> viewAllOrder(int cartId);
	
	List<CustomerOrder> viewreceipt(String orderid);
	


}
