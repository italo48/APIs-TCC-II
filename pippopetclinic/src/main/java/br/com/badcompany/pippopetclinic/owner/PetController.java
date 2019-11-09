package br.com.badcompany.pippopetclinic.owner;

import java.util.List;

import br.com.badcompany.pippopetclinic.system.GenericException;
import br.com.badcompany.pippopetclinic.util.MessageJson;
import ro.pippo.controller.Controller;
import ro.pippo.controller.GET;
import ro.pippo.controller.POST;
import ro.pippo.controller.PUT;
import ro.pippo.controller.Path;
import ro.pippo.controller.Produces;
import ro.pippo.controller.extractor.Body;
import ro.pippo.controller.extractor.Param;

@Path("/pet")
public class PetController extends Controller {
	private PetRepository petRepo;

	public PetController(PetRepository petRepo) {
		super();
		this.petRepo = petRepo;
	}
	
	@GET("/all")
	@Produces(Produces.JSON)
	public List<Pet> getAllPets() {
		getResponse().status(200);
		return petRepo.getAllPets();
	}
	
	@POST("/save/{id: [0-9]+}")
	@Produces(Produces.JSON)
	public Pet savePet(@Body Pet p, @Param("id") int id) {
		try {
			petRepo.savePet(id, p);
			getResponse().status(200);
		} catch (GenericException e) {
			e.printStackTrace();
			getResponse().status(404);			
			getResponse().json(new MessageJson(e.getMessage()));
		}
		return p;	
	}
	
	@PUT("/edit/{id: [0-9]+}")
	@Produces(Produces.JSON)
	public Pet editPet(@Body Pet p, @Param("id") int id) {
		try {
			petRepo.updatePet(id, p);
			getResponse().status(200);
		} catch (GenericException e) {
			e.printStackTrace();
			getResponse().status(404);			
			getResponse().json(new MessageJson(e.getMessage()));
		}
		return p;	
	}
	
	@GET("/all/types")
	@Produces(Produces.JSON)
	public List<PetType> getAllPetsTypes() {
		getResponse().status(200);
		return petRepo.getAllPetTypes();
	}
	
}
