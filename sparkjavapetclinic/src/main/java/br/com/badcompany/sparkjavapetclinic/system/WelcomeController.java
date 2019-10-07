package br.com.badcompany.sparkjavapetclinic.system;

import br.com.badcompany.sparkjavapetclinic.owner.Owner;
import spark.Request;
import spark.Response;
import spark.Route;

public class WelcomeController {
	public static Route welcomeEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		return "Hello, Mr ";
	};
	
	public static Route helpEndPoint = (Request req, Response res) -> {
		res.type("application/json");
		Owner o = new Owner();
		o.setAddress("Rua A, 412");
		o.setCity("Cidade A");
		o.setFirstName("Ítalo");
		o.setId(1);
		o.setLastName("Costa");
		o.setTelephone("98092834");
		return o;
	};
}
