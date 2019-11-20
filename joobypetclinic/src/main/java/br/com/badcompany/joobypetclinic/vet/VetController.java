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
package br.com.badcompany.joobypetclinic.vet;

import static br.com.badcompany.joobypetclinic.JoobyPetclinicApp.vetRepo;

import java.util.List;

import org.jooby.mvc.Consumes;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;
import org.jooby.mvc.Produces;


@Path("/vet")
@Produces("application/json")
@Consumes("application/json")
public class VetController {
	@GET
	@Path("/all")
	public List<Vet> getAllVetsEndPoint() {
		return vetRepo.getAllVets();
	};
}
