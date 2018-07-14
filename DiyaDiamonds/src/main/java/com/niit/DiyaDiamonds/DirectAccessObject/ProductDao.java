package com.niit.DiyaDiamonds.DirectAccessObject;

import java.util.List;

import com.niit.DiyaDiamonds.Model.Product;

public interface ProductDao {

	Product saveOrUpdateProduct(Product product); 
	   Product getProduct(int id);
	   void deleteProduct(int id);
	   List<Product> getAllProducts();
	   public List<Product> getCategoryProducts(int cid);

}
