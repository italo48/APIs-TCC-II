package br.com.badcompany.sparkjavapetclinic.vet;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.badcompany.sparkjavapetclinic.util.JPAUtils;

public class VetRepository {
	private EntityManager entityManager;
	private List<Vet> vets;
	
	public List<Vet> getAllVets() {
//		Deixar igual a do owner
		entityManager = JPAUtils.getEntityManager();
		entityManager.getTransaction().begin();
		vets = entityManager.createQuery("from Vet", Vet.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return vets;
	}
}
