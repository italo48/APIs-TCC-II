package br.com.badcompany.sparkjavapetclinic.owner;

import static br.com.badcompany.sparkjavapetclinic.SparkJavaPetclinicApp.gson;
import static spark.Spark.halt;

import br.com.badcompany.sparkjavapetclinic.util.MessageJson;
import spark.Filter;
import spark.Request;
import spark.Response;

public class PetValidator {
	public static final Filter Validate = (Request req, Response res) -> {
		res.type("application/json");
		
		Pet p = gson.fromJson(req.body(), Pet.class);
		if (p.getName().isEmpty() || p.getName() == null)		
			halt(500, gson.toJson(new MessageJson("Name cannot be empty or null")));
		if (p.isNew() && p.getType() == null)
			halt(500, gson.toJson(new MessageJson("Type cannot be null")));
		if (p.getBirthDate() == null)
			halt(500, gson.toJson(new MessageJson("Date of birth cannot be null")));
		
	};
}
