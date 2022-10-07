package com.acme.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the EMPLOYEES database table.
 * 
 * @author Josue Ribeiro
 */
@Entity
@Table(name = "EMPLOYEES")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "table_gen_country", table = "table_keys", pkColumnName = "table_name", valueColumnName = "cod_key", pkColumnValue = "EMPLOYEES", allocationSize = 1)
	@GeneratedValue(generator = "table_gen_country", strategy = GenerationType.TABLE)
	@Column(name = "EMPLOYEE_ID")
	private Long employeeId;

	@Column(name = "COMMISSION_PCT")
	private BigDecimal commissionPct;

	private String email;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Temporal(TemporalType.DATE)
	@Column(name = "HIRE_DATE")
	private Date hireDate;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	private BigDecimal salary;

	@OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
	private List<Department> departments;

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "MANAGER_ID")
	private Employee manager;

	@OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
	private List<Employee> employees;

	@ManyToOne
	@JoinColumn(name = "JOB_ID")
	private Job job;

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
	private List<JobHistory> jobHistories;

	public Employee() {
	}

	public Long getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public BigDecimal getCommissionPct() {
		return this.commissionPct;
	}

	public void setCommissionPct(BigDecimal commissionPct) {
		this.commissionPct = commissionPct;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Department addDepartment(Department department) {
		getDepartments().add(department);
		department.setManager(this);
		return department;
	}

	public Department removeDepartment(Department department) {
		getDepartments().remove(department);
		department.setManager(null);

		return department;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setManager(this);
		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setManager(null);
		return employee;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public List<JobHistory> getJobHistories() {
		return this.jobHistories;
	}

	public void setJobHistories(List<JobHistory> jobHistories) {
		this.jobHistories = jobHistories;
	}

	public JobHistory addJobHistory(JobHistory jobHistory) {
		getJobHistories().add(jobHistory);
		jobHistory.setEmployee(this);

		return jobHistory;
	}

	public JobHistory removeJobHistory(JobHistory jobHistory) {
		getJobHistories().remove(jobHistory);
		jobHistory.setEmployee(null);

		return jobHistory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Employee other) {
			return Objects.equals(employeeId, other.employeeId);
		}
		return false;
	}

}