package es.carsharing.services;
import org.springframework.beans.factory.annotation.Autowired;

import es.carsharing.domainModel.Viaje;
import es.carsharing.repositories.ViajeRepository;

public class ViajeService {
	
	@Autowired
	protected ViajeRepository vr;
	
	public boolean cerrarViaje(Long id) throws ViajeNotFound {
		
		Viaje v = vr.findById(id).orElseThrow(ViajeNotFound::new);
		
		return true;
		
	}

}
