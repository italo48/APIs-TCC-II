package br.com.badcompany.sparkjavapetclinic.owner;

import static br.com.badcompany.sparkjavapetclinic.App.ownerRepo;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.badcompany.sparkjavapetclinic.system.GenericException;
import br.com.badcompany.sparkjavapetclinic.util.JPAUtils;

public class PetRepository {
	private EntityManager entityManager;

	public Pet savePet(int id, int typeId, Pet pet) throws GenericException {
		Owner o = ownerRepo.findOwnerById(id);
		PetType pt = findPetTypeById(typeId);
		if (o != null && pt != null) {
			pet.setOwner(o);
			pet.setType(pt);
			entityManager = JPAUtils.getEntityManager();
			try {
				JPAUtils.beginTransaction();
				entityManager.persist(pet);
				JPAUtils.commit();
			} catch (Exception e) {
				JPAUtils.rollback();
				e.printStackTrace();
			} finally {
				JPAUtils.closeEntityManager();
			}
			return pet;
		}
		throw new GenericException("Pet type not found");
	}

	public List<Pet> getAllPets() {
		entityManager = JPAUtils.getEntityManager();
		List<Pet> pets;
		pets = entityManager.createQuery("from Pet", Pet.class).getResultList();
		JPAUtils.closeEntityManager();
		return pets;
	}

	public Pet updatePet(Pet pet) {
		entityManager = JPAUtils.getEntityManager();
		System.out.println(entityManager.hashCode());
		try {
			JPAUtils.beginTransaction();
			entityManager.merge(pet);
			JPAUtils.commit();
		} catch (Exception e) {
			JPAUtils.rollback();
			e.printStackTrace();
		} finally {
			JPAUtils.closeEntityManager();
		}
		return pet;
	}

	public List<PetType> getAllPetTypes() {
		entityManager = JPAUtils.getEntityManager();
		List<PetType> petsTypes;
		petsTypes = entityManager.createQuery("from PetType", PetType.class).getResultList();
		JPAUtils.closeEntityManager();
		return petsTypes;
	}

	public boolean existsTypeById(int id) {
		for (PetType pte : getAllPetTypes()) {
			if (pte.getId() == id)
				return true;
		}
		return false;
	}

	public Pet findPetById(int id) throws GenericException {
		for (Pet p : getAllPets()) {
			if (p.getId() == id)
				return p;
		}
		throw new GenericException("Pet not found");
	}
	
	public PetType findPetTypeById(int id) throws GenericException {
		for (PetType p : getAllPetTypes()) {
			if (p.getId() == id)
				return p;
		}
		throw new GenericException("Pet Type not found");
	}
}
