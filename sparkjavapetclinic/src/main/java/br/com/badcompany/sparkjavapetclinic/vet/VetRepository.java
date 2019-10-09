package br.com.badcompany.sparkjavapetclinic.vet;

import static br.com.badcompany.sparkjavapetclinic.App.entityManagerFactory;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class VetRepository {
	private EntityManager entityManager;
	
	public List<Vet> getAllVets() {
		List<Vet> vets = new ArrayList<>();
		int id = 1;
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		while (entityManager.find(Vet.class, id) != null) {
			vets.add(entityManager.find(Vet.class, id));
			id++;
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		return vets;
	}
}
