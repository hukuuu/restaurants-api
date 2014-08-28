package com.restaurants.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.restaurants.entity.Account;
import com.restaurants.entity.Restaurant;

@Stateless
public class RestaurantService extends CRUDService<Restaurant> {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private AccountService accountService;

	@Override
	public EntityManager getEm() {
		return em;
	}

	@Override
	public Class<?> getClazz() {
		return Restaurant.class;
	}

	public List<Restaurant> findByUsername(String name) {
		Account account = accountService.findOneByProperty("username", name);
		String string = "select r from Restaurant r where r.account = :account";
		TypedQuery<Restaurant> query = em.createQuery(string, Restaurant.class);
		query.setParameter("account", account);
		return query.getResultList();
	}

}
