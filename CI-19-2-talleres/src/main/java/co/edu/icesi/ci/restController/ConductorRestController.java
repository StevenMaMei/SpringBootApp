package co.edu.icesi.ci.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.delegate.TransactionBody;
import co.edu.icesi.ci.service.ServicioConductor;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

@RestController
public class ConductorRestController {
	
	@Autowired
	private ServicioConductor servicio;
	
	@GetMapping("/api/conductores/")
	public TransactionBody<Iterable<Tmio1Conductore>> getIndex() {
		return new TransactionBody<>("conductores", servicio.findAll());
	}
	
	@PostMapping("/api/conductores/")
	public void saveConductor(@RequestBody Tmio1Conductore conductor) throws Exception{
		servicio.guardarConductor(conductor);
	}
	
	@GetMapping("/api/conductores/search/findByCedula")
	public TransactionBody<Tmio1Conductore> findByCedula(@Param("cedula") String cedula) throws Exception{
		return new TransactionBody<>("conductor", servicio.consultarConductor(cedula));
	}
	
}
