package com.restaurants.entity;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurants.enums.Role;

@SuppressWarnings("serial")
@Entity
@Where(clause="DELETED != 'TRUE'")
@SQLDelete(sql = "UPDATE Account SET DELETED = 'TRUE' WHERE id=?")
public class Account extends BaseEntity {

	private String username;
	private String password;
	@ElementCollection
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private List<Role> roles;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Restaurant> restaurants;

	public String getPassword() {
		return password;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
