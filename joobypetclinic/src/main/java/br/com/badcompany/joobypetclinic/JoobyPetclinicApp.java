/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package br.com.badcompany.joobypetclinic;

import org.jooby.Jooby;
import org.jooby.hbm.Hbm;
import org.jooby.jdbc.Jdbc;
import org.jooby.json.Gzon;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import br.com.badcompany.joobypetclinic.owner.Owner;
import br.com.badcompany.joobypetclinic.owner.OwnerController;
import br.com.badcompany.joobypetclinic.owner.OwnerRepository;
import br.com.badcompany.joobypetclinic.owner.Pet;
import br.com.badcompany.joobypetclinic.owner.PetController;
import br.com.badcompany.joobypetclinic.owner.PetRepository;
import br.com.badcompany.joobypetclinic.owner.PetType;
import br.com.badcompany.joobypetclinic.owner.PetValidator;
import br.com.badcompany.joobypetclinic.system.ErrorController;
import br.com.badcompany.joobypetclinic.system.WelcomeController;
import br.com.badcompany.joobypetclinic.util.Exclusion;
import br.com.badcompany.joobypetclinic.util.JPAUtils;
import br.com.badcompany.joobypetclinic.vet.Specialty;
import br.com.badcompany.joobypetclinic.vet.Vet;
import br.com.badcompany.joobypetclinic.vet.VetController;
import br.com.badcompany.joobypetclinic.vet.VetRepository;
import br.com.badcompany.joobypetclinic.visit.Visit;
import br.com.badcompany.joobypetclinic.visit.VisitController;
import br.com.badcompany.joobypetclinic.visit.VisitRepository;

public class JoobyPetclinicApp extends Jooby {
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
	
	public static final VetRepository vetRepo = new VetRepository();
	public static final OwnerRepository ownerRepo = new OwnerRepository();
	public static final PetRepository petRepo = new PetRepository(ownerRepo);
	public static final VisitRepository visitRepo = new VisitRepository(petRepo);
	{
		use(new Gzon().doWith(builder -> {
			builder.addSerializationExclusionStrategy(strategy);
		}));
		use(new Jdbc());
		use(new Hbm().classes(Vet.class, Owner.class, Pet.class, Visit.class, Specialty.class, PetType.class));

//		Não existe filter para o mvc
		use("/pet/save/*", "/pet/edit/*", (req, res, chain) -> {
			PetValidator.handle(req, res, chain);
		});
		
		use(WelcomeController.class);
		use(OwnerController.class);
		use(PetController.class);
		use(VisitController.class);
		use(VetController.class);
		use(ErrorController.class);
	}

	public static void main(final String[] args) {
		JPAUtils.initEmf();
		run(JoobyPetclinicApp::new, args);
	}

}