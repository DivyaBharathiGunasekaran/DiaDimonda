package com.niit.DiyaDiamonds.DirectAccessObject;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DiyaDiamonds.Model.Product;


@Repository("productDAO")
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static List<Product> products = new ArrayList<>();
	
	@Override
	public Product saveOrUpdateProduct(Product product) {
		Session session=sessionFactory.getCurrentSession();
	    session.saveOrUpdate(product);
	    return product;
	}

	@Override
	public Product getProduct(int id) {
		Session session=sessionFactory.getCurrentSession();
		Product product=(Product)session.get(Product.class, id);
		
		return product;
	}

	@Override
	public void deleteProduct(int id) {
		Session session=sessionFactory.getCurrentSession();
		Product product=(Product)session.get(Product.class, id);
		if(product!=null)
		session.delete(product);
	}

	@Override
	public List<Product> getAllProducts() {
		products= (List<Product>) sessionFactory.getCurrentSession().createQuery("from Product").list();
		
		return products;

	}
	
	public List<Product> getCategoryProducts(int categoryId){
		
		products= (List<Product>) sessionFactory.getCurrentSession().createQuery("from Product where categoryId="+categoryId).list();
		
		return products;
	}

}
