package com.restaurants.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.restaurants.entity.OrderRequest;

@Stateless
public class OrderRequestService extends CRUDService<OrderRequest> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getClazz() {
		// TODO Auto-generated method stub
		return null;
	}

}
