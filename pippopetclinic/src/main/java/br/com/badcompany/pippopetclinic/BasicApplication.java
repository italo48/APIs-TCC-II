package br.com.badcompany.pippopetclinic;


import br.com.badcompany.pippopetclinic.owner.OwnerController;
import br.com.badcompany.pippopetclinic.owner.OwnerRepository;
import br.com.badcompany.pippopetclinic.owner.PetController;
import br.com.badcompany.pippopetclinic.owner.PetRepository;
import br.com.badcompany.pippopetclinic.owner.PetValidator;
import br.com.badcompany.pippopetclinic.system.ErrorController;
import br.com.badcompany.pippopetclinic.system.WelcomeController;
import br.com.badcompany.pippopetclinic.util.GsonCustomContentTypeEngine;
import br.com.badcompany.pippopetclinic.util.JPAUtils;
import br.com.badcompany.pippopetclinic.vet.VetController;
import br.com.badcompany.pippopetclinic.vet.VetRepository;
import br.com.badcompany.pippopetclinic.visit.VisitController;
import br.com.badcompany.pippopetclinic.visit.VisitRepository;
import ro.pippo.controller.ControllerApplication;

public class BasicApplication extends ControllerApplication {
	private OwnerRepository ownerRepo;
	private PetRepository petRepo;
	private VisitRepository visitRepo;
	private VetRepository vetRepo;
	
	@Override
	protected void onInit() {
		JPAUtils.initEmf();
		ownerRepo = new OwnerRepository();
		petRepo = new PetRepository(ownerRepo);
		visitRepo = new VisitRepository(petRepo);
		vetRepo = new VetRepository();
		
		registerContentTypeEngine(GsonCustomContentTypeEngine.class);
		
		ANY("/pet/save/.*", new PetValidator());
		ANY("/pet/edit/.*", new PetValidator());

		setNotFoundRouteHandler(new ErrorController());
		
		addControllers(new OwnerController(ownerRepo));
		addControllers(new WelcomeController());
		addControllers(new PetController(petRepo));
		addControllers(new VetController(vetRepo));
		addControllers(new VisitController(visitRepo));
	}
}
