package es.carsharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.carsharing.domainModel.Conductor;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor,String> {

}
