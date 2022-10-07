package com.acme.dao;

import java.util.List;

import org.junit.Assert;

import com.acme.entity.Country;
import com.acme.entity.Location;

public class LocationDAOTest extends AbstractSupportTest<Location, Long> {

	@Override
	protected IGenericDAO<Location, Long> getDAO() {
		var dao = new LocationDAO();
		dao.setEntityManager(em);
		return dao;
	}

	@Override
	protected Location arrangeInsert() {
		var location = new Location();
		location.setCity("City Test");
		location.setCountry(getCountry());
		location.setPostalCode("123456789");
		location.setStateProvince("PROVINCE");
		location.setStreetAddress("Street Address");
		return location;
	}

	private Country getCountry() {
		var dao = new CountryDAO();
		dao.setEntityManager(em);
		return dao.loadById("BR");
	}

	@Override
	protected void assertInsert(Location entity) {
		Assert.assertNotNull(entity.getLocationId());
	}

	@Override
	protected Location arrangeUpdate() {
		var location = getDAO().loadById(2800L);
		location.setStreetAddress("Street test");
		return location;
	}

	@Override
	protected void assertUpdate(Location entity) {
		var location = getDAO().loadById(2800L);
		Assert.assertTrue(location.getStreetAddress().contains("Street test"));
	}

	@Override
	protected Location arrangeDelete() {
		var location = new Location();
		location.setCity("City Test Fake");
		location.setCountry(getCountry());
		location.setPostalCode("123456789");
		location.setStateProvince("PROVINCE");
		location.setStreetAddress("Street Address");
		getDAO().insert(location);
		return location;
	}

	@Override
	protected void assertDelete(Location entity) {
		var location = getDAO().load(entity);
		Assert.assertNull(location);
	}

	@Override
	protected Location arrangeLoad() {
		var location = new Location();
		location.setCity("City Test to Load");
		location.setCountry(getCountry());
		location.setPostalCode("123456789");
		location.setStateProvince("PROVINCE");
		location.setStreetAddress("Street Address");
		getDAO().insert(location);
		return location;
	}

	@Override
	protected void assertLoad(Location entity) {
		var location = getDAO().load(entity);
		Assert.assertEquals(location.getCity(), entity.getCity());
	}

	@Override
	protected void arrangeLoadAll() {
		// See file /hsqldb-jpa/src/main/java/META-INF/sql/unit-test/import.sql
	}

	@Override
	protected void assertLoadAll(List<Location> entity) {
		Assert.assertTrue(!entity.isEmpty());
	}

}
