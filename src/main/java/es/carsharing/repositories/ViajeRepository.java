package es.carsharing.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import es.carsharing.domainModel.Viaje;

public interface ViajeRepository extends JpaRepository<Viaje,Long> {
	
	public Set<Viaje> findByOrigen_CiudadAndDestino_Ciudad(String ciudadOrigen, String ciudadDestino); 

}
