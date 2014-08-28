package com.restaurants.controller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.restaurants.entity.Account;
import com.restaurants.service.AccountService;

@Path("")
public class LoginController {

	@Inject
	private AccountService accountService;

	@Path("/login")
	@POST
	public Response ping(@Valid Account account) {
		Account acc = accountService.findOneByProperty("username", account.getUsername());
		if(acc != null) {
			String hashed = accountService.hashPassword(account.getPassword());
			if(acc.getPassword().equals(hashed)){
				return Response.ok().build();
			}
		return Response.status(Status.NOT_ACCEPTABLE).entity("password doesnt match").build();
		}
		return Response.status(Status.NOT_FOUND).entity("no such user").build();
	}
}
