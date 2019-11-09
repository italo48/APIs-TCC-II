package br.com.badcompany.pippopetclinic.system;

import br.com.badcompany.pippopetclinic.util.ErrorJson;
import ro.pippo.core.Response;
import ro.pippo.core.route.RouteContext;
import ro.pippo.core.route.RouteHandler;

public class ErrorController implements RouteHandler<RouteContext> {
	@Override
	public void handle(RouteContext routeContext) {
		Response res = routeContext.getResponse(); 
		res.status(418);
		res.json(new ErrorJson("The server refuses an attempt at thick coffee in the teapot."));
	}
}
