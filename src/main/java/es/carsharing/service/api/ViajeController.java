package es.carsharing.service.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.carsharing.domainModel.Viaje;
import es.carsharing.repositories.ViajeRepository;

@RestController
@RequestMapping("/viajes")
public class ViajeController {
	
	@Autowired
	ViajeRepository vr;
	
	Logger logger = LoggerFactory.getLogger(ViajeController.class);

	@GetMapping
	@JsonView(Views.DescripcionViaje.class)
	public ResponseEntity<List<Viaje>> getViajes(@RequestParam(required=false) String origen, 
			@RequestParam(required=false) String destino) {
		
		ResponseEntity<List<Viaje>> result;
		
		if ((origen == null) && (destino == null)) {
			result = ResponseEntity.ok(vr.findAll());
		} else if ((origen != null) && (destino != null)) {
			result = ResponseEntity.ok(vr.findByOrigenAndDestino(origen, destino));
		} else {
			result = ResponseEntity.badRequest().build();
		}
		
		return result;
	}
	
	@GetMapping("/{viajeId}")
	@JsonView(Views.DescripcionViaje.class)
	public ResponseEntity<Viaje> getViaje(@PathVariable Long viajeId) {
		
		ResponseEntity<Viaje> result;
		
		Optional<Viaje> v = vr.findById(viajeId);
		
		if (v.isPresent()) {
			result = ResponseEntity.ok(v.get());
		} else {
			result = ResponseEntity.notFound().build();
		}
		
		return result;
	}
	
}
