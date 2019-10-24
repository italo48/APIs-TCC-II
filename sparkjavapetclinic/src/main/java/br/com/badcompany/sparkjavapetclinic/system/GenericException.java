package br.com.badcompany.sparkjavapetclinic.system;

public class GenericException extends Exception {
	private static final long serialVersionUID = 1L;

	public GenericException(String errorMessagem) {
		super(errorMessagem);
	}
}
