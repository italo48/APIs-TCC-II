package br.com.badcompany.pippopetclinic.owner;

import java.util.ArrayList;
import java.util.List;

import br.com.badcompany.pippopetclinic.util.ErrorJson;
import ro.pippo.core.Response;
import ro.pippo.core.route.RouteContext;
import ro.pippo.core.route.RouteHandler;

public class PetValidator implements RouteHandler<RouteContext> {
	@Override
	public void handle(RouteContext routeContext) {
		Response res = routeContext.getResponse();
		List<ErrorJson> invalidList = new ArrayList<>();
		Pet p = routeContext.getRequest().createEntityFromBody(Pet.class);
		
		if (p.getName().isEmpty() || p.getName() == null)
			invalidList.add(new ErrorJson("Name cannot be empty or null"));
		if (p.isNew() && p.getType() == null)
			invalidList.add(new ErrorJson("Type cannot be null"));
		if (p.getBirthDate() == null)
			invalidList.add(new ErrorJson("Date of birth cannot be null"));

		if (!invalidList.isEmpty()) {
			res.status(500);
			res.json(invalidList);
		}
		routeContext.next();
	}
}
