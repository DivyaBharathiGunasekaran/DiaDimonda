package com.niit.DiyaDiamonds.DirectAccessObject;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.niit.DiyaDiamonds.Model.Category;
@Repository("categoryDao")
@Transactional
public class CategoryDAOImpleClass implements CategoryDataAccessObject {
	@Autowired
	SessionFactory sf;

	public boolean addCategory(Category category) 
	{
		try {
			sf.getCurrentSession().saveOrUpdate(category);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	
	public boolean deleteCategory(int categoryId) {
		try {
			sf.getCurrentSession().delete(getCategorydetails(categoryId));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Category getCategorydetails(int categoryId) {
		try {
			Category category = (Category) sf.getCurrentSession().get(Category.class, categoryId);
			return category;
		} catch (Exception e) {
			return null;
		}
	}

public List<Category> listCategories() 
	{
	try
	{
		List<Category> category = (List<Category>)sf.getCurrentSession().createQuery("from Category").list();
		return category;
	} catch (Exception e)
	{
		return null;
	}
	}
}