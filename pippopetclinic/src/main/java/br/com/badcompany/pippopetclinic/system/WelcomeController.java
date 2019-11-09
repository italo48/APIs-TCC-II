package br.com.badcompany.pippopetclinic.system;

import br.com.badcompany.pippopetclinic.util.MessageJson;
import ro.pippo.controller.Controller;
import ro.pippo.controller.GET;
import ro.pippo.controller.Path;
import ro.pippo.controller.Produces;

@Path("/")
public class WelcomeController extends Controller {
	@GET
	@Produces(Produces.JSON)
	public MessageJson Hello() {
		return new MessageJson("HELL NOOOOOO!!");
	}
}
