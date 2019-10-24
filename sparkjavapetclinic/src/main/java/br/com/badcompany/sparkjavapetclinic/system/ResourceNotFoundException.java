package br.com.badcompany.sparkjavapetclinic.system;

public class ResourceNotFoundException extends Exception {
	public ResourceNotFoundException(String errorMessagem) {
		super(errorMessagem);
	}
}
