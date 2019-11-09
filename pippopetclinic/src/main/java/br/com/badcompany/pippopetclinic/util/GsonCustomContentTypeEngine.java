package br.com.badcompany.pippopetclinic.util;

import static br.com.badcompany.pippopetclinic.PippoPetclinicApp.strategy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ro.pippo.core.Application;
import ro.pippo.core.ContentTypeEngine;

public class GsonCustomContentTypeEngine implements ContentTypeEngine {
	private Gson gson;
	@Override
	public void init(Application application) {
		gson = new GsonBuilder()
				.addSerializationExclusionStrategy(strategy)
				.create();
	}

	@Override
	public String getContentType() {
		return "application/json";
	}

	@Override
	public String toString(Object object) {
		return gson.toJson(object);
	}

	@Override
	public <T> T fromString(String content, Class<T> classOfT) {
		return gson.fromJson(content, classOfT);
	}

}
