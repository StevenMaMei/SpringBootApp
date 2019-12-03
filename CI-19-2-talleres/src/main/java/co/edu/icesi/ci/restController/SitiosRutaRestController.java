package co.edu.icesi.ci.restController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.delegate.TransactionBody;
import co.edu.icesi.ci.service.ServicioSitiosRuta;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRutaPK;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRutaWrapper;

@RestController
public class SitiosRutaRestController {

	@Autowired
	private ServicioSitiosRuta servicio;
		
	@GetMapping("/api/sitios-rutas")
	public TransactionBody<ArrayList<Tmio1SitiosRuta>> getIndex(){
		return new TransactionBody<>("sitiosrutas",servicio.findAll());
	}
	
	@PostMapping("/api/sitios-rutas")
//	public void saveSitiosRuta(@RequestBody TransactionBody<Tmio1SitiosRutaPK> sitiosRuta) throws Exception {
//		servicio.guardarSitioRuta(sitiosRuta.getBody());
//		System.out.println("Encontre algo en rest: "+servicio.findAll().get(0).getTmio1Ruta1().getDescripcion());
//	}
	
	public void saveSitiosRuta(@RequestBody TransactionBody<Tmio1SitiosRutaWrapper> sitioRuta) throws Exception {
		servicio.guardarSitioRuta(sitioRuta.getBody());
	}
	
	@DeleteMapping("/api/sitios-rutas")
	public void deleteSitiosRuta(@RequestBody TransactionBody<Tmio1SitiosRutaWrapper> sitioRuta) throws Exception {
//		Tmio1SitiosRutaPK id = new Tmio1SitiosRutaPK();
//		id.setIdSitio(sitioRuta.getBody().getIdSitio());
//		id.setIdRuta(sitioRuta.getBody().getIdRuta());
//		Tmio1SitiosRuta sr = servicio.consultarSitioRuta(id);
//		if(sr==null) {
//			System.out.println("QUIUBO, NO ENCONTRE UN CARAJO");
//		}
//		servicio.eliminarSitioRuta(sr);	
		servicio.eliminarSitioRuta(sitioRuta.getBody());
	}
	
	@PutMapping("/api/sitios-rutas")
	public void updateSitio(@RequestBody TransactionBody<Tmio1SitiosRutaWrapper> sitio) throws Exception {
		System.out.println(sitio.getBody().getIdRutaViejo());
		servicio.actualizarSitioRuta(sitio.getBody());
	}
	
	
}
