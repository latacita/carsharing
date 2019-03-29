package es.carsharing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.carsharing.domainModel.Usuario;
import es.carsharing.domainModel.Viaje;
import es.carsharing.repositories.UsuarioRepository;
import es.carsharing.repositories.ViajeRepository;

@Service
public class ViajesMng {
	
	@Autowired
	ViajeRepository vr;
	@Autowired
	UsuarioRepository ur;
	
	public List<Viaje> findAll() {
		return vr.findAll();
	}
	
	public List<Viaje> findByOrigenAndDestino(String origen, String destino) {
		return vr.findByOrigenAndDestino(origen, destino);
	}
	
	@Transactional
	public Viaje anhadirViajero(Long viajeId, String username) throws ResourceNotFound {
		
		Viaje result;
		
		Viaje v = vr.findById(viajeId).orElseThrow(ResourceNotFound::new);
		Usuario user = ur.findById(username).orElseThrow(ResourceNotFound::new);
		
		if (v.aceptarUsuario(user)) {
			result = v;
		} else {
			result = null;
		}
		
		return result;
	}

	public Optional<Viaje> findById(Long viajeId) {
		return vr.findById(viajeId);
	}

}
