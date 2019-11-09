package br.com.badcompany.pippopetclinic.visit;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.badcompany.pippopetclinic.owner.Pet;
import br.com.badcompany.pippopetclinic.owner.PetRepository;
import br.com.badcompany.pippopetclinic.system.GenericException;
import br.com.badcompany.pippopetclinic.util.JPAUtils;

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
