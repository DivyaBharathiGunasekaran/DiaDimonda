package com.niit.DiyaDiamonds.DirectAccessObject;

import java.util.List;

import com.niit.DiyaDiamonds.Model.Category;

public interface CategoryDataAccessObject {
	public boolean addCategory(Category category);


	public boolean deleteCategory(int categoryId);

	public Category getCategorydetails(int categoryId);

	public List<Category> listCategories();

}
