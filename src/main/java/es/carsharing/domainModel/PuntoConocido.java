package es.carsharing.domainModel;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
public class PuntoConocido extends Localizacion {
	
	protected String nombreSitio;

	public String getNombreSitio() {
		return nombreSitio;
	}

	public PuntoConocido(String ciudad, String nombreSitio) {
		super(ciudad);
		this.nombreSitio = nombreSitio;
	}
	
}
