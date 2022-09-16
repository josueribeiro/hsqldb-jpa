package dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;

import entity.Department;
import entity.Employee;
import entity.Job;

public class EmployeeDAOTest extends AbstractSupportTest<Employee, Long> {

	@Override
	protected IGenericDAO<Employee, Long> getDAO() {
		EmployeeDAO dao = new EmployeeDAO();
		dao.setEntityManager(em);
		return dao;
	}

	@Override
	protected Employee arrangeInsert() {
		Employee employee = new Employee();
		employee.setFirstName("Junit");
		employee.setLastName("Test");
		employee.setEmail("junit@teste.com");
		employee.setPhoneNumber("123456789");
		employee.setHireDate(new Date());
		employee.setJob(getJob());
		employee.setSalary(new BigDecimal(9000.00));
		employee.setCommissionPct(new BigDecimal(10.00));
		employee.setManager(getDAO().loadById(100L));
		employee.setDepartment(getDepartment());
		return employee;
	}

	private Job getJob() {
		JobDAO dao = new JobDAO();
		dao.setEntityManager(em);
		return dao.loadById("AC_ACCOUNT");
	}
	
	private Department getDepartment() {
		DepartmentDAO dao = new DepartmentDAO();
		dao.setEntityManager(em);
		return dao.loadById(10L);
	}

	@Override
	protected void assertInsert(Employee entity) {
		Assert.assertNotNull(entity.getEmployeeId());
	}

	@Override
	protected Employee arrangeUpdate() {
		Employee employee = getDAO().loadById(101L);
		employee.setFirstName("Updated");
		return employee;
	}

	@Override
	protected void assertUpdate(Employee entity) {
		Employee employee = getDAO().loadById(101L);
		Assert.assertTrue(employee.getFirstName().equals("Updated"));
	}

	@Override
	protected Employee arrangeDelete() {
		Employee employee = getDAO().loadById(206L);
		return employee;
	}

	@Override
	protected void assertDelete(Employee entity) {
		Employee employee = getDAO().loadById(206L);
		Assert.assertNull(employee);
	}

	@Override
	protected Employee arrangeLoad() {
		Employee employee = new Employee();
		employee.setEmployeeId(200L);
		return employee;
	}

	@Override
	protected void assertLoad(Employee entity) {
		Assert.assertNotNull(entity.getFirstName());
	}

	@Override
	protected void arrangeLoadAll() {
		// See file /hsqldb-jpa/src/main/java/META-INF/sql/unit-test/import.sql
	}

	@Override
	protected void assertLoadAll(List<Employee> entity) {
		Assert.assertTrue(!entity.isEmpty());
	}

}
