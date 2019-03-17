package es.carsharing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.carsharing.domainModel.Conductor;
import es.carsharing.domainModel.Localizacion;
import es.carsharing.domainModel.PuntoConocido;
import es.carsharing.domainModel.Usuario;
import es.carsharing.domainModel.Viaje;
import es.carsharing.repositories.ConductorRepository;
import es.carsharing.repositories.UsuarioRepository;
import es.carsharing.repositories.ViajeRepository;

@Component
public class AppFeeder implements CommandLineRunner {
	
	@Autowired
	protected UsuarioRepository ur;
	@Autowired
	protected ViajeRepository vr;
	@Autowired
	protected ConductorRepository cr;

	
	@Override
	public void run(String... args) throws Exception {
		feedUsuarios();
		feedConductores();
		feedViajes();
		
		testViajeRepository();
	}

	private void feedUsuarios() {
		Usuario u1 = new Usuario("Paco","paco@carSharing.es");
		Usuario u2 = new Usuario("Lola","lola@carSharing.es");
		ur.save(u1);
		ur.save(u2);
	}
	
	private void feedConductores() {
		Conductor c = new Conductor("Travis","travisBickle@carsharing.es",17);
		ur.save(c);
	}

	private void feedViajes() {
		
		Conductor c = cr.findById("Travis").get();
		
		Localizacion l11 = new PuntoConocido("Facultad de Ciencias", "Santander"); 
		Localizacion l12 = new PuntoConocido("Playa de la Caleta", "Cadiz");
		Localizacion l21 = new PuntoConocido("Playa de la Caleta", "Cadiz"); 
		Localizacion l22 = new PuntoConocido("Facultad de Ciencias", "Santander");
		
		
		SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");
		Date ida = null;
		Date vuelta = null;
		try {
			ida = dateParser.parse("01-04-2019");
			vuelta = dateParser.parse("07-04-2019");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Viaje v1 = new Viaje(l11,l12,c,ida,6000,8000,3);
		Viaje v2 = new Viaje(l21,l22,c,vuelta,6000,8000,3);
		
		vr.save(v1);
		vr.save(v2);
	}
	
	private void testViajeRepository() {
		Set<Viaje> viajes = vr.findByOrigen_CiudadAndDestino_Ciudad("Santander", "Cádiz");
	
		for(Viaje v : viajes) {
			System.out.println("Viaje in " + v.getFecha());
		}
		
	}

}
