package es.carsharing.domainModel;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Notificacion {

	protected String cabecera;
	protected String cuerpo;
	protected Date fecha;
	
	public String getCabecera() {
		return cabecera;
	}
	
	public String getCuerpo() {
		return cuerpo;
	}

	public Date getFecha() {
		return fecha;
	}

	public Notificacion() {
		
	}
	
	public Notificacion(String cabecera, String cuerpo) {
		super();
		this.cabecera = cabecera;
		this.cuerpo   = cuerpo;
		this.fecha    = new Date(); 
	}
	
}
