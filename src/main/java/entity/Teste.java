package entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Teste {

	public static void main(String[] args) {

		try {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-hsqldb-jpa");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			Region region = new Region();
			region.setRegionName("Region Test");
			em.persist(region);

			System.out.println("Region inserida com id " + region.getRegionId());

			em.getTransaction().commit();
			em.close();
			emf.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
