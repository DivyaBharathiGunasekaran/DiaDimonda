package com.niit.DiyaDiamonds.DirectAccessObject;

import java.util.List;

import com.niit.DiyaDiamonds.Model.Cart;

public interface Cartdao 
{

	boolean add(Cart cart);
	boolean delete(int id);
	
	List<Cart> show(int cartId);
	
}

