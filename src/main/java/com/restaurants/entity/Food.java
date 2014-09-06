package com.restaurants.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Where(clause="DELETED != 'TRUE'")
@SQLDelete(sql = "UPDATE Food SET DELETED = 'TRUE' WHERE id=?")
public class Food extends BaseEntity {

	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	private String category;
	private String title;
	@Column(length = 500)
	private String description;
	private BigDecimal price;
	private String picture;
	private String serving;

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public String getPicture() {
		return picture;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public String getServing() {
		return serving;
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

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setServing(String serving) {
		this.serving = serving;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
