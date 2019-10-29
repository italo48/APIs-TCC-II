package br.com.badcompany.sparkjavapetclinic.owner;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.badcompany.sparkjavapetclinic.system.GenericException;
import br.com.badcompany.sparkjavapetclinic.util.JPAUtils;

public class OwnerRepository {
	private EntityManager entityManager;

	public Owner saveOwner(Owner owner) throws GenericException {
		if (owner.getTelephone().length() <= 10) {
			entityManager = JPAUtils.getEntityManager();
			try {
				JPAUtils.beginTransaction();
				entityManager.merge(owner);
				JPAUtils.commit();
			} catch (Exception e) {
				JPAUtils.rollback();
				e.printStackTrace();
			} finally {
				JPAUtils.closeEntityManager();
			}
			return owner;
		}
		throw new GenericException("Something wrong happened to save the owner");
	}

	public List<Owner> getAllOwners() {
		List<Owner> owners;
		entityManager = JPAUtils.getEntityManager();
		owners = entityManager.createQuery("FROM Owner", Owner.class).getResultList();
//		JPAUtils.closeEntityManager();
		return owners;
	}

	public List<Owner> findOwnerByName(String name) {
//      Pode-se ser criado um service para tal, porem n acho necessario
//		Mudar, pq ta feio
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

	public Owner findOwnerById(int id) throws GenericException {
		for (Owner o : getAllOwners()) {
			if (o.getId() == id) {
				return o;
			}
		}
		throw new GenericException("Owner not found");
	}

	public boolean existsById(int id) {
		for (Owner o : getAllOwners()) {
			if (o.getId() == id) {
				return true;
			}
		}
		return false;
	}

	public Owner updateOwner(Owner owner) {
		entityManager = JPAUtils.getEntityManager();
		try {
			JPAUtils.beginTransaction();
			entityManager.merge(owner);
			JPAUtils.commit();
		} catch (Exception e) {
			JPAUtils.rollback();
			e.printStackTrace();
		} finally {
			JPAUtils.closeEntityManager();
		}
		return owner;
	}
}
