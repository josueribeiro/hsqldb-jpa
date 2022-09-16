package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

/**
 * The persistent class for the LOCATIONS database table.
 * 
 * @author Josue Ribeiro
 * 
 */
@Entity
@Table(name = "LOCATIONS")
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "table_gen_country", table = "table_keys", pkColumnName = "table_name", valueColumnName = "cod_key", pkColumnValue = "LOCATIONS", allocationSize = 1)
	@GeneratedValue(generator = "table_gen_country", strategy = GenerationType.TABLE)
	@Column(name = "LOCATION_ID")
	private Long locationId;

	private String city;

	@Column(name = "POSTAL_CODE")
	private String postalCode;

	@Column(name = "STATE_PROVINCE")
	private String stateProvince;

	@Column(name = "STREET_ADDRESS")
	private String streetAddress;

	// bi-directional many-to-one association to Department
	@OneToMany(mappedBy = "location")
	private List<Department> departments;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;

	public Location() {
	}

	public Long getLocationId() {
		return this.locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStateProvince() {
		return this.stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Department addDepartment(Department department) {
		getDepartments().add(department);
		department.setLocation(this);

		return department;
	}

	public Department removeDepartment(Department department) {
		getDepartments().remove(department);
		department.setLocation(null);

		return department;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(locationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(locationId, other.locationId);
	}

}