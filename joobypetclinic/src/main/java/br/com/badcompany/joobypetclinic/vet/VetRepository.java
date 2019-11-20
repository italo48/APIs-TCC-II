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
package br.com.badcompany.joobypetclinic.vet;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.badcompany.joobypetclinic.util.JPAUtils;

public class VetRepository {
	private EntityManager entityManager;
	private List<Vet> vets;
	
	public List<Vet> getAllVets() {
//		Deixar igual a do owner
		entityManager = JPAUtils.getEntityManager();
		entityManager.getTransaction().begin();
		vets = entityManager.createQuery("from Vet", Vet.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return vets;
	}
}
