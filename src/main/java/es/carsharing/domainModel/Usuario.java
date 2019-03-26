package es.carsharing.domainModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import es.carsharing.service.api.Views;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Usuario {
	
	@Id
	@JsonView({Views.DescripcionViaje.class,
		       Views.DescripcionUsuario.class,
			   Views.NuevoUsuario.class})
	protected String username;
	@JsonView({Views.DescripcionViaje.class,
		       Views.DescripcionUsuario.class,
			   Views.NuevoUsuario.class})
	protected String nombre;
	@JsonView({Views.DescripcionViaje.class,
	           Views.DescripcionUsuario.class,
		       Views.NuevoUsuario.class})
	protected String apellido;
	@JsonView({Views.DescripcionViaje.class,
    	       Views.DescripcionUsuario.class,
	    	   Views.NuevoUsuario.class})
	protected String email;
	
	@JsonIgnore
	protected Date fechaAlta;
	
	@ElementCollection
	@JsonView({Views.DescripcionViaje.class,Views.DescripcionUsuario.class})
	protected List<Integer> telefonos;
	
	@ManyToMany
	@JsonView({Views.DescripcionUsuario.class})
	protected Set<Viaje> plazasSolicitadas;
	@ManyToMany(mappedBy="viajeros")
	@JsonView({Views.DescripcionUsuario.class})
	protected Set<Viaje> viajesAceptados;
	
	public String getUsername() {
		return username;
	}

	public String getApellido() {
		return apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public List<Integer> getTelefonos() {
		return telefonos;
	}

	public Set<Viaje> getPlazasSolicitadas() {
		return plazasSolicitadas;
	}

	public Set<Viaje> getViajesAceptados() {
		return viajesAceptados;
	}
	
	protected Usuario( ) {
		
	}
	
	public Usuario(String username, String nombre, String apellido, String email) {
		this.username = username;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefonos = new ArrayList<Integer>();
		this.fechaAlta = new Date();
	}
	
	
	@Override
	public String toString() {
		return "[" + this.username + "] " + this.nombre + " " + this.apellido; 
	}
	

	public void solicitarPlaza(Viaje v) {
		
	}
	
	public void anhadirTelefono(Integer telefono) {
		this.telefonos.add(telefono);
	}
	
	public void borrarTelefono(Integer telefono) {
		// Comprobar que la lista se compacte
		this.telefonos.remove(telefono);
	}
	
	public void moverTelefono(Integer telefono, int nuevaPos) {
		// Pendiente de implementacion
	}
	
	
}
