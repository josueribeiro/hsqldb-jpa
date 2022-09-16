package dao;

import java.util.List;

import org.junit.Assert;

import entity.Region;

public class RegionTest extends AbstractSupportTest<Region, Integer> {

	@Override
	protected IGenericDAO<Region, Integer> getDAO() {
		RegionDAO dao = new RegionDAO();
		dao.setEntityManager(em);
		return dao;
	}

	@Override
	protected Region arrangeInsert() {
		Region region = new Region();
		region.setRegionName("Region Test");
		return region;
	}

	@Override
	protected void assertInsert(Region entity) {
		Assert.assertNotNull(entity.getRegionId());
	}

	@Override
	protected Region arrangeUpdate() {
		Region regionEurope = getDAO().loadById(1);
		regionEurope.setRegionName("Updated");
		return regionEurope;
	}

	@Override
	protected void assertUpdate(Region entity) {
		Region regionEuropeUpdated = getDAO().loadById(1);
		Assert.assertTrue(regionEuropeUpdated.getRegionName().contains("Updated"));
	}

	@Override
	protected Region arrangeDelete() {
		Region region = new Region();
		region.setRegionName("Fake");
		getDAO().insert(region);
		return region;
	}

	@Override
	protected void assertDelete(Region entity) {
		Region region = getDAO().load(entity);
		Assert.assertNull(region);
	}

	@Override
	protected Region arrangeLoad() {
		Region region = new Region();
		region.setRegionName("Fake");
		getDAO().insert(region);
		return region;
	}

	@Override
	protected void assertLoad(Region entity) {
		Region region = getDAO().load(entity);
		Assert.assertEquals(entity, region);
	}

	@Override
	protected void arrangeLoadAll() {
		// See file /hsqldb-jpa/src/main/java/META-INF/sql/unit-test/import.sql
	}

	@Override
	protected void assertLoadAll(List<Region> entity) {
		Assert.assertTrue(!entity.isEmpty());
	}

}
