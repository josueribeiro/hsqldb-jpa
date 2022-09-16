package dao;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;

import entity.Department;
import entity.Job;
import entity.JobHistory;
import entity.JobHistoryPK;

public class JobHistoryDAOTest extends AbstractSupportTest<JobHistory, JobHistoryPK> {

	@Override
	protected IGenericDAO<JobHistory, JobHistoryPK> getDAO() {
		JobHistoryDAO dao = new JobHistoryDAO();
		dao.setEntityManager(em);
		return dao;
	}

	@Override
	protected JobHistory arrangeInsert() {
		JobHistory jobHistory = new JobHistory();
		
		JobHistoryPK id = new JobHistoryPK();
		id.setEmployeeId(112);
		id.setStartDate(LocalDate.now());
		jobHistory.setId(id);
		
		jobHistory.setEndDate(LocalDate.now());
		jobHistory.setJob(getJob());
		jobHistory.setDepartment(getDepartment());
		return jobHistory;
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
	protected void assertInsert(JobHistory entity) {
		JobHistoryPK pk = new JobHistoryPK();
		pk.setEmployeeId(112);
		pk.setStartDate(LocalDate.now());
		JobHistory job = getDAO().loadById(pk);
		Assert.assertNotNull(job);
	}

	@Override
	protected JobHistory arrangeUpdate() {
		JobHistoryPK pk = new JobHistoryPK();
		pk.setEmployeeId(200);
		pk.setStartDate(LocalDate.of(1995, 9, 17));
		JobHistory job = getDAO().loadById(pk);
		job.setEndDate(LocalDate.of(2020, 1, 1));
		return job;
	}

	@Override
	protected void assertUpdate(JobHistory entity) {
		JobHistoryPK pk = new JobHistoryPK();
		pk.setEmployeeId(200);
		pk.setStartDate(LocalDate.of(1995, 9, 17));
		JobHistory job = getDAO().loadById(pk);
		Assert.assertEquals(job.getEndDate(), entity.getEndDate());
	}

	@Override
	protected JobHistory arrangeDelete() {
		JobHistoryPK pk = new JobHistoryPK();
		pk.setEmployeeId(201);
		pk.setStartDate(LocalDate.of(2004, 2, 17));
		JobHistory job = getDAO().loadById(pk);
		return job;
	}

	@Override
	protected void assertDelete(JobHistory entity) {
		JobHistory job = getDAO().load(entity);
		Assert.assertNull(job);
	}

	@Override
	protected JobHistory arrangeLoad() {
		JobHistoryPK pk = new JobHistoryPK();
		pk.setEmployeeId(176);
		pk.setStartDate(LocalDate.of(2006, 3, 24));
		JobHistory job = new JobHistory();
		job.setId(pk);
		return job;
	}

	@Override
	protected void assertLoad(JobHistory entity) {
		JobHistory jobHistory = getDAO().load(entity);
		Assert.assertEquals(jobHistory.getEmployee(), entity.getEmployee());
	}
		

	@Override
	protected void arrangeLoadAll() {
		// See file /hsqldb-jpa/src/main/java/META-INF/sql/unit-test/import.sql
	}

	@Override
	protected void assertLoadAll(List<JobHistory> entity) {
		Assert.assertTrue(!entity.isEmpty());
	}

}
