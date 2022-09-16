package dao;

import java.util.List;

import org.junit.Assert;

import entity.Country;
import entity.Region;

public class CountryDAOTest extends AbstractSupportTest<Country, String> {

	@Override
	protected IGenericDAO<Country, String> getDAO() {
		CountryDAO dao = new CountryDAO();
		dao.setEntityManager(em);
		return dao;
	}

	@Override
	protected Country arrangeInsert() {
		Country country = new Country();
		country.setCountryId("JL");
		country.setCountryName("Java Land");
		country.setRegion(getRegion());
		return country;
	}

	private Region getRegion() {
		RegionDAO regionDAO = new RegionDAO();
		regionDAO.setEntityManager(em);
		return regionDAO.loadById(2);
	}

	@Override
	protected void assertInsert(Country entity) {
		Assert.assertNotNull(entity.getCountryName());
	}

	@Override
	protected Country arrangeUpdate() {
		Country country = getDAO().loadById("BR");
		country.setCountryName("Brazil Updated");
		return country;
	}

	@Override
	protected void assertUpdate(Country entity) {
		Country country = getDAO().loadById("BR");
		Assert.assertTrue(country.getCountryName().contains("Updated"));
	}

	@Override
	protected Country arrangeDelete() {
		Country country = new Country();
		country.setCountryId("RM");
		country.setCountryName("To Remove");
		country.setRegion(getRegion());
		getDAO().insert(country);
		return country;
	}

	@Override
	protected void assertDelete(Country entity) {
		Country country = getDAO().load(entity);
		Assert.assertNull(country);
	}

	@Override
	protected Country arrangeLoad() {
		Country country = new Country();
		country.setCountryId("LD");
		country.setCountryName("Test Load");
		country.setRegion(getRegion());
		getDAO().insert(country);
		return country;
	}

	@Override
	protected void assertLoad(Country entity) {
		Country country = getDAO().load(entity);
		Assert.assertEquals(country.getCountryName(), entity.getCountryName());
	}

	@Override
	protected void arrangeLoadAll() {
		// See file /hsqldb-jpa/src/main/java/META-INF/sql/unit-test/import.sql
	}

	@Override
	protected void assertLoadAll(List<Country> entity) {
		Assert.assertTrue(!entity.isEmpty());
	}

}
