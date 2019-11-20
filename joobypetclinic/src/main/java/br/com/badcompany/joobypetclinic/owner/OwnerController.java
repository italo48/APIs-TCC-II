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
package br.com.badcompany.joobypetclinic.owner;

import static br.com.badcompany.joobypetclinic.JoobyPetclinicApp.ownerRepo;

import java.util.List;

import org.jooby.Err;
import org.jooby.Status;
import org.jooby.mvc.Body;
import org.jooby.mvc.Consumes;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.PUT;
import org.jooby.mvc.Path;
import org.jooby.mvc.Produces;

import br.com.badcompany.joobypetclinic.system.GenericException;

@Path("/owner")
@Produces("application/json")
@Consumes("application/json")
public class OwnerController {

	@GET
	@Path("/all")
	public List<Owner> getAllOwners() {
		return ownerRepo.getAllOwners();
	}
	
	@POST
	@Path("/save")
	public Owner addOwner (@Body Owner o) {
		if(o != null) {
			try {
				ownerRepo.saveOwner(o);
			} catch (GenericException e) {
				e.printStackTrace();
				throw new Err(Status.BAD_REQUEST, e.getMessage());
			}
			return o;
		}
		throw new Err(Status.SERVER_ERROR, "Something wrong happened!!");
	}
	
	@PUT
	@Path("/edit")
	public Owner editOwner (@Body Owner o) {
		if(o != null) {
			try {
				ownerRepo.saveOwner(o);
			} catch (GenericException e) {
				e.printStackTrace();
				throw new Err(Status.BAD_REQUEST, e.getMessage());
			}
			return o;
		}
		throw new Err(Status.SERVER_ERROR, "Something wrong happened!!");
	}
	
	@GET()
	@Path("/find/:id")
	public Owner findOwner(int id) {
		try {
			return ownerRepo.findOwnerById(id);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			throw new Err(Status.BAD_REQUEST, e.getMessage());
		} catch (GenericException e) {
			e.printStackTrace();
			throw new Err(Status.SERVER_ERROR, "Something wrong happened!!");
		}
	}
	
	@GET
	@Path("/find/name/:name")
	public List<Owner> findOwner(String name) {
		try {
			return ownerRepo.findOwnerByName(name);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		throw new Err(Status.SERVER_ERROR, "Something wrong happened!!");
	}
}
