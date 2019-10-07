package br.com.badcompany.sparkjavapetclinic;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import com.google.gson.Gson;

import br.com.badcompany.sparkjavapetclinic.db.DBConnection;
import br.com.badcompany.sparkjavapetclinic.owner.OwnerController;
import br.com.badcompany.sparkjavapetclinic.owner.OwnerDao;
import br.com.badcompany.sparkjavapetclinic.system.WelcomeController;

public class App {
//	Declare deps
	public static DBConnection conn;
	public static OwnerDao ownerDao;
	public static Gson gson;
	public static void main(String[] args) {
		
//		Instatiate deps
		gson = new Gson();
		ownerDao = new OwnerDao();
		conn = new DBConnection();
		
//		Test
//		Owner a = new Owner();
//		a.setAddress("Rua A, 412");
//		a.setCity("Cidade A");
//		a.setFirstName("Ítalo");
//		a.setId(1);
//		a.setLastName("Costa");
//		a.setTelephone("98092834");
//		
//		Owner b = new Owner();
//		b.setAddress("Rua B, 412");
//		b.setCity("Cidade B");
//		b.setFirstName("Lincoln");
//		b.setId(2);
//		b.setLastName("Oliveira");
//		b.setTelephone("2394090");
//		
//		ownerDao.getOwners().add(a);
//		ownerDao.getOwners().add(b);
		
//		config SparkJava
		port(8081);

//		Routes
//		Error
//		Welcome
		get("/", WelcomeController.welcomeEndPoint, gson::toJson);
		get("/help", WelcomeController.helpEndPoint, gson::toJson);
		
//		Owner
		post("/saveOwner/", OwnerController.addOwnerEndPoint, gson::toJson);
		get("/listOwners/", OwnerController.listOwnersEndPoint, gson::toJson);
		
//		Vet
		
//		Visit
	}
}
