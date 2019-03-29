package es.carsharing.domainModel;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;

import es.carsharing.service.api.Views;

@Entity
public class Viaje {

	@Id
	@GeneratedValue
	@JsonView({Views.DescripcionViaje.class,Views.DescripcionUsuario.class})
	protected long id;
	
	@JsonView(Views.DescripcionViaje.class)
	protected int precio;
	@JsonView(Views.DescripcionViaje.class)
	protected int duracion;
	@JsonView(Views.DescripcionViaje.class)
	protected int numPlazas;
	@JsonView({Views.DescripcionViaje.class,Views.DescripcionUsuario.class})
	protected Date fecha;
 
	@OneToOne(cascade=CascadeType.ALL)
	@JsonUnwrapped
	@JsonView({Views.DescripcionViaje.class,Views.DescripcionUsuario.class})
	protected Localizacion origen;
	@OneToOne(cascade=CascadeType.ALL)
	@JsonUnwrapped
	@JsonView({Views.DescripcionViaje.class,Views.DescripcionUsuario.class})
	protected Localizacion destino;
	
	@ManyToMany
	@JsonView({Views.DescripcionViaje.class})
	protected Set<Usuario> viajeros;
	@ManyToOne
	@JsonView(Views.DescripcionViaje.class)
	protected Conductor conductor;
	
	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getNumPlazas() {
		return numPlazas;
	}

	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public long getId() {
		return id;
	}

	public Localizacion getOrigen() {
		return origen;
	}

	public Localizacion getDestino() {
		return destino;
	}

	public Set<Usuario> getViajeros() {
		return viajeros;
	}

	public Conductor getConductor() {
		return conductor;
	}

	protected Viaje() {
		
	}

	public Viaje(Localizacion origen, Localizacion destino, Conductor conductor, 
			     Date fecha, int precio, int duracion, int numPlazas) {
		super();
		this.precio = precio;
		this.duracion = duracion;
		this.numPlazas = numPlazas;
		this.fecha = fecha;
		this.origen = origen;
		this.destino = destino;
		this.conductor = conductor;
	}
	
	public boolean aceptarUsuario(Usuario u) {
		
		boolean result = false;
		
		if (this.getViajeros().size() < this.getNumPlazas()) {
			this.getViajeros().add(u);
			u.anhadirViaje(this);
			result = true;
		} 
				
		return result;
	}
	
}
