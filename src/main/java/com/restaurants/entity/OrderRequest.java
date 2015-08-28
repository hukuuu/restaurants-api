package com.restaurants.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@SuppressWarnings("serial")
@Entity
@Where(clause="DELETED != 'TRUE'")
@SQLDelete(sql = "UPDATE OrderRequest SET DELETED = 'TRUE' WHERE id=?")
public class OrderRequest extends BaseEntity {
	
	@ManyToOne
	private Restaurant restaurant;
	
	@OneToMany
	private List<Food> foods;

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
	
	

}
