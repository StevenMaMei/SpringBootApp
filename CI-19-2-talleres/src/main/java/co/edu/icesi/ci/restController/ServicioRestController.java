package co.edu.icesi.ci.restController;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public Iterable<Tmio1Servicio> getIndex(){
		return servicioServicio.findAll();
	}

	@PostMapping("/api/servicios/")
	public void saveServicio(@RequestBody Tmio1ServicioWrapper tmio1ServicioWrapper) throws NumberFormatException, Exception {
		servicioServicio.guardarServicio(Integer.parseInt(tmio1ServicioWrapper.getIdBus()), tmio1ServicioWrapper.getCedulaConductor(), Integer.parseInt(tmio1ServicioWrapper.getRutaId()), tmio1ServicioWrapper.getFechaInicio(), tmio1ServicioWrapper.getFechaFin());	
	}

	@PutMapping("/api/servicios/")
	public void updateServicio(@RequestBody Tmio1ServicioWrapper tmio1ServicioWrapper) throws NumberFormatException, Exception {
		Tmio1Servicio ser= new Tmio1Servicio();

		Tmio1Servicio s= servicioServicio.consultarServicio(Integer.parseInt(tmio1ServicioWrapper.getIdBusViejo()), tmio1ServicioWrapper.getCedulaConductorViejo(), Integer.parseInt(tmio1ServicioWrapper.getRutaIdViejo()), tmio1ServicioWrapper.getFechaInicioViejo(), tmio1ServicioWrapper.getFechaFinViejo());
		if(s!= null) {


			ser.setTmio1Bus(servicioBus.consultarBus(Integer.parseInt(tmio1ServicioWrapper.getIdBus())));
			ser.setTmio1Conductore(servicioConductor.consultarConductor(tmio1ServicioWrapper.getCedulaConductor()));
			ser.setTmio1Ruta(servicioRuta.consultarRuta(Integer.parseInt(tmio1ServicioWrapper.getRutaId())));

			Tmio1ServicioPK pk = new Tmio1ServicioPK();
			pk.setCedulaConductor(tmio1ServicioWrapper.getCedulaConductor());
			pk.setFechaFin(tmio1ServicioWrapper.getFechaFin());
			pk.setFechaInicio(tmio1ServicioWrapper.getFechaInicio());
			pk.setIdBus(Integer.parseInt(tmio1ServicioWrapper.getIdBus()));
			pk.setIdRuta(Integer.parseInt(tmio1ServicioWrapper.getRutaId()));

			ser.setId(pk);

			servicioServicio.actualizarServicio(ser);
			servicioServicio.removerServicio(s);
		}
	}
	
	@GetMapping("/api/servicios/find")
	public Iterable<Tmio1Servicio> buscar(@Param("fecha") Date fecha) {
		return servicioServicio.findByDate(fecha);
	}
}
