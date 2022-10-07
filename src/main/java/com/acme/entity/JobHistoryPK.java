package com.acme.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * The primary key class for the JOB_HISTORY database table.
 * 
 * @author Josue Ribeiro
 */
@Embeddable
public class JobHistoryPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "EMPLOYEE_ID", insertable = true, updatable = false)
	private Long employeeId;

	@Column(name = "START_DATE")
	private LocalDate startDate;

	public JobHistoryPK() {
	}

	public Long getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeId, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof JobHistoryPK other) {
			return Objects.equals(employeeId, other.employeeId) && Objects.equals(startDate, other.startDate);
		}
		return false;
	}

}