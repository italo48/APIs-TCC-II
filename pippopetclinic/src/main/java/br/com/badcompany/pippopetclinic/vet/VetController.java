package br.com.badcompany.pippopetclinic.vet;

import java.util.List;

import ro.pippo.controller.Controller;
import ro.pippo.controller.GET;
import ro.pippo.controller.Path;
import ro.pippo.controller.Produces;

@Path("/vet")
public class VetController extends Controller {
	
	private VetRepository vetRepo;
	
	public VetController(VetRepository vetRepo) {
		super();
		this.vetRepo = vetRepo;
	}

	@GET("/all")
	@Produces(Produces.JSON)
	public List<Vet> getAllVetsEndPoint() {
		return vetRepo.getAllVets();
	};
}
