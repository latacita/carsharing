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

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Usuario {
	
	@Id
	protected String nombre;
	protected Date fechaAlta;
	protected String email;
	@ElementCollection
	protected List<Integer> telefonos;
	
	@ManyToMany
	protected Set<Viaje> plazasSolicitadas;
	@ManyToMany(mappedBy="viajeros")
	protected Set<Viaje> viajesAceptados;
	
	protected Usuario( ) {
		
	}
	
	public Usuario(String nombre, String email) {
		this.nombre = nombre;
		this.telefonos = new ArrayList<Integer>();
		this.email = email;
		this.fechaAlta = new Date();
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
