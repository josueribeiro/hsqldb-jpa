package com.acme.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The persistent class for the JOBS database table.
 * 
 * @author Josue Ribeiro
 */
@Entity
@Table(name = "JOBS")
public class Job implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "JOB_ID")
	private String jobId;

	@Column(name = "JOB_TITLE")
	private String jobTitle;

	@Column(name = "MAX_SALARY")
	private BigDecimal maxSalary;

	@Column(name = "MIN_SALARY")
	private BigDecimal minSalary;

	@OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
	private List<Employee> employees;

	@OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
	private List<JobHistory> jobHistories;

	public Job() {
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public BigDecimal getMaxSalary() {
		return this.maxSalary;
	}

	public void setMaxSalary(BigDecimal maxSalary) {
		this.maxSalary = maxSalary;
	}

	public BigDecimal getMinSalary() {
		return this.minSalary;
	}

	public void setMinSalary(BigDecimal minSalary) {
		this.minSalary = minSalary;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setJob(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setJob(null);

		return employee;
	}

	public List<JobHistory> getJobHistories() {
		return this.jobHistories;
	}

	public void setJobHistories(List<JobHistory> jobHistories) {
		this.jobHistories = jobHistories;
	}

	public JobHistory addJobHistory(JobHistory jobHistory) {
		getJobHistories().add(jobHistory);
		jobHistory.setJob(this);

		return jobHistory;
	}

	public JobHistory removeJobHistory(JobHistory jobHistory) {
		getJobHistories().remove(jobHistory);
		jobHistory.setJob(null);

		return jobHistory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(jobId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Job other) {
			return Objects.equals(jobId, other.jobId);
		}
		return false;
	}

}