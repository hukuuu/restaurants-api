package com.restaurants.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.restaurants.controller.AccountController;
import com.restaurants.controller.FoodController;
import com.restaurants.controller.LoginController;
import com.restaurants.controller.RestaurantController;
import com.restaurants.filter.RestCorsFilter;
import com.restaurants.filter.RestCorsRequestFilter;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		 classes.add(AccountController.class);
		 classes.add(RestaurantController.class);
		 classes.add(FoodController.class);
		 classes.add(LoginController.class);
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		final Set<Object> singletons = new HashSet<Object>();
		singletons.add(new RestCorsFilter());
		singletons.add(new RestCorsRequestFilter());
		return singletons;
	}

}
