package com.acme.dao;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;

import com.acme.entity.Department;
import com.acme.entity.Job;
import com.acme.entity.JobHistory;
import com.acme.entity.JobHistoryPK;

public class JobHistoryDAOTest extends AbstractSupportTest<JobHistory, JobHistoryPK> {

	@Override
	protected IGenericDAO<JobHistory, JobHistoryPK> getDAO() {
		var dao = new JobHistoryDAO();
		dao.setEntityManager(em);
		return dao;
	}

	@Override
	protected JobHistory arrangeInsert() {
		var jobHistory = new JobHistory();
		
		var id = new JobHistoryPK();
		id.setEmployeeId(112L);
		id.setStartDate(LocalDate.now());
		jobHistory.setId(id);
		
		jobHistory.setEndDate(LocalDate.now());
		jobHistory.setJob(getJob());
		jobHistory.setDepartment(getDepartment());
		return jobHistory;
	}
	
	private Job getJob() {
		var dao = new JobDAO();
		dao.setEntityManager(em);
		return dao.loadById("AC_ACCOUNT");
	}
	
	private Department getDepartment() {
		var dao = new DepartmentDAO();
		dao.setEntityManager(em);
		return dao.loadById(10L);
	}

	@Override
	protected void assertInsert(JobHistory entity) {
		var pk = new JobHistoryPK();
		pk.setEmployeeId(112L);
		pk.setStartDate(LocalDate.now());
		var job = getDAO().loadById(pk);
		Assert.assertNotNull(job);
	}

	@Override
	protected JobHistory arrangeUpdate() {
		var pk = new JobHistoryPK();
		pk.setEmployeeId(200L);
		pk.setStartDate(LocalDate.of(1995, 9, 17));
		var job = getDAO().loadById(pk);
		job.setEndDate(LocalDate.of(2020, 1, 1));
		return job;
	}

	@Override
	protected void assertUpdate(JobHistory entity) {
		var pk = new JobHistoryPK();
		pk.setEmployeeId(200L);
		pk.setStartDate(LocalDate.of(1995, 9, 17));
		var job = getDAO().loadById(pk);
		Assert.assertEquals(job.getEndDate(), entity.getEndDate());
	}

	@Override
	protected JobHistory arrangeDelete() {
		var pk = new JobHistoryPK();
		pk.setEmployeeId(201L);
		pk.setStartDate(LocalDate.of(2004, 2, 17));
		var job = getDAO().loadById(pk);
		return job;
	}

	@Override
	protected void assertDelete(JobHistory entity) {
		var job = getDAO().load(entity);
		Assert.assertNull(job);
	}

	@Override
	protected JobHistory arrangeLoad() {
		var pk = new JobHistoryPK();
		pk.setEmployeeId(176L);
		pk.setStartDate(LocalDate.of(2006, 3, 24));
		var job = new JobHistory();
		job.setId(pk);
		return job;
	}

	@Override
	protected void assertLoad(JobHistory entity) {
		var jobHistory = getDAO().load(entity);
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
