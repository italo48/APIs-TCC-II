package br.com.badcompany.pippopetclinic;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import br.com.badcompany.pippopetclinic.util.Exclusion;
import ro.pippo.core.Pippo;

public class PippoPetclinicApp {
	public static final ExclusionStrategy strategy = new ExclusionStrategy() {
		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return false;
		}

		@Override
		public boolean shouldSkipField(FieldAttributes field) {
			return field.getAnnotation(Exclusion.class) != null;
		}
	};

	public static void main(String[] args) {
		Pippo pippo = new Pippo(new BasicApplication());
		pippo.start();
	}
}
