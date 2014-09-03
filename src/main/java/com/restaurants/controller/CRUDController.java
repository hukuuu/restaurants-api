package com.restaurants.controller;

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

import com.restaurants.entity.BaseEntity;
import com.restaurants.service.CRUDService;
import com.restaurants.utils.ObjectCopy;

public abstract class CRUDController<K extends BaseEntity> {
	
	@Inject
	private ObjectCopy objectCopy;

	public abstract CRUDService<? extends BaseEntity> getService();
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<K> all = (List<K>) getService().findAll();
		return Response.ok(all).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOne(@PathParam("id") Long id) {
		K find = (K) getService().find(id);
		if(find == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok(find).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		getService().delete(id);
		return Response.ok().build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Valid K entity) {
		K save = (K) getService().save(entity);
		return Response.ok(save).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, @Valid K account) throws IllegalArgumentException, IllegalAccessException {
		K find = (K) getService().find(id);
		if(find == null) 
			return Response.status(Status.NOT_FOUND).build();
		objectCopy.copy(find, account);
		K save = (K) getService().save(find);
		return Response.ok(save).build();
	}	
}
