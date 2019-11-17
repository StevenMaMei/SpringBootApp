package co.edu.icesi.ci.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.service.ServicioBus;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;

@RestController
public class BusRestController {
	@Autowired
	private ServicioBus servicio;
	
	@GetMapping("/api/buses/")
	public Iterable<Tmio1Bus> indexBuses() {
		return servicio.findAll();
	}
	
	@PostMapping("/api/buses/")
	public void saveBus(@RequestBody Tmio1Bus bus) throws Exception {
		servicio.guardarBus(bus);
	}
	
	@GetMapping("/api/buses/search/findByPlaca")
	public Iterable<Tmio1Bus> findByPlaca(@Param("placa") String placa) throws Exception {
		return servicio.consultarBus(placa);
	}

}
