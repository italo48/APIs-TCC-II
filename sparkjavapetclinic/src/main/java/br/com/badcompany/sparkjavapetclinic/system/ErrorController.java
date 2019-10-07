package br.com.badcompany.sparkjavapetclinic.system;

import spark.Request;
import spark.Response;
import spark.Route;

public class ErrorController {
	public static Route notFoundErrorHandler = (Request req, Response res) -> {
		res.type("application/json");
		return "Yo, there is something wrong there";
	};
}
