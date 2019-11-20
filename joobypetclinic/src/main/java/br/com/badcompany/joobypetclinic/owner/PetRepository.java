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


import java.util.List;

import javax.persistence.EntityManager;

import br.com.badcompany.joobypetclinic.system.GenericException;
import br.com.badcompany.joobypetclinic.util.JPAUtils;


public class PetRepository {
	private EntityManager entityManager;
	private OwnerRepository ownerRepo;
	
	public PetRepository( OwnerRepository ownerRepo) {
		this.ownerRepo = ownerRepo;
	}

	public Pet savePet(int idOwner, Pet pet) throws GenericException {
		pet.setType(findPetTypeById(pet.getType().getId()));
		Owner o = ownerRepo.findOwnerById(idOwner);
		if (o.getPet(pet.getName()) == null) {
			o.addPet(pet);
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
		throw new GenericException("Pet name alredy exists");
	}

	public Pet updatePet(int idOwner, Pet pet) throws GenericException {
		pet.setType(findPetTypeById(pet.getType().getId()));
		Owner o = ownerRepo.findOwnerById(idOwner);
		o.addPet(pet);
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

	public List<Pet> getAllPets() {
		entityManager = JPAUtils.getEntityManager();
		List<Pet> pets;
		pets = entityManager.createQuery("FROM Pet", Pet.class).getResultList();
//		JPAUtils.closeEntityManager();
		return pets;
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

	public boolean existsPetById(int idPet) {
		for (Pet p : getAllPets()) {
			if (p.getId() == idPet)
				return true;
		}
		return false;
	}

	public PetType findPetTypeById(int id) throws GenericException {
		for (PetType p : getAllPetTypes()) {
			if (p.getId() == id)
				return p;
		}
		throw new GenericException("Pet Type not found");
	}
}
