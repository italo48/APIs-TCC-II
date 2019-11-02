package br.com.badcompany.sparkjavapetclinic.system;

import br.com.badcompany.sparkjavapetclinic.owner.Pet;
import br.com.badcompany.sparkjavapetclinic.owner.PetType;
import br.com.badcompany.sparkjavapetclinic.util.MessageJson;
import spark.Request;
import spark.Response;
import spark.Route;

public class WelcomeController {
	public static Route welcomeEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		return new MessageJson("Hello!!");
	};
}
