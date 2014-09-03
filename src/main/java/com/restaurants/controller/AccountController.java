package com.restaurants.controller;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.restaurants.entity.Account;
import com.restaurants.entity.BaseEntity;
import com.restaurants.service.AccountService;
import com.restaurants.service.CRUDService;
import com.restaurants.utils.Authenticated;

@Path("/accounts")
@Authenticated
public class AccountController extends CRUDController<Account> {
	
	@Inject
	private AccountService accountService;

	@Override
	public CRUDService<? extends BaseEntity> getService() {
		return accountService;
	}
}

