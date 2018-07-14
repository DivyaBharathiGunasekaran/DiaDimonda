package com.niit.DiyaDiamonds.DirectAccessObject;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DiyaDiamonds.Model.CustomerOrder;
@Repository("orderDAO")
@Transactional

public class OrderDaoImple implements OrderDao {
	@Autowired
	SessionFactory sessionFactory;



	@Override
	public boolean insert(CustomerOrder order) {
	
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(order);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CustomerOrder> viewAllOrder(int cartId) {
		
		try {
			List<CustomerOrder> orderlist = (List<CustomerOrder>) sessionFactory.getCurrentSession()
					.createQuery("from CustomerOrder where cartId=" + cartId).list();
			return orderlist;
		} catch (Exception e) {
			return null;
		}
	}
	

	@Override
	public List<CustomerOrder> viewreceipt(String orderid) {
	
		try {
			List<CustomerOrder> orderlist = (List<CustomerOrder>) sessionFactory.getCurrentSession()
					.createQuery("from CustomerOrder where orderId='"+ orderid+"'").list();
			return orderlist;
		} catch (Exception e) {
			return null;
		}
	}
	}
