package br.com.badcompany.sparkjavapetclinic.owner;

import static br.com.badcompany.sparkjavapetclinic.SparkJavaPetclinicApp.ownerRepo;
import static br.com.badcompany.sparkjavapetclinic.SparkJavaPetclinicApp.petRepo;

//import static br.com.badcompany.sparkjavapetclinic.SparkJavaPetclinicApp.ownerRepo;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.badcompany.sparkjavapetclinic.system.GenericException;
import br.com.badcompany.sparkjavapetclinic.util.JPAUtils;

public class PetRepository {
	private EntityManager entityManager;

	public Pet savePet(int idOwner, int idType, Pet pet) throws GenericException {
		pet.setType(petRepo.findPetTypeById(idType));
		Owner o = ownerRepo.findOwnerById(idOwner);
		o.addPet(pet);
		if (pet.getOwner() != null && pet.getType() != null) {
			entityManager = JPAUtils.getEntityManager();
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
		throw new GenericException("Something wrong happened to save the pet");
	}

	public List<Pet> getAllPets() {
		entityManager = JPAUtils.getEntityManager();
		List<Pet> pets;
		pets = entityManager.createQuery("FROM Pet", Pet.class).getResultList();
//		JPAUtils.closeEntityManager();
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
		petsTypes = entityManager.createQuery("FROM PetType", PetType.class).getResultList();
//		JPAUtils.closeEntityManager();
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
