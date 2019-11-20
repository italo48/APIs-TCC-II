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
package br.com.badcompany.joobypetclinic.visit;

import static br.com.badcompany.joobypetclinic.JoobyPetclinicApp.visitRepo;

import java.util.Collections;
import java.util.List;

import org.jooby.mvc.Body;
import org.jooby.mvc.Consumes;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;
import org.jooby.mvc.Produces;

import br.com.badcompany.joobypetclinic.system.GenericException;

@Path("/visit")
@Produces("application/json")
@Consumes("application/json")
public class VisitController {

	@POST
	@Path("/save")
	public Visit addVisitEndPoint(@Body Visit visit) {
		try {
			visitRepo.saveVisit(visit);
		} catch (GenericException e) {
			e.printStackTrace();
		}
		return visit;
	};

	@GET
	@Path("/all/:id")
	public List<Visit> listVisitEndPoint(int id) {
		try {
			return visitRepo.getAllVisits(id);
		} catch (GenericException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	};
}
