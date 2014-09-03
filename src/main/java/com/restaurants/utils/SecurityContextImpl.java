package com.restaurants.utils;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.SecurityContext;


public class SecurityContextImpl implements SecurityContext {
	
	private String username;
	private String token;
	private List<String> roles = Arrays.asList("USER");

	public SecurityContextImpl(String username, String token){
		this.username = username;
		this.token = token;
	}

	@Override
	public Principal getUserPrincipal() {
		return new Principal() {
			@Override
			public String getName() {
				return username;
			}
		};
	}

	@Override
	public boolean isUserInRole(String role) {
		return roles.contains(role.toUpperCase());
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAuthenticationScheme() {
		return "custom";
	}

}
