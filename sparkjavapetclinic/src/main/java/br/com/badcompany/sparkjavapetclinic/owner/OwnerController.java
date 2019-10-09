package br.com.badcompany.sparkjavapetclinic.owner;

import static br.com.badcompany.sparkjavapetclinic.App.gson;
import static br.com.badcompany.sparkjavapetclinic.App.ownerDao;

import com.google.gson.JsonSyntaxException;

import br.com.badcompany.sparkjavapetclinic.util.MessageJson;
import spark.Request;
import spark.Response;
import spark.Route;

public class OwnerController {
	public static Route addOwnerEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		try {
			ownerDao.saveOwner(gson.fromJson(req.body(), Owner.class));
			res.status(200);
		} catch (JsonSyntaxException a) {
			a.printStackTrace();
			res.status(400);
			return new MessageJson("Yo, your json is wrong");
		}
		return new MessageJson("Success");
	};

	public static Route listOwnersEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		res.status(200);
		return ownerDao.getAllOwners();
	};

	public static Route searchOwnerEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		if (ownerDao.getSizeOwners() == 0) {
			res.status(404);
			return new MessageJson("has not been found");
		}
		return ownerDao.findOwnerByName(req.params(":name"));
	};
}
