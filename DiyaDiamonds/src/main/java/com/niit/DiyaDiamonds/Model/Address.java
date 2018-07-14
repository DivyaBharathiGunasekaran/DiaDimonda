package com.niit.DiyaDiamonds.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Address
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int cartId;
	@NotEmpty(message = "Address can be combination of Number and Place blank")
	private String addressline1;
	@NotEmpty(message = "city cannot be blank")
	private String city;
	@NotEmpty(message = "state cannot be blank")
	private String state;
	@NotEmpty(message = "pincode cannot be blank")
	@Size(min = 6, max = 6)
	@Pattern(regexp="[1-9][0-9]{5}", message="must start with either 1 to 9 and have 6 numbers")
	private String pincode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddressline1() {
		return addressline1;
	}
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String  getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	


}
