package es.carsharing.domainModel;

import javax.persistence.Embeddable;

@Embeddable
public class MarcaVehiculo {
	
	protected String nombre;

	public String getVehiculo() {
		return this.nombre;
	}
	
	public MarcaVehiculo(String nombre) {
		this.nombre = nombre;
	}
	
}
