package es.carsharing.repositories;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import es.carsharing.domainModel.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
	
	Usuario findByEmail(String email);
	
	Set<Usuario> findByFechaAltaAfter(Date fecha);

}
