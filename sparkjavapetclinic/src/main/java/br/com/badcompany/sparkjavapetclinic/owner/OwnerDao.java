package br.com.badcompany.sparkjavapetclinic.owner;

import static br.com.badcompany.sparkjavapetclinic.App.entityManagerFactory;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager; 
public class OwnerDao {
	private EntityManager entityManager;

	public void saveOwner(Owner owner) {
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(owner);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public List<Owner> getAllOwners() {
		List<Owner> owners = new ArrayList<>();
		int id = 1;
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		while (entityManager.find(Owner.class, id) != null) {
			owners.add(entityManager.find(Owner.class, id));
			id++;
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		return owners;
	}

	public Owner findOneOwner(String name) {
		return getAllOwners().stream().filter(owner -> owner.getLastName().equals(name))
				.findFirst().orElse(null);
	}
}
