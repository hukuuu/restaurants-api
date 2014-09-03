package com.restaurants.service;

import static org.jboss.security.auth.spi.Util.BASE64_ENCODING;
import static org.jboss.security.auth.spi.Util.createPasswordHash;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.resteasy.util.Base64;

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
		String hashed = hash(account.getPassword());
		account.setPassword(hashed);
		account.setRoles(Arrays.asList(Role.USER));
		return save(account);
	}
	
	public String hash(String string) {
		String hash = createPasswordHash("SHA", BASE64_ENCODING, null, null,
				string);

		return hash;
	}
	
	public Account authenticate(String token) throws Exception {
		String base64Credentials = token.substring("Basic".length()).trim();
        String credentials = new String(Base64.decode(base64Credentials),
                Charset.forName("UTF-8"));
        // credentials = username:password
        final String[] values = credentials.split(":",2);

        if(values.length != 2){
        	throw new Exception("could not extract username and password from provided token " + token);
        }
        
        Account user = findOneByProperty("username", values[0]);
        if(user == null)
        	throw new Exception("no such user " + values[0]);

        if(user.getPassword() != null && user.getPassword().equals(hash(values[1]))) return user;
        
        throw new Exception("passwords doesnt match " + values[1]);
	}

}
