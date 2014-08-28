package com.restaurants.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.restaurants.entity.BaseEntity;
import com.restaurants.entity.Restaurant;
import com.restaurants.service.CRUDService;
import com.restaurants.service.RestaurantService;

@Path("/secure/restaurants")
public class RestaurantController extends CRUDController<Restaurant>{
	
	@Context
	private SecurityContext sc;
	
	@Inject
	private RestaurantService restaurantService;

	@Override
	public CRUDService<? extends BaseEntity> getService() {
		return restaurantService;
	}
	
	
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Restaurant> restaurants = restaurantService.findByUsername(sc.getUserPrincipal().getName());
		if(restaurants == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok().entity(restaurants).build();
	}
	


}
