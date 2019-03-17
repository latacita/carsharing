package es.carsharing.domainModel;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
public class Direccion extends Localizacion {
	
	protected String calle;
	protected String numero;

	public String getCalle() {
		return calle;
	}

	public String getNumero() {
		return numero;
	}

	public Direccion(String calle, String numero, String ciudad) {
		super(ciudad);
		this.calle = calle;
		this.numero = numero;
	}
	
	


}