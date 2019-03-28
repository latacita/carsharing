package es.carsharing.service.api;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.carsharing.domainModel.Usuario;
import es.carsharing.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository ur;

	Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@PostMapping
	public ResponseEntity<Usuario> anhadirUsuario(@RequestBody 
			@JsonView(Views.NuevoUsuario.class) Usuario u) {

		ResponseEntity<Usuario> result;
		
		if (ur.existsById(u.getUsername())) {
			result = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		} else {
			u = ur.save(u);
			URI resourceLink = null;
			try {
				resourceLink = new URI("/usuarios/" + u.getUsername());
			} catch (URISyntaxException e) {
			}
			result = ResponseEntity.created(resourceLink).build();
		}
		
		return result;
		
	}
	
	@GetMapping(value="/{id}")
	@JsonView(Views.DescripcionUsuario.class)
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") String userId) {
		
		Optional<Usuario> u = ur.findById(userId);
		ResponseEntity<Usuario> result;
		
		if (u.isPresent()) {
			result = ResponseEntity.ok(u.get());
		} else { 
			result = ResponseEntity.notFound().build();
		}

		return result; 	
	}

}
