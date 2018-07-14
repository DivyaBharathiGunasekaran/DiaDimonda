package com.niit.DiyaDiamonds.DirectAccessObject;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.niit.DiyaDiamonds.Model.Cart;
@Repository("cartdao")
@Transactional
public class CartDaoImple implements Cartdao
{
@Autowired
SessionFactory sessionFactory;
private static List<Cart> cartItems=new ArrayList<>();
@Override
public boolean add(Cart cart)
{
try 
{
sessionFactory.getCurrentSession().saveOrUpdate(cart);
return true;
} 
catch (Exception e) 
{
e.printStackTrace();
return false;
}
}
@Override
public boolean delete(int id) 
{
try 
{

Cart cart = sessionFactory.getCurrentSession().get(Cart.class, id);
sessionFactory.getCurrentSession().delete(cart);
return true;
} 
catch (Exception e) 
{
e.printStackTrace();
return false;
}
}

@Override
public List<Cart> show(int cartId)
{
try 
{
cartItems=(List<Cart>) sessionFactory.getCurrentSession().createQuery("from Cart where cartId="+cartId).list();
return cartItems;
}
catch (Exception e) 
{
	return null;
}
}
}