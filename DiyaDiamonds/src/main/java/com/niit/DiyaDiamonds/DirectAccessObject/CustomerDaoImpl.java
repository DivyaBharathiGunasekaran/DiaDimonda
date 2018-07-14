package com.niit.DiyaDiamonds.DirectAccessObject;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DiyaDiamonds.Model.Customer;
import com.niit.DiyaDiamonds.Model.UserCred;


@Repository("customerDAO")
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	SessionFactory sessionFactory;
	@Override
	public boolean addCustomer(Customer customer) 
	{
		try {
			UserCred u= new UserCred();
			u.setEmailId(customer.getEmailId());
			u.setPassword(customer.getPasssword());
			u.setRole("ROLE_USER");
			sessionFactory.getCurrentSession().saveOrUpdate(customer);
			sessionFactory.getCurrentSession().saveOrUpdate(u);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delCustomer(String emailId) {
		try {
			sessionFactory.getCurrentSession().delete(showCustomer(emailId));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Customer showCustomer(String emailId) {
		try {
			Customer customer= (Customer) sessionFactory.getCurrentSession().createQuery("from Customer where emailId ='"+emailId+"'").uniqueResult();
			
			return customer;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Customer> showAllCustomer() {
		try {
			ArrayList<Customer> customer = (ArrayList<Customer>) sessionFactory.getCurrentSession().createQuery("from Customer").list();
			return customer;
		} catch (Exception e) {
			return null;
		}
	}

}
