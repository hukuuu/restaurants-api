package com.restaurants.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.restaurants.entity.Account;
import com.restaurants.entity.BaseEntity;
import com.restaurants.entity.Restaurant;
import com.restaurants.service.AccountService;
import com.restaurants.service.CRUDService;
import com.restaurants.service.RestaurantService;
import com.restaurants.service.UtilsService;
import com.restaurants.utils.Authenticated;

@Path("/restaurants")
@Authenticated
public class RestaurantController extends CRUDController<Restaurant>{
	
	@Context
	private SecurityContext sc;
	
	@Inject
	private RestaurantService restaurantService;
	
	@Inject
	private AccountService accountService;
	
	@Inject
	private UtilsService utilsService;

	@Override
	public CRUDService<? extends BaseEntity> getService() {
		return restaurantService;
	}
	
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Restaurant entity) {
		Account user = accountService.findOneByProperty("username", sc.getUserPrincipal().getName());
		entity.setAccount(user);
		return super.create(entity);
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
