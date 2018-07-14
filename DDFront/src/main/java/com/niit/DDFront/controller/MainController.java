package com.niit.DDFront.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.niit.DiyaDiamonds.DirectAccessObject.AddressDao;
import com.niit.DiyaDiamonds.DirectAccessObject.Cartdao;
import com.niit.DiyaDiamonds.DirectAccessObject.CategoryDataAccessObject;
import com.niit.DiyaDiamonds.DirectAccessObject.CustomerDao;
import com.niit.DiyaDiamonds.DirectAccessObject.OrderDao;
import com.niit.DiyaDiamonds.DirectAccessObject.ProductDao;
import com.niit.DiyaDiamonds.Model.Address;
import com.niit.DiyaDiamonds.Model.Cart;
import com.niit.DiyaDiamonds.Model.Category;
import com.niit.DiyaDiamonds.Model.Customer;
import com.niit.DiyaDiamonds.Model.CustomerOrder;
import com.niit.DiyaDiamonds.Model.Product;

@Controller
public class MainController {

	@Autowired
	CategoryDataAccessObject categoryDao;
	@Autowired
	ProductDao productDAO;
	@Autowired
	CustomerDao customerDAO;
	@Autowired
	Cartdao cartdao;
	@Autowired
	AddressDao addressdao;
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	OrderDao orderDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public String index(Model model) {

		model.addAttribute("title", "Home");
		model.addAttribute("userClickHome", true);

		return "mainpage";

	}

	@RequestMapping(value = "about")
	public String about(Model model) {

		model.addAttribute("title", "About Us");

		model.addAttribute("userClickAbout", true);
		return "mainpage";

	}

	@RequestMapping(value = "contact")
	public String contact(Model model) {

		model.addAttribute("title", "Contact Us");

		model.addAttribute("userClickContact", true);
		return "mainpage";

	}
	
	
	@RequestMapping(value="sendmail")
	public String sendmail(HttpServletRequest request) {
		try {
			String recipientAddress ="ddiyadiamonds@gmail.com";
			String uname = request.getParameter("uname");
			String usubject = request.getParameter("usubject");
			String uphno = request.getParameter("uphno");
			String umessage = request.getParameter("umessage");
			String finalmessage = "Hi Admin, "+umessage+" Contact me in:"+uphno+"\n\n\n regards\n\n"+uname;
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(recipientAddress);
			email.setSubject(usubject);
			email.setText(finalmessage);
			mailSender.send(email);
		}
		catch(Exception e) {
			
		}
		return "redirect:/contact";
		}
	@RequestMapping(value = "admin/category")
	public String Category(Model M) {

		M.addAttribute("categorylist", categoryDao.listCategories());
		M.addAttribute("status", false);
		M.addAttribute("edit", false);
		M.addAttribute("category", new Category());
		M.addAttribute("title", "Category");
		M.addAttribute("userClickCategory", true);
		return "mainpage";

	}

	@RequestMapping(value = "admin/setcategory")
	public String setcat(@Valid @ModelAttribute("category") Category category, BindingResult result, Model M) {

		if (result.hasErrors()) {
			M.addAttribute("categorylist", categoryDao.listCategories());
			M.addAttribute("status", true);
			M.addAttribute("edit", false);
			M.addAttribute("category", category);
			M.addAttribute("title", "Category");
			M.addAttribute("userClickCategory", true);
			return "mainpage";

		}
		try {
			categoryDao.addCategory(category);
			return "redirect:/admin/category";
		} catch (Exception e) {
			M.addAttribute("categorylist", categoryDao.listCategories());
			M.addAttribute("status", true);
			M.addAttribute("edit", false);
			M.addAttribute("category", new Category());
			M.addAttribute("title", "Category");
			M.addAttribute("userClickCategory", true);
			return "mainpage";
		}

	}

	@RequestMapping(value = "admin/delcat/{categoryId}")
	public String delcat(@PathVariable int categoryId, Model M) {
		try {
			categoryDao.deleteCategory(categoryId);
			return "redirect:/admin/category";
		} catch (Exception e) {
			M.addAttribute("categorylist", categoryDao.listCategories());
			M.addAttribute("status", true);
			M.addAttribute("edit", false);
			M.addAttribute("category", new Category());
			M.addAttribute("title", "Category");
			M.addAttribute("userClickCategory", true);
			return "mainpage";
		}
	}

