package br.com.badcompany.sparkjavapetclinic.visit;

import static br.com.badcompany.sparkjavapetclinic.SparkJavaPetclinicApp.gson;
import static br.com.badcompany.sparkjavapetclinic.SparkJavaPetclinicApp.visitRepo;

import com.google.gson.JsonSyntaxException;

import br.com.badcompany.sparkjavapetclinic.system.GenericException;
import br.com.badcompany.sparkjavapetclinic.util.MessageJson;
import spark.Request;
import spark.Response;
import spark.Route;

public class VisitController {
	public static final Route addVisitEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		try {
			visitRepo.saveVisit(gson.fromJson(req.body(), Visit.class));
			res.status(200);
		} catch (JsonSyntaxException a) {
			a.printStackTrace();
			res.status(500);
			return new MessageJson("Yo, your json is wrong");
		} catch (GenericException e) {
			res.status(500);
			return new MessageJson(e.getMessage());
		}
		return new MessageJson("Success");
	};
	
	public static final Route listVisitEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		res.status(200);
		return visitRepo.getAllVisits();
	};
}
