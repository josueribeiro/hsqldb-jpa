package com.acme.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import com.acme.exception.ValiationException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;

public abstract class GenericDAO<T, PK> implements IGenericDAO<T, PK> {

	private EntityManager entityManager;

	protected void validate(T entity) {
		var violations = Validation.buildDefaultValidatorFactory().getValidator()
				.validate(entity);
		var ex = new ValiationException();
		if (!violations.isEmpty()) {
			for (ConstraintViolation<T> c : violations) {
				ex.addError(c.getMessage());
			}
			throw ex;
		}
	}

	@Override
	public T insert(T entity) {
		validate(entity);
		getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		validate(entity);
		var merge = getEntityManager().merge(entity);
		return merge;
	}

	@Override
	public void delete(T entity) {
		var entityToDelete = loadById(getIdEntity(entity));
		getEntityManager().remove(entityToDelete);
	}

	@Override
	public void deleteAll() {
		var query = getEntityManager().createQuery("delete from " + getParameterizedClass().getSimpleName());
		query.executeUpdate();
	}

	@Override
	public T load(T entity) {
		return loadById(getIdEntity(entity));
	}

	@Override
	public T loadById(PK pk) {
		try {
			return getEntityManager().find(getParameterizedClass(), pk);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<T> loadAll() {
		return getEntityManager()
				.createQuery("select o from " + getParameterizedClass().getSimpleName() + " o", getParameterizedClass())
				.getResultList();
	}

	public List<T> getListByNamedQuery(String nameQuery, String parameter, String value) {
		var query = getEntityManager().createNamedQuery(nameQuery, getParameterizedClass());
		query.setParameter(parameter, value);
		return query.getResultList();
	}

	public List<T> getListByNamedQuery(String namedQuery, Map<String, Object> parameters) {
		var query = getEntityManager().createNamedQuery(namedQuery, getParameterizedClass());
		parameters.forEach((k, v) -> {
			query.setParameter(k, v);
		});
		return query.getResultList();
	}

	public T getByNameQuery(String nameQuery, String parameter, String value) {
		var query = getEntityManager().createNamedQuery(nameQuery, getParameterizedClass());
		query.setParameter(parameter, value);
		return query.getSingleResult();
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	private Class<T> getParameterizedClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	@SuppressWarnings("unchecked")
	public PK getIdEntity(T entity) {
		return (PK) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
	}
}