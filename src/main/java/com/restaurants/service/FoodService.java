package com.restaurants.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.restaurants.entity.Food;

@Stateless
public class FoodService extends CRUDService<Food> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEm() {
		return em;
	}

	@Override
	public Class<?> getClazz() {
		return Food.class;
	}

}
