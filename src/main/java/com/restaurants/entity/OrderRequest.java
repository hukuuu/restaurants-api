package com.restaurants.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@SuppressWarnings("serial")
@Entity
@Where(clause = "DELETED != 'TRUE'")
@SQLDelete(sql = "UPDATE OrderRequest SET DELETED = 'TRUE' WHERE id=?")
public class OrderRequest extends BaseEntity {

	@ManyToOne
	private Restaurant restaurant;

	@OneToMany
	private List<Food> foods;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	private Integer seats;

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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

}
