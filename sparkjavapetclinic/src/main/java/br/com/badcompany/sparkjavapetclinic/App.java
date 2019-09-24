package br.com.badcompany.sparkjavapetclinic;

import static spark.Spark.*;

public class App {
	public static void main(String[] args) {
		port(8080);
		get("/hello", (req, res) -> "Hello World");
	}
}
