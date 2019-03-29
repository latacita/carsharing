package es.carsharing.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.carsharing.domainModel.Usuario;
import es.carsharing.domainModel.Viaje;
import es.carsharing.repositories.UsuarioRepository;
import es.carsharing.repositories.ViajeRepository;

@Service
public class ViajesMng {
	
	@PersistenceUnit
	EntityManagerFactory emf;
	
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
	

	public Optional<Viaje> findById(Long viajeId) {
		return vr.findById(viajeId);
	}
	
	@Transactional
	public Viaje anhadirViajero(Long viajeId, String username) throws ResourceNotFound {
		
		Viaje v = vr.findById(viajeId).orElseThrow(ResourceNotFound::new);
		Usuario user = ur.findById(username).orElseThrow(ResourceNotFound::new);
		
		return (v.aceptarUsuario(user))?v:null;
	}
	
	public Viaje anhadirViajeroGestionadoArtesano(Long viajeId, String username) throws ResourceNotFound {
		
		Viaje result = null;
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		try {
		
			Viaje v = em.find(Viaje.class, viajeId);
			
			if (v != null) {
				
				Usuario user = em.find(Usuario.class,username);
				
				if (user != null) {
					result = v.aceptarUsuario(user)?v:null;
				} else {
					throw new ResourceNotFound();
				} 
			} else {
				throw new ResourceNotFound();
			}
			
			em.getTransaction().commit();

		} catch(Exception e) {
			em.getTransaction().rollback();
			throw e;
		} 
		
		return result;
	}


}
