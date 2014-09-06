package com.restaurants.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.restaurants.entity.Food;
import com.restaurants.entity.Restaurant;

@Stateless
public class FoodService extends CRUDService<Food> {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private RestaurantService restaurantService;

	@Override
	public EntityManager getEm() {
		return em;
	}

	@Override
	public Class<?> getClazz() {
		return Food.class;
	}
	
	public List<Food> findByRestaurantId(Long id) {
		return restaurantService.find(id).getFoods();
	}

	public Food saveForRestaurantId(Long restaurantId, Food food) {
		Restaurant restaurant = restaurantService.find(restaurantId);
		food.setRestaurant(restaurant);
		return save(food);
	}

}
