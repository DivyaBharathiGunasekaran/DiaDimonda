package com.niit.DiyaDiamonds.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;
@Entity 
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int categoryId;
	@Column(unique=true,nullable=false)
	@NotEmpty(message="category cannot be blank")
	private String categoryname;
	@NotEmpty(message="description cannot be blank")
	private String categoryDescription;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	

}
