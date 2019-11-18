package co.edu.icesi.ci.restController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.delegate.TransactionBody;
import co.edu.icesi.ci.service.ServicioBus;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;

@RestController
public class BusRestController {
	@Autowired
	private ServicioBus servicio;
	
	@GetMapping("/api/buses/")
	public TransactionBody<ArrayList<Tmio1Bus>> indexBuses() {
		return new TransactionBody<>("buses",servicio.findAll());
	}
	
	@PostMapping("/api/buses/")
	public void saveBus(@RequestBody Tmio1Bus bus) throws Exception {
		servicio.guardarBus(bus);
	}
	
	@GetMapping("/api/buses/search/findByPlaca")
	public TransactionBody<ArrayList<Tmio1Bus>> findByPlaca(@Param("placa") String placa) throws Exception {
		return new TransactionBody<ArrayList<Tmio1Bus>>("buses", servicio.consultarBus(placa));
	}

}
