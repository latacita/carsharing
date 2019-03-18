package es.carsharing.domainModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Localizacion {

	@Id
	@GeneratedValue
	protected long id;
	
	protected String ciudad;
	
	public String getCiudad() {
		return ciudad;
	}
	
	protected Localizacion() {
		
	}
	
	public Localizacion(String ciudad) {
		this.ciudad = ciudad;
	}
	
}
