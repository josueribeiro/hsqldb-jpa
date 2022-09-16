package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

/**
 * The persistent class for the REGIONS database table.
 * 
 * @author Josue Ribeiro
 */
@Entity
@Table(name = "REGIONS")
public class Region implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "table_gen_region", table = "table_keys", pkColumnName = "table_name", valueColumnName = "cod_key", pkColumnValue = "REGIONS", allocationSize = 1)
	@GeneratedValue(generator = "table_gen_region", strategy = GenerationType.TABLE)
	@Column(name = "REGION_ID")
	private Integer regionId;

	@Column(name = "REGION_NAME")
	private String regionName;

	@OneToMany(mappedBy = "region")
	private List<Country> countries = new ArrayList<>();

	public Region() {
	}

	public Integer getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<Country> getCountries() {
		return this.countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public Country addCountry(Country country) {
		getCountries().add(country);
		country.setRegion(this);
		return country;
	}

	public Country removeCountry(Country country) {
		getCountries().remove(country);
		country.setRegion(null);
		return country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(regionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Region other = (Region) obj;
		return Objects.equals(regionId, other.regionId);
	}

}