package es.carsharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.carsharing.domainModel.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {

}
