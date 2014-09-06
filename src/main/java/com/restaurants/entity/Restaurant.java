package com.restaurants.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Where(clause="DELETED != 'TRUE'")
@SQLDelete(sql = "UPDATE Restaurant SET DELETED = 'TRUE' WHERE id=?")
public class Restaurant extends BaseEntity {

	@JsonIgnore
	@ManyToOne
	private Account account;
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy="restaurant")
	private List<Food> foods;
	private String openHours;
	private String phone;
	private String address;
	private String category;

	public Account getAccount() {
		return account;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public String getName() {
		return name;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpenHours() {
		return openHours;
	}

	public void setOpenHours(String openHours) {
		this.openHours = openHours;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
