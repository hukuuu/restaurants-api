package com.restaurants.controller;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.restaurants.entity.BaseEntity;
import com.restaurants.entity.Food;
import com.restaurants.service.CRUDService;
import com.restaurants.service.FoodService;

@Path("/food")
public class FoodController extends CRUDController<Food> {
	
	@Inject
	private FoodService foodService;

	@Override
	public CRUDService<? extends BaseEntity> getService() {
		return foodService;
	}

}
