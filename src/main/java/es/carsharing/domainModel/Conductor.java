package es.carsharing.domainModel;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import es.carsharing.service.api.Views;

@Entity
public class Conductor extends Usuario {
	
	@JsonView(Views.DescripcionViaje.class)
	protected int anhosCarnet;
	@OneToMany(mappedBy="conductor")
	protected Set<Viaje> viajesActivos;
	@OneToMany(mappedBy="conductor")
	protected Set<Viaje> historialViajes;

	public int getAnhosCarnet() {
		return anhosCarnet;
	}

	public Set<Viaje> getViajesActivos() {
		return viajesActivos;
	}

	public Set<Viaje> getHistorialViajes() {
		return historialViajes;
	}
	
	protected Conductor() {
		
	}
	
	public Conductor(String username, String nombre, String apellido, String email, int anhosCarnet) {
		super(username,nombre,apellido,email);
		this.anhosCarnet = anhosCarnet;
	}

}
