package br.com.badcompany.sparkjavapetclinic;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.gson.Gson;

import br.com.badcompany.sparkjavapetclinic.owner.OwnerController;
import br.com.badcompany.sparkjavapetclinic.owner.OwnerDao;
import br.com.badcompany.sparkjavapetclinic.system.ErrorController;
import br.com.badcompany.sparkjavapetclinic.system.WelcomeController;
import br.com.badcompany.sparkjavapetclinic.vet.VetController;
import br.com.badcompany.sparkjavapetclinic.vet.VetDao;

public class App {
//	Declare deps
	public static EntityManagerFactory entityManagerFactory;
	public static Gson gson;
	
	public static OwnerDao ownerDao;
	public static VetDao vetDao;
	
	public static void main(String[] args) {
		
//		Instatiate deps
		entityManagerFactory = Persistence.createEntityManagerFactory("Petclinic-PU");
		gson = new Gson();
		
		ownerDao = new OwnerDao();
		vetDao = new VetDao();
		
//		config SparkJava
		port(8081);

//		Routes
//		Error
//		Welcome
		get("/", WelcomeController.welcomeEndPoint, gson::toJson);
		get("/help/", WelcomeController.helpEndPoint, gson::toJson);
		
//		Owner
		post("/saveOwner/", OwnerController.addOwnerEndPoint, gson::toJson);
		get("/listOwners/", OwnerController.listOwnersEndPoint, gson::toJson);
		get("/searchOwner/:name/", OwnerController.searchOwnerEndPoint, gson::toJson);
		
//		Vet
		get("/listVets/", VetController.getAllVetsEndPoint, gson::toJson);
		
//		Visit
		
//		Error
		get("/oops/", ErrorController.ooopsEndPoint, gson::toJson);
	}
}
