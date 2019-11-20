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
package br.com.badcompany.joobypetclinic.visit;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.badcompany.joobypetclinic.owner.Pet;
import br.com.badcompany.joobypetclinic.owner.PetRepository;
import br.com.badcompany.joobypetclinic.system.GenericException;
import br.com.badcompany.joobypetclinic.util.JPAUtils;

public class VisitRepository {
	private EntityManager entityManager;
	private PetRepository petRepo;

	public VisitRepository(PetRepository petRepo) {
		super();
		this.petRepo = petRepo;
	}

	public Visit saveVisit(Visit visit) throws GenericException {
		Pet p = petRepo.findPetById(visit.getPetId());
		if (p != null) {
			p.addVisit(visit);
			entityManager = JPAUtils.getEntityManager();
			try {
				JPAUtils.beginTransaction();
				entityManager.persist(visit);
				JPAUtils.commit();
			} catch (Exception e) {
				JPAUtils.rollback();
				e.printStackTrace();
			} finally {
				JPAUtils.closeEntityManager();
			}
			return visit;
		}
		throw new GenericException("Pet not found");
	}

	public List<Visit> getAllVisits(int idPet) throws GenericException {
		if (petRepo.existsPetById(idPet)) {			
			entityManager = JPAUtils.getEntityManager();
			List<Visit> visits = new ArrayList<>();
			for (Visit v : entityManager.createQuery("from Visit", Visit.class).getResultList()) {
				if (v.getPetId() == idPet) {
					visits.add(v);
				}
			}
			JPAUtils.closeEntityManager();
			return visits;
		}
		throw new GenericException("Pet not found");
	}
}
