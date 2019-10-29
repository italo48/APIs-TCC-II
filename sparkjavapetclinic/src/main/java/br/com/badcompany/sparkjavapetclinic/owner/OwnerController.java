package br.com.badcompany.sparkjavapetclinic.owner;

import static br.com.badcompany.sparkjavapetclinic.SparkJavaPetclinicApp.gson;
import static br.com.badcompany.sparkjavapetclinic.SparkJavaPetclinicApp.ownerRepo;

import br.com.badcompany.sparkjavapetclinic.system.GenericException;
import br.com.badcompany.sparkjavapetclinic.util.MessageJson;
import spark.Request;
import spark.Response;
import spark.Route;

public class OwnerController {
	public static Route addOwnerEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		Owner o = gson.fromJson(req.body(), Owner.class);
		if(o.getPets().size() == 0) {			
			try {
				ownerRepo.saveOwner(o);
				res.status(200);
			} catch(GenericException e) {
				e.printStackTrace();
				res.status(500);
				return new MessageJson(e.getMessage());			
			}
			return o;
		}
		res.status(400);
		return new MessageJson("Cannot register an owner with a pet");			
	};

	public static Route listOwnersEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		res.status(200);
		return ownerRepo.getAllOwners();
	};

	public static Route searchOwnerEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		
		if (ownerRepo.findOwnerByName(req.params(":name")).size() == 0) {
			res.status(404);
			return new MessageJson("Owner not found");
		}
		return ownerRepo.findOwnerByName(req.params(":name"));
	};
}
