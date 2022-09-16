package dao;

import java.util.List;

import org.junit.Assert;

import entity.Department;

public class DepartmentDAOTest extends AbstractSupportTest<Department, Long> {

	@Override
	protected IGenericDAO<Department, Long> getDAO() {
		DepartmentDAO dao = new DepartmentDAO();
		dao.setEntityManager(em);
		return dao;
	}

	@Override
	protected Department arrangeInsert() {
		Department department = new Department();
		department.setDepartmentName("Department test");
		return department;
	}

	@Override
	protected void assertInsert(Department entity) {
		Assert.assertNotNull(entity.getDepartmentId());
	}

	@Override
	protected Department arrangeUpdate() {
		Department department = getDAO().loadById(10L);
		department.setDepartmentName("Updated");
		return department;
	}

	@Override
	protected void assertUpdate(Department entity) {
		Department department = getDAO().loadById(10L);
		Assert.assertTrue(department.getDepartmentName().contains("Updated"));
	}

	@Override
	protected Department arrangeDelete() {
		Department department = new Department();
		department.setDepartmentName("Department test");
		getDAO().insert(department);
		return department;
	}

	@Override
	protected void assertDelete(Department entity) {
		Department department = getDAO().load(entity);
		Assert.assertNull(department);
	}

	@Override
	protected Department arrangeLoad() {
		Department department = new Department();
		department.setDepartmentName("Department test");
		getDAO().insert(department);
		return department;
	}

	@Override
	protected void assertLoad(Department entity) {
		Department department = getDAO().load(entity);
		Assert.assertEquals(department.getDepartmentName(), entity.getDepartmentName());
	}

	@Override
	protected void arrangeLoadAll() {
		// See file /hsqldb-jpa/src/main/java/META-INF/sql/unit-test/import.sql
	}

	@Override
	protected void assertLoadAll(List<Department> entity) {
		Assert.assertTrue(!entity.isEmpty());
	}

}
