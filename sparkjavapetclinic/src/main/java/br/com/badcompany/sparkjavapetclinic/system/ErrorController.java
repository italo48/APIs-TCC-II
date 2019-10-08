package br.com.badcompany.sparkjavapetclinic.system;

import br.com.badcompany.sparkjavapetclinic.util.MessageJson;
import spark.Request;
import spark.Response;
import spark.Route;

public class ErrorController {
	public static Route ooopsEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		res.status(418);
		return new MessageJson("The server refuses an attempt at thick coffee in the teapot.");
	};
}
