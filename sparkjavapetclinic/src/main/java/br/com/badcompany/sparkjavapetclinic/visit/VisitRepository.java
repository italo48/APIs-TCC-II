package br.com.badcompany.sparkjavapetclinic.visit;

import static br.com.badcompany.sparkjavapetclinic.SparkJavaPetclinicApp.petRepo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.badcompany.sparkjavapetclinic.owner.Pet;
import br.com.badcompany.sparkjavapetclinic.system.GenericException;
import br.com.badcompany.sparkjavapetclinic.util.JPAUtils;

public class VisitRepository {
	private EntityManager entityManager;

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
