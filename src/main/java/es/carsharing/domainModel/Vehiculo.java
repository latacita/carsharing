package es.carsharing.domainModel;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehiculo {
	
	@Id
	@GeneratedValue
	protected long id; 
	
	protected String matricula;
	@Embedded
	protected MarcaVehiculo marca;
	
	public Vehiculo(String matricula, MarcaVehiculo marca) {
		this.matricula = matricula;
		this.marca = marca;
	}
	
}
