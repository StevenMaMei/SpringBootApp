package co.edu.icesi.ci.restController;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.delegate.TransactionBody;
import co.edu.icesi.ci.service.ServicioBus;
import co.edu.icesi.ci.service.ServicioConductor;
import co.edu.icesi.ci.service.ServicioRuta;
import co.edu.icesi.ci.service.ServicioServicio;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioWrapper;

@RestController
public class ServicioRestController {

	@Autowired
	private ServicioServicio servicioServicio;


	@Autowired
	private ServicioBus servicioBus;

	@Autowired
	private ServicioConductor servicioConductor;

	@Autowired
	private ServicioRuta servicioRuta;

	@GetMapping("/api/servicios/")
	public TransactionBody<Iterable<Tmio1Servicio>> getIndex(){
		return new TransactionBody<>("servicios",servicioServicio.findAll());
	}

	@PostMapping("/api/servicios/")
	public TransactionBody<Object> saveServicio(@RequestBody TransactionBody<Tmio1ServicioWrapper> tmio1ServicioWrapper){
		Tmio1ServicioWrapper t = tmio1ServicioWrapper.getBody();
		try {
			servicioServicio.guardarServicio(Integer.parseInt(t.getIdBus()), t.getCedulaConductor(), Integer.parseInt(t.getRutaId()), t.getFechaInicio(), t.getFechaFin());
		} catch (NumberFormatException e) {
			return new TransactionBody<>("exception",e.getMessage());
		} catch (Exception e) {
			return new TransactionBody<>("exception",e.getMessage());
		}	
		return new TransactionBody<>("null",null);
		
	}

	@PutMapping("/api/servicios/")
	public TransactionBody<Object> updateServicio(@RequestBody TransactionBody<Tmio1ServicioWrapper> tmio1ServicioWrapper){
		Tmio1Servicio ser= new Tmio1Servicio();
		Tmio1ServicioWrapper t = tmio1ServicioWrapper.getBody();

		Tmio1Servicio s;
		try {
			s = servicioServicio.consultarServicio(Integer.parseInt(t.getIdBusViejo()), t.getCedulaConductorViejo(), Integer.parseInt(t.getRutaIdViejo()), t.getFechaInicioViejo(), t.getFechaFinViejo());
			if(s!= null) {
				
				
				ser.setTmio1Bus(servicioBus.consultarBus(Integer.parseInt(t.getIdBus())));
				ser.setTmio1Conductore(servicioConductor.consultarConductor(t.getCedulaConductor()));
				ser.setTmio1Ruta(servicioRuta.consultarRuta(Integer.parseInt(t.getRutaId())));
				
				Tmio1ServicioPK pk = new Tmio1ServicioPK();
				pk.setCedulaConductor(t.getCedulaConductor());
				pk.setFechaFin(t.getFechaFin());
				pk.setFechaInicio(t.getFechaInicio());
				pk.setIdBus(Integer.parseInt(t.getIdBus()));
				pk.setIdRuta(Integer.parseInt(t.getRutaId()));
				
				ser.setId(pk);
				
				servicioServicio.actualizarServicio(ser);
				servicioServicio.removerServicio(s);
			}
		} catch (NumberFormatException e) {
			return new TransactionBody<>("exception",e.getMessage());
		} catch (Exception e) {
			return new TransactionBody<>("exception",e.getMessage());
		}
		return new TransactionBody<>("null",null);
	}
	
	@GetMapping("/api/servicios/find")
	public TransactionBody<Iterable<Tmio1Servicio>> buscar(@Param("fecha") Date fecha) {
		return new TransactionBody<>("servicios",servicioServicio.findByDate(fecha));
	}
}
