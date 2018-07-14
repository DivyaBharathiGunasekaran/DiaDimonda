package com.niit.DiyaDiamonds.DirectAccessObject;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DiyaDiamonds.Model.Address;

@Repository("addressDao")
@Transactional

public class AddressDaoImple implements AddressDao {
	@Autowired
	SessionFactory sf;

	private static List<Address> addresslist = new ArrayList<>();

	Address address = new Address();
	
	public boolean addAddress(Address address) {
		try {
			sf.getCurrentSession().saveOrUpdate(address);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public boolean delAddress(int id) {
		try {

			Session session = sf.getCurrentSession();
			Address address = (Address) session.get(Address.class, id);
			session.delete(address);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Address show(int id) {
		Session session = sf.getCurrentSession();
		Address address = (Address) session.get(Address.class, id);
		return address;
	}

	public List<Address> list(int cartId) {
		List<Address> addressList = (List<Address>) sf.getCurrentSession()
				.createQuery("from Address where cartId=" + cartId).list();
		return addressList;
	}
}
