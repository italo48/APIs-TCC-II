package br.com.badcompany.sparkjavapetclinic.owner;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.badcompany.sparkjavapetclinic.model.NamedEntity;

@Entity
@Table(name = "types")
public class PetType extends NamedEntity {
	private static final long serialVersionUID = 1L;

}
