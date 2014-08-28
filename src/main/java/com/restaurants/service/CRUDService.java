package com.restaurants.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.restaurants.entity.BaseEntity;


public abstract class CRUDService<T extends BaseEntity> {

	public abstract EntityManager getEm();

	public abstract Class<?> getClazz();

	@SuppressWarnings("unchecked")
	public T find(Long id) {
		return (T) getEm().find(getClazz(), id);
	}

	public T save(BaseEntity entity) {
		T result;
		if (entity.isNew()) {
			getEm().persist(entity);
			result = (T) entity;
		} else {
			result = (T) getEm().merge(entity);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public void delete(Long id) {
		T find = (T) getEm().find(getClazz(), id);
		getEm().remove(find);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getEm().createQuery("SELECT e FROM " + getClazz().getName() + " e ORDER BY e.id").getResultList();
	}
	
	public void deleteAll() {
		Query query = getEm().createQuery("DELETE FROM " + getClazz().getName());
		query.executeUpdate();
	}
	
	public T findOneByProperty(String property, Object value) {
		String queryString = "SELECT e FROM " + getClazz().getName() + " e WHERE e." + property + "=:propertyValue";
		Query query = getEm().createQuery(queryString);
		query.setParameter("propertyValue", value);
		query.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<T> result = query.getResultList();
		return result.isEmpty() ? null : result.get(0);
	}

}
