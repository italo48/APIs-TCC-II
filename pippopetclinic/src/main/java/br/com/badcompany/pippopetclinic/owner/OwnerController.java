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

@Path("/owner")
public class OwnerController extends Controller{
	
	private OwnerRepository ownerRepo;
	
	public OwnerController(OwnerRepository ownerRepo) {
		super();
		this.ownerRepo = ownerRepo;
	}

	@GET("/all")
	@Produces(Produces.JSON)
	public List<Owner> getAllOwners() {
		if(ownerRepo.getAllOwners().size() == 0) {
			getResponse().status(404);
		} else {
			getResponse().status(200);
		}
		return ownerRepo.getAllOwners();
	}
	
	@POST("/save")
	@Produces(Produces.JSON)
	public Owner addOwner (@Body Owner o) {
		if(o != null) {
			getResponse().status(200);
			try {
				ownerRepo.saveOwner(o);
			} catch (GenericException e) {
				e.printStackTrace();
				getResponse().json(new MessageJson(e.getMessage()));
			}
			return o;
		}
		getResponse().status(500);
		return null;
	}
	
	@PUT("/edit")
	@Produces(Produces.JSON)
	public Owner editOwner (@Body Owner o) {
		if(o != null) {
			getResponse().status(200);
			try {
				ownerRepo.saveOwner(o);
			} catch (GenericException e) {
				e.printStackTrace();
				getResponse().json(new MessageJson(e.getMessage()));
			}
			return o;
		}
		getResponse().status(500);
		return null;
	}
	
	@GET("/find/{id: [0-9]+}")
	@Produces(Produces.JSON)
	public Owner findOwner(@Param("id") int id) {
		try {
			getResponse().status(200);
			return ownerRepo.findOwnerById(id);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			getResponse().status(500);
			return null;
		} catch (GenericException e) {
			e.printStackTrace();
			getResponse().json(new MessageJson(e.getMessage()));
		}
		return null;
	}
	
	@GET("/find/{name}")
	@Produces(Produces.JSON)
	public List<Owner> findOwner(@Param("name") String name) {
		try {
			getResponse().status(200);
			return ownerRepo.findOwnerByName(name);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			getResponse().status(500);
			return null;
		}
	}
}
