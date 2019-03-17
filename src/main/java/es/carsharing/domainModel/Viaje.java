package es.carsharing.domainModel;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Viaje {

	@Id
	@GeneratedValue
	protected long id;
	
	protected int precio;
	protected int duracion;
	protected int numPlazas;
	protected Date fecha;
 
	@OneToOne(cascade=CascadeType.ALL)
	protected Localizacion origen;
	@OneToOne(cascade=CascadeType.ALL)
	protected Localizacion destino;
	
	@ManyToMany
	protected Set<Usuario> viajeros;
	@ManyToOne
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
	
}
