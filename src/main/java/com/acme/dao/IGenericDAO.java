package com.acme.dao;

import java.util.List;

import jakarta.persistence.EntityManager;

public interface IGenericDAO<T, PK> {

	public T insert(T entity);

	public T update(T entity);

	public void delete(T entity);

	public void deleteAll();

	public T load(T entity);

	public T loadById(PK pk);

	public List<T> loadAll();

	public PK getIdEntity(T entity);

	public EntityManager getEntityManager();

}
