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

import static br.com.badcompany.joobypetclinic.JoobyPetclinicApp.petRepo;

import java.util.List;

import org.jooby.mvc.Body;
import org.jooby.mvc.Consumes;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.PUT;
import org.jooby.mvc.Path;
import org.jooby.mvc.Produces;

import br.com.badcompany.joobypetclinic.system.GenericException;


@Path("/pet")
@Produces("application/json")
@Consumes("application/json")
public class PetController {
	
	@GET
	@Path("/all")
	public List<Pet> getAllPets() {
		return petRepo.getAllPets();
	}
	
	@POST
	@Path("/save/:id")
	public Pet savePet(@Body Pet p, int id) {
		try {
			petRepo.savePet(id, p);
		} catch (GenericException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	@PUT
	@Path("/edit/:id")
	public Pet editPet(@Body Pet p, int id) {
		try {
			petRepo.updatePet(id, p);
		} catch (GenericException e) {
			e.printStackTrace();
		}
		return p;	
	}
	
	@GET
	@Path("/all/types")
	public List<PetType> getAllPetsTypes() {
		return petRepo.getAllPetTypes();
	}
	
}
