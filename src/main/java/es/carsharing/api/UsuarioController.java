package es.carsharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.carsharing.domainModel.Usuario;
import es.carsharing.repositories.InitialUserData;
import es.carsharing.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository ur;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void anhadirUsuario(@RequestBody InitialUserData inputData) {
		System.out.println("Datos Recibidos = [" + inputData.getUsername() + "] " + inputData.getNombre() + " " + inputData.getApellido());
		ur.save(new Usuario(inputData.getUsername(),inputData.getNombre(),inputData.getApellido(),inputData.getEmail()));	
	}
	
	
	@GetMapping(value="/{id}")
	public Usuario obtenerUsuario(@PathVariable("id") String userId) {
		System.out.println("Trying to find " + userId);
		return ur.findById(userId).get();	
	}
	

}
