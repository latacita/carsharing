package es.carsharing.service.api;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.carsharing.domainModel.Viaje;
import es.carsharing.service.ResourceNotFound;
import es.carsharing.service.ViajesMng;

@RestController
@RequestMapping("/viajes")
public class ViajeController {
	
	@Autowired
	ViajesMng viajesSvc;
	
	@PersistenceUnit
	EntityManagerFactory emf;
	
	Logger logger = LoggerFactory.getLogger(ViajeController.class);

	@GetMapping
	@JsonView(Views.DescripcionViaje.class)
	public ResponseEntity<List<Viaje>> getViajes(@RequestParam(required=false) String origen, 
			@RequestParam(required=false) String destino) {
		
		ResponseEntity<List<Viaje>> result;
		
		if ((origen == null) && (destino == null)) {
			result = ResponseEntity.ok(viajesSvc.findAll());
		} else if ((origen != null) && (destino != null)) {
			result = ResponseEntity.ok(viajesSvc.findByOrigenAndDestino(origen, destino));
		} else {
			result = ResponseEntity.badRequest().build();
		}
		
		return result;
	}
	
	@GetMapping("/{viajeId}")
	@JsonView(Views.DescripcionViaje.class)
	public ResponseEntity<Viaje> getViaje(@PathVariable Long viajeId) {
		
		ResponseEntity<Viaje> result;
		
		Optional<Viaje> v = viajesSvc.findById(viajeId);
		
		if (v.isPresent()) {
			result = ResponseEntity.ok(v.get());
		} else {
			result = ResponseEntity.notFound().build();
		}
		
		return result;
	}
	
	@PutMapping("/{viajeId}/viajeros/{viajeroId}")
	@JsonView(Views.DescripcionViaje.class)
	public ResponseEntity<Viaje> anhadirViajero(@PathVariable Long viajeId, @PathVariable String viajeroId) {
		
		ResponseEntity<Viaje> result = null;
		
		try {
			Viaje v = viajesSvc.anhadirViajeroGestionadoArtesano(viajeId, viajeroId);
			if (v != null) {
				result = ResponseEntity.ok(v);
			} else {
				result = new ResponseEntity<Viaje>(HttpStatus.FORBIDDEN);
			}
		} catch (ResourceNotFound e) {
			result = ResponseEntity.notFound().build();
		}
		
		return result;
	}
	
}
