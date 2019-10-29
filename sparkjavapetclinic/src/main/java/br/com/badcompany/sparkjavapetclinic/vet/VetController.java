package br.com.badcompany.sparkjavapetclinic.vet;

import static br.com.badcompany.sparkjavapetclinic.SparkJavaPetclinicApp.vetRepo;

import spark.Request;
import spark.Response;
import spark.Route;

public class VetController {
	public static Route getAllVetsEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		res.status(200);
		return vetRepo.getAllVets();
	};
}
