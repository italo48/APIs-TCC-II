package br.com.badcompany.sparkjavapetclinic.visit;

import static br.com.badcompany.sparkjavapetclinic.SparkJavaPetclinicApp.petRepo;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.badcompany.sparkjavapetclinic.system.GenericException;
import br.com.badcompany.sparkjavapetclinic.util.JPAUtils;

public class VisitRepository {
	private EntityManager entityManager;

	public Visit saveVisit(Visit visit) throws GenericException {
		if (petRepo.findPetById(visit.getPetId()) != null) {			
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
		throw new GenericException("Something went wrong while saving the visit");
	}

	public List<Visit> getAllVisits() {
		entityManager = JPAUtils.getEntityManager();
		List<Visit> visits;
		visits = entityManager.createQuery("from Visit", Visit.class).getResultList();
		JPAUtils.closeEntityManager();
		return visits;
	}
}
