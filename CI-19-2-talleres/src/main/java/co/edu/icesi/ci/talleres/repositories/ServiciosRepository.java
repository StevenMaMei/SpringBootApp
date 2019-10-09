package co.edu.icesi.ci.talleres.repositories;

import org.springframework.data.repository.CrudRepository;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;

public interface ServiciosRepository extends CrudRepository<Tmio1Servicio, Tmio1ServicioPK> {

}
