package br.com.badcompany.sparkjavapetclinic.owner;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OwnerDao {
	List<Owner> owners = new ArrayList<>();
//	private Session sess;
	public void saveOwner(Owner owner) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Owners-PU");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(owner);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	public List<Owner> getOwners() {
		return this.owners;
	}
}
