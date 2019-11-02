package br.com.badcompany.sparkjavapetclinic;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.before;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.badcompany.sparkjavapetclinic.owner.OwnerController;
import br.com.badcompany.sparkjavapetclinic.owner.OwnerRepository;
import br.com.badcompany.sparkjavapetclinic.owner.PetController;
import br.com.badcompany.sparkjavapetclinic.owner.PetRepository;
import br.com.badcompany.sparkjavapetclinic.owner.PetValidator;
import br.com.badcompany.sparkjavapetclinic.system.ErrorController;
import br.com.badcompany.sparkjavapetclinic.system.WelcomeController;
import br.com.badcompany.sparkjavapetclinic.util.Exclusion;
import br.com.badcompany.sparkjavapetclinic.util.JPAUtils;
import br.com.badcompany.sparkjavapetclinic.vet.VetController;
import br.com.badcompany.sparkjavapetclinic.vet.VetRepository;
import br.com.badcompany.sparkjavapetclinic.visit.VisitController;
import br.com.badcompany.sparkjavapetclinic.visit.VisitRepository;

public class SparkJavaPetclinicApp {
//	Declare deps
	public static Gson gson;
	public static OwnerRepository ownerRepo;
	public static VetRepository vetRepo;
	public static PetRepository petRepo;
	public static VisitRepository visitRepo;

	public static void main(String[] args) {

//		Config Gson
		ExclusionStrategy strategy = new ExclusionStrategy() {
		    @Override
		    public boolean shouldSkipClass(Class<?> clazz) {
		        return false;
		    }
		 
		    @Override
		    public boolean shouldSkipField(FieldAttributes field) {
		        return field.getAnnotation(Exclusion.class) != null;
		    }
		};
		
//		Instatiate deps
		JPAUtils.initEmf();
		gson = new GsonBuilder()
				.addSerializationExclusionStrategy(strategy)
				.create();
		ownerRepo = new OwnerRepository();
		vetRepo = new VetRepository();
		petRepo = new PetRepository();
		visitRepo = new VisitRepository();

//		config SparkJava
		port(8081);
		
//		Routes
//		Validation
		before("/pet/addPet/:idOwner/", PetValidator.Validate);
		before("/pet/updatePet/:idOwner/", PetValidator.Validate);
		
		
//		Welcome
		get("/", WelcomeController.welcomeEndPoint, gson::toJson);
		get("/help/", WelcomeController.helpEndPoint, gson::toJson);

//		Owner
		post("/owner/saveOwner/", OwnerController.addOwnerEndPoint, gson::toJson);
		get("/owner/listOwners/", OwnerController.listOwnersEndPoint, gson::toJson);
		get("/owner/searchOwner/:name/", OwnerController.searchOwnerEndPoint, gson::toJson);
		put("/owner/updateOwner/", OwnerController.addOwnerEndPoint, gson::toJson);

//		Vet
		get("/listVets/", VetController.getAllVetsEndPoint, gson::toJson);
		
//		Pet
		post("/pet/addPet/:idOwner/", PetController.addPetEndPoint, gson::toJson);
		get("/pet/listPets/", PetController.listPetsEndPoint, gson::toJson);
		get("/pet/listPetTypes/", PetController.typePetsEndPoint, gson::toJson);
		put("/pet/updatePet/:idOwner/", PetController.updatePetEndPoint, gson::toJson);

//		Visit
		get("/visit/listVisit/:idPet/", VisitController.listVisitEndPoint, gson::toJson);
		post("/visit/addVisit/", VisitController.addVisitEndPoint, gson::toJson);
		

//		Error
		get("*", ErrorController.ooopsEndPoint, gson::toJson);
	}
}
