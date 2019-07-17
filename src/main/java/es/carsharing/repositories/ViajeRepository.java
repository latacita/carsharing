package es.carsharing.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.carsharing.domainModel.Viaje;

public interface ViajeRepository extends JpaRepository<Viaje,Long> {
	
	@Query("SELECT v FROM Viaje v WHERE v.origen.ciudad = ?1 AND v.destino.ciudad = ?2")
	public List<Viaje> findByOrigenCiudadAndDestinoCiudad(String origen, String destino);
	
}
