package com.niit.DiyaDiamonds;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.niit.DiyaDiamonds.DirectAccessObject.CategoryDataAccessObject;
import com.niit.DiyaDiamonds.Model.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =Config.class)
public class TestCategoryDao 
{
	@Autowired
	CategoryDataAccessObject categorydao;
	 Category category = new Category();
	
	

	@Before
	public void setUp() throws Exception {
		
		category.setCategoryname("chocker");
		category.setCategoryDescription("chocker Description");
	}

	@After
	public void tearDown() {
		categorydao.deleteCategory(category.getCategoryId());
	}

	@Ignore
	@Test
	public void test() 
	{
		categorydao.addCategory(category);
		
		List<Category> list = categorydao.listCategories();
		assertTrue("success", list.size()>0);
	}

}
	
	