/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package br.com.badcompany.joobypetclinic.owner;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.badcompany.joobypetclinic.system.GenericException;
import br.com.badcompany.joobypetclinic.util.JPAUtils;

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
}
