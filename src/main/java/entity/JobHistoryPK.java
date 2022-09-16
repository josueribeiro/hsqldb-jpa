package entity;

import java.io.Serializable;
import java.time.LocalDate;

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
	private long employeeId;

//	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	private LocalDate startDate;

	public JobHistoryPK() {
	}

	public long getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JobHistoryPK)) {
			return false;
		}
		JobHistoryPK castOther = (JobHistoryPK) other;
		return (this.employeeId == castOther.employeeId) && this.startDate.equals(castOther.startDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.employeeId ^ (this.employeeId >>> 32)));
		hash = hash * prime + this.startDate.hashCode();
		return hash;
	}
}