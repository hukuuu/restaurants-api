package com.restaurants.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.restaurants.entity.Food;
import com.restaurants.service.FoodService;
import com.restaurants.utils.ObjectCopy;

@Path("/restaurants/{restaurantId}/food")
public class FoodController {

	@Inject
	private FoodService foodService;

	@Inject
	private ObjectCopy objectCopy;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@PathParam("restaurantId") Long restaurantId) {
		List<Food> food = foodService.findByRestaurantId(restaurantId);
		if (food == null)
			food = new ArrayList<Food>();
		return Response.ok(food).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(@PathParam("restaurantId") Long restaurantId,
			@Valid Food food) {
		foodService.saveForRestaurantId(restaurantId, food);
		return Response.ok().build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, @Valid Food food)
			throws IllegalArgumentException, IllegalAccessException {
		Food find = foodService.find(id);
		if (find == null)
			return Response.status(Status.NOT_FOUND).build();
		objectCopy.copy(find, food);
		Food save = foodService.save(find);
		return Response.ok(save).build();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getOne(@PathParam("id") Long id, @Valid Food food) {
		Food find = foodService.find(id);
		if (find == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok(find).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Long id) {
		foodService.delete(id);
		return Response.ok().build();
	}

}
