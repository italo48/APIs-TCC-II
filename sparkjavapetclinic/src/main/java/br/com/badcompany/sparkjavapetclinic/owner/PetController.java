package br.com.badcompany.sparkjavapetclinic.owner;

import static br.com.badcompany.sparkjavapetclinic.App.gson;
import static br.com.badcompany.sparkjavapetclinic.App.petRepo;

import com.google.gson.JsonSyntaxException;

import br.com.badcompany.sparkjavapetclinic.system.GenericException;
import br.com.badcompany.sparkjavapetclinic.util.MessageJson;
import spark.Request;
import spark.Response;
import spark.Route;

public class PetController {
//vou testa o final
	public static final Route addPetEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		Pet p = gson.fromJson(req.body(), Pet.class);
		
		int  ownerId = Integer.parseInt(req.params(":idOwner"));
		int typeId = Integer.parseInt(req.params(":idType")); 		
		try {
			petRepo.savePet(ownerId, typeId, p);
			res.status(200);
		} catch (JsonSyntaxException a) {
			a.printStackTrace();
			res.status(400);
			return new MessageJson("Yo, your json is wrong");
		} catch (GenericException e) {
			e.printStackTrace();
			res.status(404);			
			return new MessageJson(e.getMessage());
		}
		return p;
	};

	public static final Route listPetsEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		res.status(200);
		return petRepo.getAllPets();
	};
	public static final Route updatePetEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		Pet newPet = gson.fromJson(req.body(), Pet.class);
		return petRepo.updatePet(newPet);
	};
	
	public static final Route typePetsEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		res.status(200);
		return petRepo.getAllPetTypes();
	};
}
