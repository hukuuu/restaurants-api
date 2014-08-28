package com.restaurants.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Food extends BaseEntity {

	@ManyToOne
	private Restaurant restaurant;
	private String category;
	private String title;
	@Column(length = 500)
	private String description;
	private BigDecimal price;

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public String getTitle() {
		return title;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
