package co.edu.icesi.ci.talleres.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;

public interface BusesRepository extends CrudRepository<Tmio1Bus, Integer> {

//	Optional<Tmio1Bus> findByPlaca(String placa);
//	void delete(Tmio1Bus bus);
//	Tmio1Bus save(Tmio1Bus bus);
}
