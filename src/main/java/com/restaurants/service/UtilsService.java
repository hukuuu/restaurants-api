package com.restaurants.service;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.restaurants.entity.Account;

@Stateless
public class UtilsService {
	
	@Inject
	private AccountService accountService;
	
//	@Resource
//	private SessionContext sc;
	
	@Context
	private SecurityContext sc;
	
	public Account getCurrentUser() {
//		String name = sc.getCallerPrincipal().getName();
		String name = sc.getUserPrincipal().getName();
		System.out.println(name);
		Account user = accountService.findOneByProperty("username", name);
		System.out.println(user);
		return user;
	}
}
