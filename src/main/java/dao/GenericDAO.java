package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import exception.ValiationException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;

public abstract class GenericDAO<T, PK> implements IGenericDAO<T, PK> {

	private EntityManager entityManager;

	protected void validate(T entity) {
		Set<ConstraintViolation<T>> violations = Validation.buildDefaultValidatorFactory().getValidator()
				.validate(entity);
		ValiationException ex = new ValiationException();
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
		T merge = getEntityManager().merge(entity);
		return merge;
	}

	@Override
	public void delete(T entity) {
		T entityToDelete = loadById(getIdEntity(entity));
		getEntityManager().remove(entityToDelete);
	}

	@Override
	public void deleteAll() {
		Query q = getEntityManager().createQuery("delete from " + getParameterizedClass().getSimpleName());
		q.executeUpdate();
	}

	@Override
	public T load(T entity) {
		return loadById(getIdEntity(entity));
	}

	@Override
	public T loadById(PK pk) {
		try {
			return (T) getEntityManager().find(getParameterizedClass(), pk);
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
		TypedQuery<T> query = getEntityManager().createNamedQuery(nameQuery, getParameterizedClass());
		query.setParameter(parameter, value);
		return query.getResultList();
	}

	public List<T> getListByNamedQuery(String namedQuery, Map<String, Object> parameters) {
		TypedQuery<T> query = getEntityManager().createNamedQuery(namedQuery, getParameterizedClass());
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}

	public T getByNameQuery(String nameQuery, String parameter, String value) {
		TypedQuery<T> query = getEntityManager().createNamedQuery(nameQuery, getParameterizedClass());
		query.setParameter(parameter, value);
		return (T) query.getSingleResult();
	}

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

	@SuppressWarnings("unchecked")
	public PK getIdEntity(T entity) {
		// TODO JRGJ Testar quando a entidade possuir mais de um ID.
		return (PK) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
	}
}