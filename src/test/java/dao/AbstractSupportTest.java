package dao;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class AbstractSupportTest<T, PK> {

	private static EntityManagerFactory emf;
	protected EntityManager em;

	@BeforeClass
	public static void setUp() {
		emf = Persistence.createEntityManagerFactory("pu-hsqldb-jpa");
	}

	@Before
	public void beforeTest() {
		em = emf.createEntityManager();
	}

	@Test
	public void testInsert() {
		// Arrange
		T entity = arrangeInsert();

		// Act
		getDAO().getEntityManager().getTransaction().begin();
		getDAO().insert(entity);
		getDAO().getEntityManager().getTransaction().commit();

		// Assert
		assertInsert(entity);
	}

	@Test
	public void testUpdate() {
		// Arrange
		T entity = arrangeUpdate();

		// Act
		getDAO().getEntityManager().getTransaction().begin();
		getDAO().update(entity);
		getDAO().getEntityManager().getTransaction().commit();

		// Assert
		assertUpdate(entity);
	}

	@Test
	public void testDelete() {
		// Arrange
		T entity = arrangeDelete();

		// Act
		getDAO().getEntityManager().getTransaction().begin();
		getDAO().delete(entity);
		getDAO().getEntityManager().getTransaction().commit();

		// Assert
		assertDelete(entity);
	}

	@Test
	public void testLoad() {
		// Arrange
		T entity = arrangeLoad();

		// Act
		T loadEntity = getDAO().load(entity);

		// Assert
		assertLoad(loadEntity);
	}

	@Test
	public void testLoadById() {
		// Arrange
		T entity = arrangeLoad();
		PK idEntity = getDAO().getIdEntity(entity);

		// Act
		T loadEntity = getDAO().loadById(idEntity);

		// Assert
		assertLoad(loadEntity);
	}

	@Test
	public void testLoadAll() {
		// Arrange
		arrangeLoadAll();

		// Act
		List<T> entities = getDAO().loadAll();

		// Assert
		assertLoadAll(entities);
	}

	protected abstract IGenericDAO<T, PK> getDAO();

	protected abstract T arrangeInsert();

	protected abstract void assertInsert(T entity);

	protected abstract T arrangeUpdate();

	protected abstract void assertUpdate(T entity);

	protected abstract T arrangeDelete();

	protected abstract void assertDelete(T entity);

	protected abstract T arrangeLoad();

	protected abstract void assertLoad(T entity);

	protected abstract void arrangeLoadAll();

	protected abstract void assertLoadAll(List<T> entity);

	@After
	public void afterTest() {
		em.close();
	}

	@AfterClass
	public static void shutdown() {
		emf.close();
	}

}
