package com.restaurants.filter;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.restaurants.entity.Account;
import com.restaurants.service.AccountService;
import com.restaurants.utils.Authenticated;
import com.restaurants.utils.SecurityContextImpl;

@Authenticated
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

	@Inject
	private AccountService accountService;

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {

		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		List<String> authorizationHeaders = headers.get("Authorization");

		if (authorizationHeaders == null || authorizationHeaders.size() == 0) {
			requestContext.abortWith(Response.status(Status.UNAUTHORIZED)
					.build());
		}
		try {
			Account account = accountService.authenticate(authorizationHeaders
					.get(0));
			requestContext.setSecurityContext(new SecurityContextImpl(account.getUsername(), account.getPassword()));
		} catch (Exception e) {
			requestContext.abortWith(Response.status(Status.UNAUTHORIZED).entity(e.getMessage()).build());
		}
	}

}
