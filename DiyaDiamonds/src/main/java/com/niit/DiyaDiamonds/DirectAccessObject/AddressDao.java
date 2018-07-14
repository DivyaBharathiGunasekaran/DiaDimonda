package com.niit.DiyaDiamonds.DirectAccessObject;

import java.util.List;

import com.niit.DiyaDiamonds.Model.Address;

public interface AddressDao 
{
	public boolean addAddress(Address address);
	public boolean delAddress(int id);
	Address show(int id);
	List<Address> list(int cartId);
	

}
