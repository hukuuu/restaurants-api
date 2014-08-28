package com.restaurants.service;

import static org.jboss.security.auth.spi.Util.BASE64_ENCODING;
import static org.jboss.security.auth.spi.Util.createPasswordHash;

import java.util.Arrays;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.restaurants.entity.Account;
import com.restaurants.enums.Role;

@Stateless
public class AccountService extends CRUDService<Account> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEm() {
		return em;
	}

	@Override
	public Class<?> getClazz() {
		return Account.class;
	}
	
	
	public Account register( Account account) {
		String hashed = hashPassword(account.getPassword());
		account.setPassword(hashed);
		account.setRoles(Arrays.asList(Role.USER));
		return save(account);
	}
	
	public String hashPassword(String password) {
		String hash = createPasswordHash("SHA", BASE64_ENCODING, null, null,
				password);
		return hash;
	}

}
