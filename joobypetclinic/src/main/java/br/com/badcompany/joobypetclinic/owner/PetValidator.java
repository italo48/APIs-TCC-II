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
package br.com.badcompany.joobypetclinic.owner;

import java.util.ArrayList;
import java.util.List;

import org.jooby.Request;
import org.jooby.Response;
import org.jooby.Route.Chain;

import br.com.badcompany.joobypetclinic.util.ErrorJson;

public class PetValidator {
	public static void handle(Request req, Response res, Chain chain) throws Throwable {
		List<ErrorJson> invalidList = new ArrayList<>();
		Pet p = req.body(Pet.class);
		if (p.getName().isEmpty() || p.getName() == null)
			invalidList.add(new ErrorJson("Name cannot be empty or null"));
		if (p.isNew() && p.getType() == null)
			invalidList.add(new ErrorJson("Type cannot be null"));
		if (p.getBirthDate() == null)
			invalidList.add(new ErrorJson("Date of birth cannot be null"));

		if (!invalidList.isEmpty()) {
			res.status(500).send(invalidList);
		}
		chain.next(req, res);
	}
}
