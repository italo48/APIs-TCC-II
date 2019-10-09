package br.com.badcompany.sparkjavapetclinic.owner;

import static br.com.badcompany.sparkjavapetclinic.App.entityManagerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

public class OwnerRepository {
	private EntityManager entityManager;

	public void saveOwner(Owner owner) {
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(owner);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Collection<Owner> getAllOwners() {

//		Mudar
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

	public List<Owner> findOwnerByName(String name) {

//		Mudar
		List<Owner> owners = new ArrayList<>();
		for (Owner o : getAllOwners()) {
			if (o.getLastName().equals(name)) {
				owners.add(o);
			}
		}
		return owners;
	}

	public int getSizeOwners() {
		return getAllOwners().size();
	}
}
