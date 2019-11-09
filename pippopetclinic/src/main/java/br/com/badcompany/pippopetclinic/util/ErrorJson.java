package br.com.badcompany.pippopetclinic.util;

public class ErrorJson {
	private String error;

	public ErrorJson(String error) {
		super();
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
