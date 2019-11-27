package co.edu.icesi.ci.restController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.icesi.ci.delegate.TransactionBody;
import co.edu.icesi.ci.service.ServicioRuta;
import co.edu.icesi.ci.service.ServicioSitio;
import co.edu.icesi.ci.service.ServicioSitiosRuta;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRutaPK;

public class SitiosRutaRestController {

	@Autowired
	private ServicioSitiosRuta servicio;
	
	@Autowired
	private ServicioSitio sSitio;
	
	@Autowired
	private ServicioRuta sRuta;
	
	@GetMapping("/api/sitios-rutas")
	public TransactionBody<ArrayList<Tmio1SitiosRuta>> getIndex(){
		return new TransactionBody<>("sitiosrutas",servicio.findAll());
	}
	
	@PostMapping("/api/sitios-rutas")
	public void saveSitiosRuta(@RequestBody TransactionBody<Tmio1SitiosRutaPK> sitiosRuta) throws Exception {
		servicio.guardarSitioRuta(sitiosRuta.getBody());
	}
	
	@DeleteMapping("/api/sitios-rutas")
	public void deleteSitio(@Param("id") Tmio1SitiosRutaPK id) throws Exception {
		// TO-DO AQUI EL PROBLEMA
		Tmio1SitiosRuta sr = servicio.consultarSitioRuta(id);
		servicio.eliminarSitioRuta(sr);	
	}
	
	@PutMapping("/api/sitios-rutas")
	public void updateSitio(@RequestBody TransactionBody<Tmio1SitiosRuta> sitio) throws Exception {
		servicio.actualizarSitioRuta(sitio.getBody());
	}
	
	
}
