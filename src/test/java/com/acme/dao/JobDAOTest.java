package com.acme.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;

import com.acme.entity.Job;

public class JobDAOTest extends AbstractSupportTest<Job, String> {

	@Override
	protected IGenericDAO<Job, String> getDAO() {
		var dao = new JobDAO();
		dao.setEntityManager(em);
		return dao;
	}

	@Override
	protected Job arrangeInsert() {
		var job = new Job();
		job.setJobId("TEST_JUNIT");
		job.setJobTitle("This is a test");
		job.setMaxSalary(new BigDecimal(100000.0));
		job.setMaxSalary(new BigDecimal(10000.0));
		return job;
	}

	@Override
	protected void assertInsert(Job entity) {
		var job = getDAO().load(entity);
		Assert.assertNotNull(job);
	}

	@Override
	protected Job arrangeUpdate() {
		var job = getDAO().loadById("AC_ACCOUNT");
		job.setJobTitle("This is a test");
		return job;
	}

	@Override
	protected void assertUpdate(Job entity) {
		var job = getDAO().loadById("AC_ACCOUNT");
		Assert.assertTrue(job.getJobTitle().contains("This is a test"));
	}

	@Override
	protected Job arrangeDelete() {
		var job = new Job();
		job.setJobId("TEST_JUNIT_DELETE");
		job.setJobTitle("This is a test");
		job.setMaxSalary(new BigDecimal(100000.0));
		job.setMaxSalary(new BigDecimal(10000.0));
		getDAO().insert(job);
		return job;
	}

	@Override
	protected void assertDelete(Job entity) {
		var job = getDAO().load(entity);
		Assert.assertNull(job);
	}

	@Override
	protected Job arrangeLoad() {
		var job = new Job();
		job.setJobId("TEST_JUNIT_LOAD");
		job.setJobTitle("This is a test");
		job.setMaxSalary(new BigDecimal(100000.0));
		job.setMaxSalary(new BigDecimal(10000.0));
		getDAO().insert(job);
		return job;
	}

	@Override
	protected void assertLoad(Job entity) {
		var job = getDAO().load(entity);
		Assert.assertEquals(entity, job);
	}

	@Override
	protected void arrangeLoadAll() {
		// See file /hsqldb-jpa/src/main/java/META-INF/sql/unit-test/import.sql
	}

	@Override
	protected void assertLoadAll(List<Job> entity) {
		Assert.assertTrue(!entity.isEmpty());
	}

}