	@RequestMapping(value = "admin/editcat/{categoryId}")
	public String showcategory(@PathVariable int categoryId, Model M) {

		M.addAttribute("categorylist", categoryDao.listCategories());
		M.addAttribute("status", false);
		M.addAttribute("edit", true);
		M.addAttribute("category", categoryDao.getCategorydetails(categoryId));
		M.addAttribute("title", "Category");
		M.addAttribute("userClickCategory", true);
		return "mainpage";

	}

	@RequestMapping(value = "admin/products")
	public String manageProducts(Model model) {
		model.addAttribute("title", "Manage Products");
		model.addAttribute("userClickProduct", true);
		model.addAttribute("productlist", productDAO.getAllProducts());
		model.addAttribute("product", new Product());
		model.addAttribute("status", false);
		model.addAttribute("edit", false);
		model.addAttribute("categorylist", categoryDao.listCategories());
		return "mainpage";
	}

	@RequestMapping(value = "admin/setproduct")
	public String setProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Manage Products");
			model.addAttribute("userClickProduct", true);
			model.addAttribute("product", product);
			model.addAttribute("status", true);
			model.addAttribute("edit", false);
			model.addAttribute("categorylist", categoryDao.listCategories());
			model.addAttribute("productlist", productDAO.getAllProducts());
			return "mainpage";
		} else {
			try {
				productDAO.saveOrUpdateProduct(product);
				uploadfile(product.getId(), product.getPimage());
				return "redirect:/admin/products";
			}

			catch (Exception e) {
				model.addAttribute("title", "Manage Products");
				model.addAttribute("userClickProduct", true);
				model.addAttribute("product", product);
				model.addAttribute("status", true);
				model.addAttribute("edit", false);
				model.addAttribute("categorylist", categoryDao.listCategories());
				model.addAttribute("productlist", productDAO.getAllProducts());
				return "mainpage";
			}
		}

	}

	void uploadfile(int id, MultipartFile f) throws Exception {
		String path = "E:\\NIIT Project\\newfolder\\DDFront\\src\\main\\webapp\\resources\\pimages\\";
		path = path + String.valueOf(id + ".jpg");
		if (!f.isEmpty()) {
			byte[] b = f.getBytes();
			BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(new File(path)));
			bs.write(b);
			bs.close();
			Thread.sleep(10000);
		}
	}

	@RequestMapping(value = "admin/delprod/{id}")
	public String delprod(@PathVariable int id, Model model) {
		try {
			productDAO.deleteProduct(id);
			String path = "E:\\NIIT Project\\newfolder\\DDFront\\src\\main\\webapp\\resources\\pimages\\";
			path = path + String.valueOf(id + ".jpg");
			Path paths = Paths.get(path);
			if (Files.exists(paths)) {
				try {
					Files.delete(paths);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return "redirect:/admin/products";
		} catch (Exception e) {
			model.addAttribute("title", "Manage Products");
			model.addAttribute("userClickProduct", true);
			model.addAttribute("productlist", productDAO.getAllProducts());
			model.addAttribute("product", new Product());
			model.addAttribute("status", false);
			model.addAttribute("edit", false);
			model.addAttribute("categorylist", categoryDao.listCategories());
			return "mainpage";
		}
	}

	@RequestMapping(value = "admin/editprod/{id}")
	public String editProduct(@PathVariable int id, Product product, Model M) {

		M.addAttribute("productlist", productDAO.getAllProducts());
		M.addAttribute("status", false);
		M.addAttribute("edit", true);
		M.addAttribute("product", productDAO.getProduct(id));
		M.addAttribute("title", "Edit Product");
		M.addAttribute("userClickProduct", true);
		M.addAttribute("categorylist", categoryDao.listCategories());
		return "mainpage";

	}

	@RequestMapping(value = "productgrid")
	public String products(Model model) {

		model.addAttribute("title", "Products");
		model.addAttribute("productlist", productDAO.getAllProducts());
		model.addAttribute("userClickProducts", true);
		return "mainpage";

	}

	@RequestMapping(value = "info/{id}")
	public String productInfo(@PathVariable int id, Product product, Model M) {

		M.addAttribute("productlist", productDAO.getAllProducts());
		M.addAttribute("product", productDAO.getProduct(id));
		M.addAttribute("title", "Product Info");
		M.addAttribute("userClickProductInfo", true);
		return "mainpage";

	}

	@RequestMapping(value = "login")
	public String login(Model model) {

		model.addAttribute("title", "Sign In");

		model.addAttribute("userClickLogin", true);

		return "mainpage";

	}

	@RequestMapping(value = "register")
	public String register(Model model) {

		model.addAttribute("title", "Sign Up");

		model.addAttribute("userClickRegister", true);
		model.addAttribute("customer", new Customer());
		return "mainpage";

	}

	@RequestMapping(value = "addcustomer")
	public String addcustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Register");
			model.addAttribute("userClickRegister", true);
			model.addAttribute("customer", customer);
			model.addAttribute("status", true);

			return "mainpage";
		}

		try {
			customerDAO.addCustomer(customer);
			return "redirect:/home";
		} catch (Exception e) {
			model.addAttribute("title", "Register");
			model.addAttribute("userClickRegister", true);
			model.addAttribute("customer", customer);
			model.addAttribute("status", true);
			return "mainpage";
		}

	}

	@RequestMapping("/loginerror")
	public String loginFailure(Model model) {
		model.addAttribute("title", "Sign In");
		model.addAttribute("loginerror", true);
		model.addAttribute("userClickLogin", true);
		model.addAttribute("error", "Invalid email/password..please enter valid email id");
		return "mainpage";
	}

	@RequestMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("msg", "Loggedout successfully..");
		model.addAttribute("title", "Sign In");
		model.addAttribute("userClickLogin", true);
		model.addAttribute("loginerror", false);
		model.addAttribute("logout", true);
		return "mainpage";
	}

	@RequestMapping(value = "loginsuccess")
	public String loginsuccess(HttpSession session, Model M) {
		String useremail = SecurityContextHolder.getContext().getAuthentication().getName();
		Customer c = customerDAO.showCustomer(useremail);
		Collection<GrantedAuthority> authority = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();
		for (GrantedAuthority permission : authority) {
			if (permission.getAuthority().equals("ROLE_USER")) {
				session.setAttribute("username", c.getName());
				session.setAttribute("useremail", c.getEmailId());
				session.setAttribute("usercartid", c.getCartId());
				session.setAttribute("userlogin", true);
				session.setAttribute("cartsize", cartdao.show(c.getCartId()).size());
				M.addAttribute("title", "Products");
				M.addAttribute("userClickProducts", true);
				M.addAttribute("productlist", productDAO.getAllProducts());
				M.addAttribute("catlist", categoryDao.listCategories());

			} else {
				session.setAttribute("username", "Admin");
				session.setAttribute("userlogin", false);
				M.addAttribute("title", "home");
				M.addAttribute("userClickHome", true);
				M.addAttribute("catlist", categoryDao.listCategories());
			}
		}
		return "mainpage";

	}

	@RequestMapping(value = "cart/addtocart/{id}")
	public String addcart(@PathVariable int id, @RequestParam(value = "qnty") int qnty, Model model,
			HttpSession session) {
		ArrayList<Cart> cartlist = (ArrayList<Cart>) cartdao
				.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
		Product product = productDAO.getProduct(id);
		if(product.getQuantity()>=qnty)
		{
			
		for (Cart cartItem : cartlist) {
			if (cartItem.getPid() == id) {
				cartItem.setQty(qnty);
				cartItem.setTotal(qnty * product.getPrice());
				cartdao.add(cartItem);
				return "redirect:/cart/viewcart";
			}
		}

		Cart cart = new Cart();
		cart.setCartId(Integer.parseInt(session.getAttribute("usercartid").toString()));
		cart.setPid(product.getId());
		cart.setPname(product.getName());
		cart.setQty(qnty);
		cart.setPprice(product.getPrice());
		cart.setTotal(product.getPrice());
		cartdao.add(cart);

		return "redirect:/cart/viewcart";

	}
		else
		{   model.addAttribute("msg",true);
			model.addAttribute("productlist", productDAO.getAllProducts());
			model.addAttribute("product", productDAO.getProduct(id));
			model.addAttribute("title", "Product Info");
			model.addAttribute("userClickProductInfo", true);
			return "mainpage";
		}
	}
	
	@RequestMapping(value = "cart/viewcart")
	public String viewcart(Model model, HttpSession session) {
		ArrayList<Cart> cartlist = (ArrayList<Cart>) cartdao
				.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
		model.addAttribute("title", "Cart");
		model.addAttribute("cartlist", cartlist);
		model.addAttribute("userClickCart", true);
		session.setAttribute("cartsize", cartlist.size());
		return "mainpage";

	}

	@RequestMapping(value = "cart/deletecart/{id}")
	public String delcart(@PathVariable int id, Model model, HttpSession session) {
		ArrayList<Cart> cartlist = (ArrayList<Cart>) cartdao
				.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
		cartdao.delete(id);
		return "redirect:/cart/viewcart";
	}

	@RequestMapping(value = "address")
	public String address(Model M, HttpSession session) {

		M.addAttribute("title", "Address");
		M.addAttribute("catlist", categoryDao.listCategories());
		M.addAttribute("userClickCheckOut", true);
		M.addAttribute("address", new Address());
		M.addAttribute("addresslist", addressdao.list(Integer.parseInt(session.getAttribute("usercartid").toString())));
		return "mainpage";
	}

	@RequestMapping(value = "setaddress")
	public String setCategory(@Valid @ModelAttribute("address") Address address, BindingResult result, Model M,
			HttpSession session) {

		if (result.hasErrors()) {
			M.addAttribute("title", "Address");
			M.addAttribute("userClickCheckOut", true);
			M.addAttribute("catlist", categoryDao.listCategories());
			M.addAttribute("address", address);
			M.addAttribute("addresslist",
					addressdao.list(Integer.parseInt(session.getAttribute("usercartid").toString())));

			return "mainpage";
		}

		try {

			address.setCartId(Integer.parseInt(session.getAttribute("usercartid").toString()));
			addressdao.addAddress(address);
			return "redirect:/address";
		} catch (Exception e) {
			M.addAttribute("title", "Address");
			M.addAttribute("userClickCheckOut", true);
			M.addAttribute("catlist", categoryDao.listCategories());
			M.addAttribute("address", address);
			M.addAttribute("addresslist",
					addressdao.list(Integer.parseInt(session.getAttribute("usercartid").toString())));
			return "mainpage";
		}

	}

	@RequestMapping(value = "editadd/{id}")
	public String editAddress(@PathVariable int id, Product product, Model M, HttpSession session) {
		M.addAttribute("title", "Address");
		M.addAttribute("userClickCheckOut", true);
		M.addAttribute("catlist", categoryDao.listCategories());
		M.addAttribute("address", addressdao.show(id));
		M.addAttribute("addresslist", addressdao.list(Integer.parseInt(session.getAttribute("usercartid").toString())));
		return "mainpage";
	}

	@RequestMapping(value = "deladd/{id}")
	public String deladd(@PathVariable int id, Model M) {
		addressdao.delAddress(id);
		return "redirect:/address";
	}

	@RequestMapping(value = "Invoice/{aid}")
	public String invoice(@PathVariable int aid, Model model, HttpSession session) {
		ArrayList<Cart> cartlist = (ArrayList<Cart>) cartdao
				.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
		Long uuid = UUID.randomUUID().getMostSignificantBits();
		String id = "OD" + uuid.toString().replace('-', '2');
		Iterator<Cart> cartiterator = cartlist.listIterator();
		while (cartiterator.hasNext()) {
			Cart cart = cartiterator.next();
			Product product = productDAO.getProduct(cart.getPid());
			product.setQuantity(product.getQuantity() - cart.getQty());
			productDAO.saveOrUpdateProduct(product);

			CustomerOrder c = new CustomerOrder();
			c.setCartId(cart.getCartId());
			c.setOrderId(id);
			c.setAddid(aid);
			c.setDate(new Date());
			c.setPid(cart.getPid());
			c.setPname(cart.getPname());
			c.setQty(cart.getQty());
			c.setSubtotal(cart.getTotal());
			orderDAO.insert(c);
			cartdao.delete(cart.getId());


					try {
								String recipientAddress =SecurityContextHolder.getContext().getAuthentication().getName();
								Customer customer = customerDAO.showCustomer(recipientAddress);
								String uname = customer.getName(); 
								String usubject = "Order Confirmation";
								String finalmessage = "Hi"+ uname+":,\n\n Your order is confirmed.\n\n Your order number is"+id+"\n\n\n regards\n\n Admin";
								SimpleMailMessage email = new SimpleMailMessage();
								email.setTo(recipientAddress);
								email.setSubject(usubject);
								email.setText(finalmessage);
								mailSender.send(email);
							}
							catch(Exception e) {
								
							}
		}

		return "redirect:/viewbill/" + id + "/" + aid;
	}

	@RequestMapping(value = "viewbill/{oid}/{aid}")
	public String viewbill(Model M, HttpSession session, @PathVariable String oid, @PathVariable int aid) {
		List<CustomerOrder> custorder = orderDAO.viewreceipt(oid);
		M.addAttribute("title", "Order");
		M.addAttribute("catlist", categoryDao.listCategories());
		M.addAttribute("userClickInvoice", true);
		M.addAttribute("baddress", addressdao.show(aid));
		M.addAttribute("orderid", oid);
		M.addAttribute("orderdetail", custorder);
		session.setAttribute("cartsize",
				cartdao.show(Integer.parseInt(session.getAttribute("usercartid").toString())).size());
		return "mainpage";

	}
	

}
