package br.com.badcompany.pippopetclinic.visit;

import java.util.Collections;
import java.util.List;

import br.com.badcompany.pippopetclinic.system.GenericException;
import br.com.badcompany.pippopetclinic.util.MessageJson;
import ro.pippo.controller.Controller;
import ro.pippo.controller.GET;
import ro.pippo.controller.POST;
import ro.pippo.controller.Path;
import ro.pippo.controller.Produces;
import ro.pippo.controller.extractor.Body;
import ro.pippo.controller.extractor.Param;

@Path("/visit")
public class VisitController extends Controller {
	
	private VisitRepository visitRepo;
	
	public VisitController(VisitRepository visitRepo) {
		super();
		this.visitRepo = visitRepo;
	}
	
	@POST("/save")
	@Produces(Produces.JSON)
	public Visit addVisitEndPoint(@Body Visit visit){
		try {
			visitRepo.saveVisit(visit);
			getResponse().status(200);
		} catch (GenericException e) {
			getResponse().status(404);
			getResponse().json(new MessageJson(e.getMessage()));
		}
		return visit;
	};
	
	@GET("/all/{id: [0-9]+}")
	@Produces(Produces.JSON)
	public List<Visit> listVisitEndPoint(@Param("id") int id) {
		try {
			getResponse().status(200);
			return visitRepo.getAllVisits(id);
		} catch (GenericException e) {
			e.printStackTrace();
			getResponse().status(404);
			getResponse().json(new MessageJson(e.getMessage())); 
		}
		return Collections.emptyList();
	};
}
